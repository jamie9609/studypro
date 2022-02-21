package com.jamie.workTest;

import com.alibaba.fastjson.JSON;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @PackageName: com.jamie.workTest
 * @ClassName: TestLamdaCase
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/2/15 10:21 上午
 */
public class TestLamdaCase {

    public static void main(String[] args) {

       /* List<Integer> list = new ArrayList<>();

        List<String> collect = list.stream().map(String::valueOf).collect(Collectors.toList());
        System.out.println(collect);*/

        /*List<Integer> listenBizTypes = new ArrayList<>(Collections.singletonList(12));
        listenBizTypes.add(123);
        System.out.println(listenBizTypes);

        List<Integer> listenBizTypes2 = new ArrayList<>(Collections.singletonList(12));
        listenBizTypes2.add(12223);
        System.out.println(listenBizTypes2);*/

        /*List<Long> test = new ArrayList<>();
        test.add(1212L);
        String s = JSON.toJSONString(test);
        System.out.println(s);

        List<String>  list = JSON.parseArray("[12122]").toJavaList(String.class);

        System.out.println(list);*/

        Map<Integer, List<Integer>> testHash = new HashMap<>();
        for(int i = 0; i < 10; i ++) {
            testHash.put(i, Collections.singletonList(i + 100));
        }
        Map<Integer, List<Integer>> testHash2 = new HashMap<>();
        testHash.forEach((k, v) -> {
            testHash2.put(k, v.stream().filter(item -> (item > 105)).collect(Collectors.toList()));
        });

        System.out.println("testHash2" + JSON.toJSONString(testHash2));
        System.out.println("testHash" + JSON.toJSONString(testHash));
    }
}
