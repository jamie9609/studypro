package com.jamie.javastudy.designPattern.decorator2;

/**
 * @PackageName: com.jamie.javastudy.designPattern.decorator2
 * @ClassName: SimpleCoffee
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 8:35 下午
 */
public class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 1;
    }

    @Override
    public String getMaterial() {
        return "coffee";
    }
}
