package com.jamie.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class}, scanBasePackages = { "com.jamie", "com.controller" })
/**
 * 如果不加@ServletComponentScan注解则会报404即找不到页面。
 * 如果Application类和Servlet类不在同一包下，则@ServletComponentScan需要添加相应的路径。
 * https://blog.csdn.net/m0_37739193/article/details/85097477
 */
@ServletComponentScan("com.jamie.servlet")
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

        /**
         * 此处可打印加载的bean
         */
        /*ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class, args);
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }*/
    }

}
