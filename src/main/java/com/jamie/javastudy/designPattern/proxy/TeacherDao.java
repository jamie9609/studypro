package com.jamie.javastudy.designPattern.proxy;

/**
 * @PackageName: com.jamie.study.designPattern.proxy
 * @ClassName: TeacherDao
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 12:04 下午
 */
public class TeacherDao implements ITeacherDao{

    @Override
    public void teach() {
        System.out.println("老师正在授课");
    }
}
