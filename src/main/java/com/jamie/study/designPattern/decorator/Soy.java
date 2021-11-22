package com.jamie.study.designPattern.decorator;

/**
 * @PackageName: com.jamie.study.designPattern.decorator
 * @ClassName: Soy
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 11:20 下午
 */
public class Soy extends Decorator{
    public Soy(Drink drk) {
        super(drk);
        setDesc("豆浆");
        setPrice(1.f);
    }
}
