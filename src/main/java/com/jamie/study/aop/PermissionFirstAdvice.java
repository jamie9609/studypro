package com.jamie.study.aop;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.jamie.study.aop
 * @ClassName: PermissionFirstAdvice
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/12 10:56 上午
 */
@Aspect
@Component
public class PermissionFirstAdvice {
    //定义一个切面

    @Pointcut("@annotation(com.jamie.study.aop.PermissionsAnnotation)")
    private void permissionCheck() {
    }

    @Around("permissionCheck()")
    public Object permissionCheckFirst(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("===================第一个切面===================：" + System.currentTimeMillis());

        //获取请求参数，详见接口类
        Object[] objects = joinPoint.getArgs();
        Long id = ((JSONObject) objects[0]).getLong("id");
        String name = ((JSONObject) objects[0]).getString("name");
        System.out.println("id--------------" + id);
        System.out.println("name--------------" + name);

        // id小于0则抛出非法id的异常
        if (id < 0) {
            return JSON.parseObject("{\"message\":\"illegal id\",\"code\":403}");
        }
        return joinPoint.proceed();
    }
}
