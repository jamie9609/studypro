package com.jamie.study.designPattern.proxy.dynamicProxy;

/**
 * @PackageName: com.jamie.study.designPattern.dynamicProxy
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 1:26 下午
 */
public class Client {
    public static void main(String[] args) {
        TeacherDao target = new TeacherDao();
        ITeacherDao proxyInstance = (ITeacherDao)new ProxyFactory(target).getProxyInstance();
        System.out.println("proxy" + proxyInstance.getClass());
        proxyInstance.teach();
    }
}
