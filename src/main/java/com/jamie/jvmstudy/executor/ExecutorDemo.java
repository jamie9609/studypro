package com.jamie.jvmstudy.executor;

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
     *
     *
     * 在《阿里巴巴java开发手册》中指出了线程资源必须通过线程池提供，不允许在应用中自行显示的创建线程，这样一方面是线程的创建更加规范，可以合理控制开辟线程的数量；另一方面线程的细节管理交给线程池处理，优化了资源的开销。而线程池不允许使用Executors去创建，而要通过ThreadPoolExecutor方式，这一方面是由于jdk中Executor框架虽然提供了如newFixedThreadPool()、newSingleThreadExecutor()、newCachedThreadPool()等创建线程池的方法，但都有其局限性，不够灵活；另外由于前面几种方法内部也是通过ThreadPoolExecutor方式实现，使用ThreadPoolExecutor有助于大家明确线程池的运行规则，创建符合自己的业务场景需要的线程池，避免资源耗尽的风险。
     *
     *
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
                System.out.println(10/0);
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

        TimeUnit.SECONDS.sleep(10);
        scheduledExecutor.shutdown();
        System.out.println(scheduledExecutor.isShutdown());
    }
}
