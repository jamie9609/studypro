package com.jamie.jvmstudy.executor;

import lombok.AllArgsConstructor;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;


/**
 *  CompletableFuture 详解（一）：基本概念及用法：
 *  https://blog.csdn.net/tongtest/article/details/107549749
 *
 *  CompletableFuture 详解（二）：supplyAsync / runAsync 实现原理 源码分析：
 *  https://blog.csdn.net/tongtest/article/details/107577226
 *
 * @PackageName: com.jamie.leetcode.dataStructure
 * @ClassName: CompletableFutureDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/3 11:47 上午
 */
public class CompletableFutureDemo {
    private static Integer count = 10;
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 70;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 100L;


    public static void main(String[] args) {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.allowCoreThreadTimeOut(true);


        System.out.println("cpu:" + Runtime.getRuntime().availableProcessors());

        CompletableFuture<String> supplyAsyncFuture = new CompletableFuture<>();
        CompletableFuture<String> supplyAsyncFuture2 = new CompletableFuture<>();
        CompletableFuture<Void> runAsyncFuture1 = new CompletableFuture<>();

        CompletableFuture<String>[] supplyAsyncCFuture = new CompletableFuture[count];


        /**
         * 第一种只需传入一个Supplier实例（一般使用lamda表达式），此时框架会默认使用ForkJoin的线程池来执行被提交的任务。
         * 例如：completable task=0, thread=ForkJoinPool.commonPool-worker-1
         */
        for (int i = 0; i < count; i ++) {
            int finalI = i;
            CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(()->{
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("completable task=" + finalI + ", thread=" + Thread.currentThread().getName() );
                /*try {
                    throw new Exception("手动异常");
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
                return "test";
            });
            supplyAsyncCFuture[i] = supplyAsync;
        }

        CompletableFuture.allOf(supplyAsyncCFuture).whenComplete((a,b)->{
            if(b!=null){
                System.out.println("error stack trace->");
                b.printStackTrace();
            }else{
                System.out.println("Finally run succ,result->" + a);
            } });


        /**
         * 第二种可以指定自定义的线程池，然后将任务提交给该线程池执行。
         */
        /*for (int i = 0; i < count; i ++) {
            int finalI = i;
            supplyAsyncFuture2= CompletableFuture.supplyAsync(()->{
                System.out.println("completable task=" + finalI + ", thread=" + Thread.currentThread().getName() );
                return "test";
            }, threadPoolExecutor);
        }*/

        /**
         * CompletableFuture.supplyAsync()也可以用来创建CompletableFuture实例。通过该函数创建的CompletableFuture实例会异步执行当前传入的计算任务。在调用端，则可以通过get或join获取最终计算结果。
         *
         * CompletableFuture.runAsync()也可以用来创建CompletableFuture实例。与supplyAsync()不同的是，runAsync()传入的任务要求是Runnable类型的，所以没有返回值。runAsync适合创建不需要返回值的计算任务。
         */
        CompletableFuture<Void> runAsyncFuture2 = new CompletableFuture<>();
        for (int i = 0; i < count; i ++) {
            int finalI = i;
            runAsyncFuture1 = CompletableFuture.runAsync(()->{
                System.out.println("runAsyncFuture2 task=" + finalI + ", thread=" + Thread.currentThread().getName() );
            }, threadPoolExecutor);
        }


        CompletableFuture<String> stringCompletableFuture = supplyAsyncFuture.thenApply((e) -> {
            System.out.println("Xxxxx task=" + ", thread=" + Thread.currentThread().getName());
            return "hello world";
        });

        CompletableFuture<String> stringCompletableFuture3 = stringCompletableFuture.whenComplete((a, e) -> {
                    if (Objects.nonNull(e)) {
                        System.out.println(e);
                    } else {
                        System.out.println("success when complete");
                    }
                });


        //future.complete("manual test");
        //runAsyncFuture.join();
        System.out.println(supplyAsyncFuture.join());
        System.out.println(stringCompletableFuture3.join());
        supplyAsyncFuture.complete("future");
    }

}
