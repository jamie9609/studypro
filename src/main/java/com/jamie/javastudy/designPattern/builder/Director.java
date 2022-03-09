package com.jamie.javastudy.designPattern.builder;

/**
 * @PackageName: com.jamie.javastudy.designPattern.builder
 * @ClassName: Director
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/9 3:58 下午
 */
public class Director {
    private CommonHouseBuilder builder;

    public Director(CommonHouseBuilder builder) {
        this.builder = builder;
    }

    public void buildHouseA() {
        builder.buildBasic();
        builder.roofed();
        System.out.println("房子A建造完成");
    }

    public void buildHouseB() {
        builder.buildWall();
        builder.buildBasic();
        System.out.println("房子B建造完成");
    }
}
