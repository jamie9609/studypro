package com.jamie.study.designPattern.bridge;

/**
 * @PackageName: com.jamie.study.designPattern.bridge
 * @ClassName: Xiaomi
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 6:45 下午
 */
public class Xiaomi implements Brand{
    @Override
    public void open() {
        System.out.println("小米手机开机了");
    }

    @Override
    public void close() {
        System.out.println("小米手机关机了");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话");
    }
}
