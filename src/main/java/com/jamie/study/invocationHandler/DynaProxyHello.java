package com.jamie.study.invocationHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @PackageName: com.jamie.study.invocationHandler
 * @ClassName: DynaProxyHello
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/19 10:59 上午
 */
public class DynaProxyHello implements InvocationHandler {
    private Object delegate;

    public Object bind(Object delegate) {
        this.delegate = delegate;
        return Proxy.newProxyInstance(this.delegate.getClass().getClassLoader(), this.delegate.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("问候之前的日志记录。。。");
            String name = method.getName();
            System.out.println("original method name: "+ name);

            if (name.equals("sayHello")) {
                System.out.println("日志策略a:  " + name);
            } else if (name.equals("sayGoodsBye")) {
                System.out.println("日志策略b:  " + name);
            }
            result = method.invoke(this.delegate, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
