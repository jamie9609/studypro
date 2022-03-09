package com.jamie.javastudy.designPattern.builder;

/**
 * @PackageName: com.jamie.study.designPattern.builder
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 2:41 下午
 */
public class Client {

    public static void main(String[] args) {

        CommonHouseBuilder builder = new CommonHouseBuilder();
        Director director = new Director(builder);
        director.buildHouseA();
        System.out.println("------------------");
        director.buildHouseB();

    }
}
