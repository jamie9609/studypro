package com.jamie.study.designPattern.bridge;

/**
 * @PackageName: com.jamie.study.designPattern.bridge
 * @InterfaceName: Phone
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 6:47 下午
 */
public abstract class Phone {
    private Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }

    protected void open(){
        this.brand.open();
    }

    protected void close(){
        this.brand.close();
    }

    protected void call(){
        this.brand.call();
    }
}
