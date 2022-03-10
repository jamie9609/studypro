package com.jamie.javastudy.designPattern.adapter;

/**
 * 类的适配器模式
 * @PackageName: com.jamie.javastudy.designPattern.adapter
 * @ClassName: Typec2Vga1
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 6:55 下午
 */
public class TypeC2Vga1 extends MyPhone implements MyVga{
    @Override
    public void vgaInterface() {
        typeCPhone();
        System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
        System.out.println("信息已转换成VGA接口，显示屏可以对接。");
    }
}
