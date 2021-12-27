package com.jamie.javastudy.smartInitializingSingleton;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

/**
 * 实现SmartInitializingSingleton的接口后，当所有单例 bean 都初始化完成以后， Spring的IOC容器会回调该接口的 afterSingletonsInstantiated()方法。
 * @PackageName: com.jamie.javastudy.smartInitializingSingleton
 * @ClassName: SmartInitializingSingletonDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/27 5:51 下午
 */

@Component
public class SmartInitializingSingletonDemo implements SmartInitializingSingleton {
    private ListableBeanFactory beanFactory;

    public SmartInitializingSingletonDemo(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void afterSingletonsInstantiated() {
        String[] beanNames = beanFactory.getBeanNamesForType(SmartInitializingSingletonDemo.class);
        System.out.println("hello world");
        for (String s : beanNames) {
            System.out.println(s);
        }
    }
}
