package com.jamie.javastudy.designPattern.strategy;

/**
 * @PackageName: com.jamie.javastudy.designPattern.strategy
 * @ClassName: Context
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/10 3:36 下午
 */
public class Context {
    public Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void contextInterface() {
        strategy.algorithmInterface();
    }

}
