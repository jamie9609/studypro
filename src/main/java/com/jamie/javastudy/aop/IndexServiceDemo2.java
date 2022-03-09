package com.jamie.javastudy.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @PackageName: com.jamie.javastudy.aop
 * @ClassName: IndexServiceDemo2
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/2 11:07 上午
 */
@Component
public class IndexServiceDemo2 {

    @Autowired
    private IndexServiceDemo1 indexServiceDemo1;

}
