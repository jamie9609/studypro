package com.jamie.javastudy.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: Person
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 5:24 下午
 */
public abstract class Person {

    public abstract void accept(Action action);
}
