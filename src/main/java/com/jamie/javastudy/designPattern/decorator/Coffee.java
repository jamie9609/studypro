package com.jamie.javastudy.designPattern.decorator;

/**
 * @PackageName: com.jamie.study.designPattern.decorator
 * @ClassName: Coffee
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 10:57 下午
 */
public class Coffee extends Drink{

    @Override
    public float cast() {
        return super.getPrice();
    }
}
