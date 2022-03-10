package com.jamie.javastudy.designPattern.decorator2;

/**
 * @PackageName: com.jamie.javastudy.designPattern.decorator2
 * @ClassName: WithSugar
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 8:40 下午
 */
public class WithSugar extends CoffeeDecorator{
    public WithSugar(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double getCost() {
        return super.getCost() + 1;
    }

    @Override
    public String getMaterial() {
        return super.getMaterial() + ", " + "sugar";
    }
}
