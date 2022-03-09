package com.jamie.javastudy.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.jamie.javastudy.aop
 * @ClassName: IndexServiceDemo1
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/2 11:07 上午
 */
@Component
public class IndexServiceDemo1 {

    @Autowired
    private IndexServiceDemo2 indexServiceDemo2;

    public IndexServiceDemo1 () {
        System.out.println("IndexServiceDemo1 build success ...");
    }
}
