package com.jamie.javastudy.designPattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @PackageName: com.jamie.study.designPattern.iterator
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/22 9:01 下午
 */
public class Client {
    public static void main(String[] args) {
        List<Object> testCase = new ArrayList<>();
        testCase.add("jack");
        Iterator it = testCase.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}
