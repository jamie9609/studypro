package com.jamie.javastudy.designPattern.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @PackageName: com.jamie.study.designPattern.proxy.cglib
 * @ClassName: ProxyFactory
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 2:44 下午
 */
public class ProxyFactory implements MethodInterceptor {
    //维护一个目标对象
    private Object target;

    //构造器，传入一个被代理的对象
    public ProxyFactory(Object target){
        this.target = target;
    }

    //返回一个代理对象，是target对象的代理对象
    public Object getProxyInstance() {
        //1、创建一个工具类
        Enhancer enhancer = new Enhancer();
        //2、设置父类
        enhancer.setSuperclass(target.getClass());
        //3、设置回调函数
        enhancer.setCallback(this);
        //4、创建子类对象、代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("cglib 代理模式开始～～～～～");
        Object returnVal = method.invoke(target, args);
        System.out.println("cglib 代理模式提交～～～～～");
        return returnVal;
    }
}
