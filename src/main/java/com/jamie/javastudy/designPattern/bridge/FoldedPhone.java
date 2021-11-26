package com.jamie.javastudy.designPattern.bridge;

/**
 * @PackageName: com.jamie.study.designPattern.bridge
 * @ClassName: FoldedPhone
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 6:50 下午
 */
public class FoldedPhone extends Phone{
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    @Override
    public void open() {
        super.open();
        System.out.println("折叠样式");
    }

    @Override
    public void close() {
        super.close();
        System.out.println("折叠样式");
    }

    @Override
    public void call() {
        super.call();
        System.out.println("折叠样式");
    }
}
