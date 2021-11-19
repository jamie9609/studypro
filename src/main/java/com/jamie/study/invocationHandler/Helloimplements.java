package com.jamie.study.invocationHandler;

/**
 * @PackageName: com.jamie.study.invocationHandler
 * @ClassName: Helloimplements
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/19 10:47 上午
 */
public class Helloimplements implements IHello{
    @Override
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }

    @Override
    public void sayGoodsBye(String name) {
        System.out.println(name + " GoodBye！");

    }
}
