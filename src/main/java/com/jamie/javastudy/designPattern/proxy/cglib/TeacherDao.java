package com.jamie.javastudy.designPattern.proxy.cglib;

/**
 * @PackageName: com.jamie.study.designPattern.proxy.cglib
 * @ClassName: TeacherDao
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 2:35 下午
 */
public class TeacherDao {
    public void teach() {
        System.out.println("老师授课中，我是cglib代理，不需要实现接口");
    }
}
