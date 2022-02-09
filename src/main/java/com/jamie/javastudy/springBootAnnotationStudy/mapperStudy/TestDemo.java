package com.jamie.javastudy.springBootAnnotationStudy.mapperStudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * @PackageName: com.jamie.javastudy.springBootAnnotationStudy.mapper
 * @ClassName: TestDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/2/9 5:16 下午
 */
@Component
public class TestDemo {

    @Autowired
     MapperUtilTest mapperUtilTest;

    public void testUtil () {
        AppleDO appleDO = new AppleDO("富士", "red", 11, 10001);
        AppleDTO convert = mapperUtilTest.convert(appleDO);
        System.out.println(convert.toString());
        System.out.println("xxx");
    }
}
