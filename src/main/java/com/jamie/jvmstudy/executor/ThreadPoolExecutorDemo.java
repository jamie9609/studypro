package com.jamie.jvmstudy.executor;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @PackageName: com.jamie.jvmstudy.executor
 * @ClassName: ThreadPoolExecutorDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/2 8:59 下午
 */
public class ThreadPoolExecutorDemo {

    private static final int CORE_POOL_SIZE = 50;
    private static final int MAX_POOL_SIZE = 70;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 100L;


    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 10; i++) {
            MyRunnable myRunnable = new MyRunnable(String.valueOf(i));
            threadPoolExecutor.execute(myRunnable);

            threadPoolExecutor.execute(() ->{
                System.out.println("方法2线程:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.isTerminated()) {
        }
        System.out.println("Finished all threads");
        while (!threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS)) {
            System.out.println("线程池中还有任务在处理");
        }

    }

    public static class MyRunnable implements Runnable {

        private String command;

        public MyRunnable(String s) {
            this.command = s;
        }

        @Override
        public void run() {
            System.out.println("方法1线程:" + Thread.currentThread().getName() + " Start. Time = " + new Date());
            processCommand();
            System.out.println("方法1线程:" + Thread.currentThread().getName() + " End. Time = " + new Date());
        }

        private void processCommand() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        @Override
        public String toString() {
            return this.command;
        }
    }
}
