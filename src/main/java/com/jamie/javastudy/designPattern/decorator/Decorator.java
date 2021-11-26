package com.jamie.javastudy.designPattern.decorator;

/**
 * @PackageName: com.jamie.study.designPattern.decorator
 * @ClassName: Decorator
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 11:03 下午
 */
public class Decorator extends Drink{
    private Drink obj;

    public Decorator(Drink drk){
        this.obj = drk;
    }

    @Override
    public float cast() {
        return super.getPrice() + obj.getPrice();
    }

    @Override
    public String getDesc(){
        return super.getDesc() + super.getPrice() + " && " + obj.getDesc();
    }
}
