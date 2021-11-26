package com.jamie.javastudy.designPattern.adapter;

/**
 * @PackageName: com.jamie.study.designPattern.adapter
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 4:11 下午
 */
public class Client {

    public static void main(String[] args) {
        System.out.println("=====适配器模式======");
        Phone.chargeing(new VoltageAdapter(new Voltage220V()));
    }
}
