package com.jamie.javastudy.concurrency;


import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @PackageName: com.jamie.study.concurrency
 * @ClassName: Atomic
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/15 11:51 上午
 */
@Slf4j
public class Atomic {
    AtomicInteger count   = new AtomicInteger();
    public static final String hello = "hello";
    public void test() {
        for (int i =0 ; i < 100; i ++) {
            Try.runRunnable(() -> {
                count.addAndGet(1);
                log.info("this is count={}",count.get());
                    }
            );
        }
    }

    public static void main(String[] args) {
        Atomic atomic = new Atomic();
        atomic.test();
    }

}
