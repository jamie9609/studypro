package com.jamie.javastudy.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: Fail
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 5:26 下午
 */
public class Fail extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价是很失败！");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给的评价是很失败！");
    }
}
