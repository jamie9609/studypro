package com.jamie.workTest;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @PackageName: com.jamie.workTest
 * @ClassName: List2String
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/29 6:10 下午
 */
public class DeepCopyList {

    /**
     * 深拷贝
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.arch"));
        System.out.println(System.getProperty("os.name"));
        String testStr = "12";

        List<String> ids = Arrays.stream(StringUtils.split(testStr, ',')).collect(Collectors.toList());

        System.out.println(ids.toString());

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            list.add("HELLO" + i);
        }

        //list深度拷贝
        List<String> newList = new ArrayList<>();
        CollectionUtils.addAll(newList, new String[list.size()]);
        Collections.copy(newList, list);
        newList.set(0, "set enw");

        System.out.println("原list值：" + list);
        System.out.println("新list值：" + newList);


        newList.retainAll(list);
        System.out.println("新list值：" + newList);

        /*Optional<Object> x = Optional.empty();
        if(!x.isPresent()) {
            System.out.println("xxxx");
        }*/

        /*List<Integer> cityList = new ArrayList<>();
        cityList.add(1);
        cityList.add(3);
        cityList.add(4);
        cityList.add(5);
        System.out.println(cityList.get(3));*/
        //System.out.println(cityList.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
