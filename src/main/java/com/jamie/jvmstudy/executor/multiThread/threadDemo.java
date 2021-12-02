package com.jamie.jvmstudy.executor.multiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 *
 * 创建线程的多种方式
 * @PackageName: com.jamie.jvmstudy.executor.multiThread
 * @ClassName: threadDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/26 12:08 下午
 */
public class threadDemo {

    /**
     * 方法1；
     * 实现Runnable接口，重载run()，无返回值，Runnable接口的存在主要是为了解决Java中不允许多继承的问题。
     */
    public static class ThreadRunnable implements Runnable {
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable:" + Thread.currentThread().getName() + ":" + i);
            }
        }
    }

    /**
     * 方法2；
     * 继承Thread类，重写run()，通过调用Thread的start()会调用创建线程的run()，
     * 不同线程的run方法里面的代码交替执行。但由于Java不支持多继承.因此继承Thread类就代表这个子类不能继承其他类.
     */
    public static class ThreadCustom extends Thread {
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread:" + Thread.currentThread().getName() + ":" + i);
            }
        }
    }


    public static void main(String[] args) {
        /**
         * 方法3；
         * 实现Callable接口，通过FutureTask/Future来创建有返回值的Thread线程，
         * 通过Executor执行，该方式有返回值，可以获得异步。
         */
        FutureTask<Integer> integerFutureTask = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    System.out.println("FutureTask:" + Thread.currentThread().getName() + ":" + i);
                }
                return 1;
            }
        });
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(integerFutureTask);


        /**
         * 方法4:
         * 通过线程池其动感多线程。
         *  其一: FixThreadPool(int n); 固定大小的线程池
         *  使用于为了满足资源管理需求而需要限制当前线程数量的场合。使用于负载比较重的服务器。
         *  需要保证顺序执行各个任务的场景
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

        for(int i = 0; i < 5; i++) {
            fixedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++) {
                        System.out.println("fixedThreadPool" + Thread.currentThread().getName() + ":" + j);
                    }

                }
            });
        }
        fixedThreadPool.shutdown();

        /**
         * 其二: SingleThreadPoolExecutor :单线程池
         * 需要保证顺序执行各个任务的场景
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 5; i++) {
            singleThreadExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<10;j++) {
                        System.out.println("singleThreadExecutor" + Thread.currentThread().getName() + ":" + j);
                    }

                }
            });
        }
        singleThreadExecutor.shutdown();

        /**
         * 其三：CashedThreadPool(); 缓存线程池
         *  当提交任务速度高于线程池中任务处理速度时，缓存线程池会不断的创建线程
         *  适用于提交短期的异步小程序，以及负载较轻的服务器
         */
        ExecutorService CachedThreadPool= Executors.newCachedThreadPool();
        for(int i = 0; i < 5; i++) {
            CachedThreadPool.submit(new Runnable() {
                @Override
                public void run() {
                    for(int j = 0;j < 10; j++) {
                        System.out.println("CachedThreadPool" + Thread.currentThread().getName() + j);
                    }

                }
            });
        }
        CachedThreadPool.shutdown();



        Thread thread1 = new Thread(new ThreadRunnable());
        Thread thread2 = new Thread(new ThreadRunnable());
        thread1.start();
        thread2.start();
        ThreadCustom threadCustom1 = new ThreadCustom();
        ThreadCustom threadCustom2 = new ThreadCustom();
        threadCustom1.start();
        threadCustom2.start();

    }

}
