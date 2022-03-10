package com.jamie.javastudy.designPattern.strategy;

/**
 * @PackageName: com.jamie.javastudy.designPattern.strategy
 * @ClassName: ConcreteStrategyB
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 3:39 下午
 */
public class ConcreteStrategyB extends Strategy{
    @Override
    public void algorithmInterface() {
        System.out.println("算法B的实现");
    }
}
