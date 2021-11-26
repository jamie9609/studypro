package com.jamie.javastudy.invocationHandler;

/**
 * @PackageName: com.jamie.study.invocationHandler
 * @ClassName: testCase
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/19 10:53 上午
 */
public class testCase {
    public static void main(String[] args) {
        // 静态代理方法。
        /*Helloimplements helloimplements = new Helloimplements();
        StaticProxy staticProxy = new StaticProxy();
        //staticProxy.setImpl(helloimplements);
        staticProxy.sayHello("jamie");
        staticProxy.sayGoodsBye("jamie");*/

        // 动态代理方法
        DynaProxyHello dynaProxyHello = new DynaProxyHello();
        Helloimplements helloImpl = new Helloimplements();
        IHello bind = (IHello) dynaProxyHello.bind(helloImpl);
        bind.sayHello("JAMIE");
        bind.sayGoodsBye("jamie");
    }
}
