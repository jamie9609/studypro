package com.jamie.javastudy.designPattern.decorator2;

/**
 * @PackageName: com.jamie.javastudy.designPattern.decorator2
 * @ClassName: CoffeeDecorator
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 8:36 下午
 */
public abstract class CoffeeDecorator implements Coffee {

    public Coffee coffeeDecorator;

    public CoffeeDecorator(Coffee coffeeDecorator) {
        this.coffeeDecorator = coffeeDecorator;
    }

    public double getCost() {
        return coffeeDecorator.getCost();
    }

    public String getMaterial() {
        return coffeeDecorator.getMaterial();
    }
}
