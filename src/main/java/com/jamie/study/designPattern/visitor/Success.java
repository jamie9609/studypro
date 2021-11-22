package com.jamie.study.designPattern.visitor;

/**
 * @PackageName: com.jamie.study.designPattern.visitor
 * @ClassName: Success
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 5:26 下午
 */
public class Success extends Action{
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价是很成功！");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给的评价是很成功！");
    }
}
