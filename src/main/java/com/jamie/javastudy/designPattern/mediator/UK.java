package com.jamie.javastudy.designPattern.mediator;

/**
 * @PackageName: com.jamie.javastudy.designPattern.mediator
 * @ClassName: UK
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/14 2:28 下午
 */
public class UK extends Country{

    public UK(UnitedNations unitedNations) {
        super(unitedNations);
    }

    public void declare(String message) {
        unitedNations.declare(message, this);
    }

    public void getMessage(String message) {
        System.out.println("英国获得对方信息：" + message);
    }

}
