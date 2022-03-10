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


        //第一种适配器用法
        System.out.println("-------------第一种适配器------------");
        MyVga vga = new TypeC2Vga1();
        vga.vgaInterface();//适配器将typec转换成vga
        System.out.println("显示屏对接适配器，手机成功投影到显示屏!");

        //第二种适配器用法
        System.out.println("-------------第二种适配器------------");
        TypeC2Vga2 typeC2Vga2 = new TypeC2Vga2(new MyPhone());
        typeC2Vga2.vgaInterface();//适配器将typec转换成vga
        System.out.println("显示屏对接适配器，手机成功投影到显示屏!");
    }
}
