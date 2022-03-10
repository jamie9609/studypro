package com.jamie.javastudy.designPattern.strategy;

/**
 * @PackageName: com.jamie.javastudy.designPattern.strategy
 * @ClassName: ConcreteStrategyA
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 3:38 下午
 */
public class ConcreteStrategyA extends Strategy{
    @Override
    public void algorithmInterface() {
        System.out.println("算法A的实现");
    }
}
