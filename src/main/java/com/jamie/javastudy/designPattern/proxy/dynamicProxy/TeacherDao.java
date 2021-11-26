package com.jamie.javastudy.designPattern.proxy.dynamicProxy;

/**
 * @PackageName: com.jamie.study.designPattern.dynamicProxy
 * @ClassName: TeacherDao
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 1:16 下午
 */
public class TeacherDao implements ITeacherDao{
    @Override
    public void teach() {
        System.out.println("老师正在授课中，，");
    }
}
