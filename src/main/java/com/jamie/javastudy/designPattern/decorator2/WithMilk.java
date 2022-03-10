package com.jamie.javastudy.designPattern.decorator2;

/**
 * @PackageName: com.jamie.javastudy.designPattern.decorator2
 * @ClassName: WithMilk
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 8:39 下午
 */
public class WithMilk extends CoffeeDecorator{
    public WithMilk(Coffee coffeeDecorator) {
        super(coffeeDecorator);
    }

    @Override
    public double getCost() {
        double additionalCost = 0.5;
        return super.getCost() + additionalCost;
    }

    @Override
    public String getMaterial() {
        return super.getMaterial() + ", " + "milk";
    }

}
