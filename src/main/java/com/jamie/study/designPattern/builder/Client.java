package com.jamie.study.designPattern.builder;

/**
 * @PackageName: com.jamie.study.designPattern.builder
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 2:41 下午
 */
public class Client {

    public static void main(String[] args) {
        CommonHouse commonHouse = new CommonHouse();
        commonHouse.build();

        //用到了建造者模式
        StringBuilder stringBuilder = new StringBuilder("hello" + "world");
        System.out.println(stringBuilder);
    }
}
