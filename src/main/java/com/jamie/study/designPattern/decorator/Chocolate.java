package com.jamie.study.designPattern.decorator;

/**
 * @PackageName: com.jamie.study.designPattern.decorator
 * @ClassName: Chocolate
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 11:17 下午
 */
public class Chocolate extends Decorator{
    public Chocolate(Drink drk) {
        super(drk);
        setDesc("巧克力");
        setPrice(3.1f);
    }
}
