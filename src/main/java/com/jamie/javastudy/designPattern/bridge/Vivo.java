package com.jamie.javastudy.designPattern.bridge;

/**
 * @PackageName: com.jamie.study.designPattern.bridge
 * @ClassName: Vivo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 6:46 下午
 */
public class Vivo implements Brand{
    @Override
    public void open() {
        System.out.println("vivo手机开机");
    }

    @Override
    public void close() {
        System.out.println("vivo手机关机");
    }

    @Override
    public void call() {
        System.out.println("vivo手机打电话");
    }
}
