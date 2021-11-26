package com.jamie.javastudy.invocationHandler;

/**
 * @PackageName: com.jamie.study.invocationHandler
 * @ClassName: StaticProxy
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/19 10:51 上午
 */
public class StaticProxy implements IHello{
    private IHello iHello;

    public void setImpl(IHello impl) {
        this.iHello = impl;
    }

    @Override
    public void sayHello(String name) {
        System.out.println("问候之前的日志记录...");
        iHello.sayHello(name);
    }

    @Override
    public void sayGoodsBye(String name) {
        System.out.println("分别之前的日志记录...");
        iHello.sayGoodsBye(name);
    }
}


