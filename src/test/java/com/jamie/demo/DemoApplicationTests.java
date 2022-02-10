package com.jamie.demo;

import com.jamie.javastudy.springBootAnnotationStudy.mapperStudy.TestMapperStudyDemo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = DemoApplication.class)
class DemoApplicationTests {
    @Resource
    TestMapperStudyDemo testMapperStudyDemo;

    @Test
    void test() {
        testMapperStudyDemo.testUtil();
    }

}
