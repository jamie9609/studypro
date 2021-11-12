package com.jamie.study.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.jamie.study.annotation
 * @ClassName: testAnnotationDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/11 7:54 下午
 */
@Component
public class TestAnnotationDemoA {

    @Autowired
    TestAnnotationDemoB testAnnotationDemoB;

    public void testA(TestAnnotationDemoB testAnnotationDemoB) {
        System.out.println("test" + "testAnnotationDemoB");
    }

}
