package com.jamie.study.designPattern.proxy.dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @PackageName: com.jamie.study.designPattern.dynamicProxy
 * @ClassName: ProxyFactory
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 1:16 下午
 */
public class ProxyFactory {
    private Object target;

    public ProxyFactory(Object target){
        this.target = target;
    }

    //给目标对象生成代理对象
    public Object getProxyInstance() {
        //1、ClassLoader loader 制定当前目标对象使用的类加载器，获取加载器的方法固定
        //2、Class<?>[] interfaces，目标对象实现的接口类型，使用范型方法确认类型；
        //3、InvocationHandler h，时间处理，执行目标对象的方法时，会触发事情处理器的方法。
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("jdk代理开始～～～～～");
                Object returnVal = method.invoke(target, args);
                return returnVal;
            }
        });
    }

}
