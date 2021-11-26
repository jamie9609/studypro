package com.jamie.javastudy.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.jamie.study.annotation
 * @ClassName: testAnnotationDemoB
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/11 7:55 下午
 */
@Component
public class TestAnnotationDemoB {

    @Autowired
    TestAnnotationDemoA testAnnotationDemoA;

    public void testA(TestAnnotationDemoA testAnnotationDemoA) {
        System.out.println("test" + "testAnnotationDemoA");
    }

}
