package com.jamie.jvmstudy;

import lombok.SneakyThrows;

import java.util.concurrent.*;

/**
 * 参考：https://cloud.tencent.com/developer/article/1551331?from=10680
 * @PackageName: com.jamie.jvmstudy
 * @ClassName: Executor2Demo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/2 7:15 下午
 */
public class Executor2Demo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Runnable runnable = new Runnable(){
            @SneakyThrows
            @Override
            public void run() {
                System.out.println("Runnable" + "," + Thread.currentThread().getName()+",start!");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Runnable" + "," + Thread.currentThread().getName()+",end!");
            }
        };

        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("Callable" + "," + Thread.currentThread().getName()+",start!");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("Callable" + "," + Thread.currentThread().getName()+",end!");
                return 10;
            }
        };
        //一个有返回值
        Future<Integer> result = executorService.submit(callable);
        //一个没有返回值
        executorService.submit(runnable);

        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName());
        System.out.println(System.currentTimeMillis() + "," + Thread.currentThread().getName() + ",结果：" + result.get());

    }
}
