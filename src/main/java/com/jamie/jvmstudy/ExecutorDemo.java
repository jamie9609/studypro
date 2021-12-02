package com.jamie.jvmstudy;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @PackageName: com.jamie.jvmstudy
 * @ClassName: ExecutorDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/2 5:24 下午
 */
public class ExecutorDemo {

    /**
     * newScheduledThreadPool 主要用来延迟执行任务，或者定时执行任务。功能和Timer类似，但是ScheduleThreadPoolExecutor更强大、更灵活一些。Timer后台是单个线程，而ScheduleThreadPoolExecutor可以在创建的时候指定多个线程。
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        System.out.println(System.currentTimeMillis() + "-----------ready-----------");

        AtomicInteger atomicInteger = new AtomicInteger();

        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(10);

        Runnable runnable = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                int increment = atomicInteger.getAndIncrement();
                System.out.println(Thread.currentThread().getName() + "a执行测试:" + increment);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable runnable2 = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                int increment = atomicInteger.getAndIncrement();
                System.out.println(Thread.currentThread().getName() + "b执行测试:" + increment);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        scheduledExecutor.scheduleAtFixedRate(runnable, 1,1, TimeUnit.SECONDS);
        scheduledExecutor.scheduleAtFixedRate(runnable2, 1,1, TimeUnit.SECONDS);
        //scheduledExecutor.schedule(runnable2, 2, TimeUnit.SECONDS);

        /*scheduledExecutor.schedule(() ->{
            System.out.println(System.currentTimeMillis() + "开始执行任务");
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "结束执行任务");

        }, 10, TimeUnit.SECONDS);


        scheduledExecutor.schedule(() ->{
            System.out.println(System.currentTimeMillis() + "开始执行任务B");
            try {
                TimeUnit.SECONDS.sleep(2);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(System.currentTimeMillis() + "结束执行任务B");

        }, 10, TimeUnit.SECONDS);*/


    }
}
