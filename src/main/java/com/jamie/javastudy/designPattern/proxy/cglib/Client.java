package com.jamie.javastudy.designPattern.proxy.cglib;

/**
 * @PackageName: com.jamie.study.designPattern.proxy.cglib
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 2:58 下午
 */
public class Client {
    public static void main(String[] args) {
        TeacherDao target = new TeacherDao();

        TeacherDao proxyInstance = (TeacherDao)new ProxyFactory(target).getProxyInstance();

        proxyInstance.teach();
    }
}
