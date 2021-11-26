package com.jamie.javastudy.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

/**
 * @PackageName: com.jamie.study.annotation
 * @ClassName: RedisLockAspect
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/20 5:22 下午
 */
public class RedisLockAspect {

    @Around(value = "@annotation(com.jamie.study.annotation.EnableRedisLock)")
    public void handleRedisLock(ProceedingJoinPoint joinPoint) {
        // 通过反射获取到注解对象
        EnableRedisLock redisLock = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(EnableRedisLock.class);
        // 获取注解变量的值
        String lockKey = redisLock.lockKey();
        long expireTime = redisLock.expireTime();
        TimeUnit timeUnit = redisLock.timeUnit();
        int retryTimes = redisLock.retryTime();

        // 获取锁
        if (tryLock(lockKey, expireTime, timeUnit, retryTimes)) {
            try {
                // 获取锁成功继续执行业务逻辑
                joinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
                releseLock();
            }

        }
    }

    public boolean tryLock(String lockKey, long expireTime, TimeUnit timeUnit, int retryTimes) {
        System.out.println("locked");
        return true;
    }

    public boolean releseLock() {
        System.out.println("unclocked");
        return true;
    }


}
