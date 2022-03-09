package com.jamie.javastudy.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @PackageName: com.jamie.javastudy.aop
 * @ClassName: AppTest
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/2 11:10 上午
 */
public class AppTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}
