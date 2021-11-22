package com.jamie.study.designPattern.decorator;

/**
 * @PackageName: com.jamie.study.designPattern.decorator
 * @ClassName: Milk
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 11:19 下午
 */
public class Milk extends Decorator{
    public Milk(Drink drk){
        super(drk);
        setDesc("牛奶");
        setPrice(2.1f);

    }
}
