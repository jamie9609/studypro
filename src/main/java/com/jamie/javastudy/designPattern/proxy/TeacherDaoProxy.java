package com.jamie.javastudy.designPattern.proxy;

/**
 * @PackageName: com.jamie.study.designPattern.proxy
 * @ClassName: TeacherDaoProvy
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 12:05 下午
 */
public class TeacherDaoProxy implements ITeacherDao{

    private ITeacherDao target;

    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("开始代理。。。");
        target.teach();
        System.out.println("提交。。。。");
    }
}
