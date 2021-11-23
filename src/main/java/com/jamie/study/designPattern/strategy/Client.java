package com.jamie.study.designPattern.strategy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @PackageName: com.jamie.study.designPattern.strategy
 * @ClassName: Client
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 5:18 下午
 */
public class Client {
    public static void main(String[] args) {

        Integer[] data = {1, 3, 5, 2, 30, 32, 2};

        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return -1;
                } else {
                    return 1;
                }
            }
        };

        Arrays.sort(data, comparator);


        Arrays.sort(data, (a1, a2) -> {
            if (a1.compareTo(a2) > 0) {
                return 1;
            }else {
                return -1;
            }
        });
        System.out.println(Arrays.asList(data));
    }
}
