package com.jamie.study.designPattern.builder;

/**
 * @PackageName: com.jamie.study.designPattern.builder
 * @ClassName: AbstractHouse
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 2:36 下午
 */
public abstract class AbstractHouse {

    //打地基
    public abstract void buildBasic();
    //砌墙
    public abstract void buildWall();
    //封顶
    public abstract void roofed();

    public void build() {
        buildBasic();
        buildWall();
        roofed();
    }
}
