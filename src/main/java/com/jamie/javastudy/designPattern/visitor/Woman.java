package com.jamie.javastudy.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: Woman
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 5:25 下午
 */
public class Woman extends Person{
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
