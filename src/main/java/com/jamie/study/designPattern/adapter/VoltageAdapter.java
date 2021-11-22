package com.jamie.study.designPattern.adapter;

/**
 * @PackageName: com.jamie.study.designPattern.adapter
 * @ClassName: VoltageAdapter
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 4:04 下午
 */
public class VoltageAdapter implements IVoltage5V{
    private Voltage220V voltage220V;

    public VoltageAdapter(Voltage220V voltage220V) {
        this.voltage220V = voltage220V;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if (null != voltage220V) {
            int src = voltage220V.output220V();
            System.out.println("使用适配器适配");
            dst = src / 44;
            System.out.println("适配完成，输出的电压=" + dst);
        }
        return dst;
    }
}
