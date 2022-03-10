package com.jamie.javastudy.designPattern.adapter;

/**
 * 对象的适配器模式
 * @PackageName: com.jamie.javastudy.designPattern.adapter
 * @ClassName: TypeC2Vga2
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 6:58 下午
 */
public class TypeC2Vga2 implements MyVga{
    public MyPhone myPhone;

    public TypeC2Vga2(MyPhone myPhone) {
        this.myPhone = myPhone;
    }

    @Override
    public void vgaInterface() {
        if(myPhone != null) {
            myPhone.typeCPhone();
            System.out.println("接收到Type-c口信息，信息转换成VGA接口中...");
            System.out.println("信息已转换成VGA接口，显示屏可以对接。");
        }
    }
}
