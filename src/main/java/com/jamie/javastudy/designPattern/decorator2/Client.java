package com.jamie.javastudy.designPattern.decorator2;

/**
 * @PackageName: com.jamie.javastudy.designPattern.decorator2
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 8:40 下午
 */
public class Client {

    public static void main(String[] args) {
        SimpleCoffee coffee = new SimpleCoffee();
        Coffee withMilk = new WithMilk(coffee);
        System.out.println(withMilk.getCost());
        System.out.println(withMilk.getMaterial());

        Coffee withSugar = new WithSugar(withMilk);
        System.out.println(withSugar.getCost());
        System.out.println(withSugar.getMaterial());
    }
}
