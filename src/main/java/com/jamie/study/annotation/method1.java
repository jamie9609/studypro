package com.jamie.study.annotation;

import java.util.concurrent.TimeUnit;

/**
 * @PackageName: com.jamie.study.annotation
 * @ClassName: method1
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/20 6:02 下午
 */
public class method1 {

    @EnableRedisLock(lockKey = "student", expireTime = 10, timeUnit = TimeUnit.SECONDS, retryTime = 5)
    public void method1() {
        // 这里写业务逻辑
    }
}
