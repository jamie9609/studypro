package com.jamie.javastudy.designPattern.adapter;

/**
 * @PackageName: com.jamie.study.designPattern.adapter
 * @ClassName: Voltage220V
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 4:02 下午
 */
public class Voltage220V {

    public int output220V(){
        int src = 220;
        System.out.println("电压=" + src + "伏");
        return src;
    }
}
