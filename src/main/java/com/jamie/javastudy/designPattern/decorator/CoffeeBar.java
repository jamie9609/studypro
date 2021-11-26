package com.jamie.javastudy.designPattern.decorator;

/**
 * @PackageName: com.jamie.study.designPattern.decorator
 * @ClassName: CoffeeBar
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 11:23 下午
 */
public class CoffeeBar {
    public static void main(String[] args) {

        Drink longBlack = new LongBlack();
        longBlack = new Milk(longBlack);
        System.out.println("点一杯咖啡，再加一份牛奶：" + longBlack.getDesc());

        longBlack = new Chocolate(longBlack);
        System.out.println("点一杯咖啡，再加一份牛奶，再加一份巧克力：" + longBlack.getDesc());

    }
}
