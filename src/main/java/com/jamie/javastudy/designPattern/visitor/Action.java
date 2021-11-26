package com.jamie.javastudy.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: Action
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 5:22 下午
 */
public abstract class Action {

    public abstract void getManResult(Man man);

    public abstract void getWomanResult(Woman woman);
}
