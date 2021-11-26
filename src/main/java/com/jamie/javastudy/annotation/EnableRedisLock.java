package com.jamie.javastudy.annotation;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @PackageName: com.jamie.study.annotation
 * @ClassName: EnableRedisLock
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/20 5:01 下午
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface EnableRedisLock {
    String lockKey();

    long expireTime() default 5;

    TimeUnit timeUnit() default  TimeUnit.SECONDS;

    int retryTime() default 10;
}
