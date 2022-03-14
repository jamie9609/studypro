package com.jamie.javastudy.designPattern.mediator;

/**
 * @PackageName: com.jamie.javastudy.designPattern.mediator
 * @ClassName: USA
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/14 2:22 下午
 */
public class USA extends Country {

    public USA(UnitedNations unitedNations) {
        super(unitedNations);
    }

    public void declare(String message) {
        unitedNations.declare(message, this);
    }

    public void getMessage(String message) {
        System.out.println("美国获得对方信息：" + message);
    }
}
