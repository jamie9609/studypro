package com.jamie.jvmstudy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 如果是在request方法上面加synchronized，重量级锁，执行时间5s多。只是对cas加锁，执行时间131ms.
 * @PackageName: com.jamie.jvmstudy
 * @ClassName: CasMultiDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/2 3:53 下午
 */
public class CasMultiDemo {
    //访问次数
    volatile static int count = 0;

    //模拟访问一次 加了重量级锁，导致每次只有单线程执行，执行时间很长。 是悲观锁。
    public static void request() throws InterruptedException {
        //模拟耗时5毫秒
        TimeUnit.MILLISECONDS.sleep(5);
        int expectCount;
        do {
            expectCount = getCount();
        } while ( !compareAndSwap(expectCount, expectCount + 1));
    }

    /**
     * 获取当前count值
     * @return
     */
    public static int getCount() {
        return count;
    }

    /**
     * CAS方法。这个方法使用synchronized修饰了，能保证此方法是线程安全的。这种加锁方式是乐观锁，cas中就使用了这样的操作。
     * 判断count当前值是否和期望的expectCount一样，如果一样将newCount赋值给count
     * @param expectCount
     * @param newCount
     * @return
     */
    public static synchronized boolean compareAndSwap(int expectCount, int newCount) {
        if (getCount() == expectCount) {
            count = newCount;
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        for (int i = 0; i < threadSize; i ++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j ++) {
                        request();
                    }
                }catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                    System.out.println("当前线程:" + Thread.currentThread().getName() + " 倒数计时器：" + countDownLatch.getCount());
                }
            });
            thread.start();
        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("----------" + Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count=" + count);
    }

}
