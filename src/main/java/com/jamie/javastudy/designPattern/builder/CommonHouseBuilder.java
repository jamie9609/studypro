package com.jamie.javastudy.designPattern.builder;

/**
 * @PackageName: com.jamie.study.designPattern.builder
 * @ClassName: CommonHouse
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/21 2:39 下午
 */
public class CommonHouseBuilder extends AbstractHouseBuilder {
    @Override
    public void buildBasic() {
        System.out.println("给普通房子打地基");
    }

    @Override
    public void buildWall() {
        System.out.println("给普通房子砌墙");
    }

    @Override
    public void roofed() {
        System.out.println("给普通房子封顶");
    }
}
