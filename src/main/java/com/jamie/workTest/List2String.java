package com.jamie.workTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @PackageName: com.jamie.workTest
 * @ClassName: List2String
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/29 6:10 下午
 */
public class List2String {

    public static void main(String[] args) {
        List<Integer> cityList = new ArrayList<>();
        cityList.add(1);
        cityList.add(3);
        cityList.add(4);
        cityList.add(5);
        System.out.println(cityList.get(3));
        //System.out.println(cityList.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
