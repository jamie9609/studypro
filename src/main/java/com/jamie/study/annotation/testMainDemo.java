package com.jamie.study.annotation;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @PackageName: com.jamie.study.annotation
 * @ClassName: testMainDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/11 7:58 下午
 */
public class testMainDemo {

    @Autowired
    TestAnnotationDemoB testAnnotationDemoB;

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
