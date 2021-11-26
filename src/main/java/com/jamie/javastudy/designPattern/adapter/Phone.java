package com.jamie.javastudy.designPattern.adapter;

/**
 * @PackageName: com.jamie.study.designPattern.adapter
 * @ClassName: phone
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 4:08 下午
 */
public class Phone {

    public static void chargeing(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5) {
            System.out.println("电压为5v，可以充电");
        } else if(iVoltage5V.output5V() > 5) {
            System.out.println("电压大于5v，不可充电");
        }
    }
}
