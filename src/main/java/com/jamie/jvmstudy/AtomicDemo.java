package com.jamie.jvmstudy;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 与 CasMultiDemo 互为对照。实现多线程下的计数安全。
 * @PackageName: com.jamie.jvmstudy
 * @ClassName: AtomicDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/2 4:34 下午
 */
public class AtomicDemo {

    //static AtomicInteger count =  new AtomicInteger();

    volatile static int count = 0;

    /**
     * volatile 关键字 https://www.cnblogs.com/dolphin0520/p/3920373.html
     * @throws InterruptedException
     */

    public static void request() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(5);
        //count.incrementAndGet();
        count ++;
    }


    public static void main(String[] args) throws InterruptedException {
        long starTime = System.currentTimeMillis();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        for (int i = 0; i < threadSize; i++) {
            Thread thread = new Thread(() -> {
                try {
                    for (int j = 0; j < 10; j++) {
                        request();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
            thread.start();
        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println(Thread.currentThread().getName() + "，耗时：" + (endTime - starTime) + ",count=" + count);
    }
}
