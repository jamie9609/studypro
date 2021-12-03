package com.jamie.workTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @PackageName: com.jamie.workTest
 * @ClassName: HeapSortTest
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/3 5:43 下午
 */
public class HeapSortTest {

    public static List<Integer> HeapSortDemo(List<Integer> source) {
        int n = source.size();

        List<Integer> ans = new ArrayList<>();
        ans.add(Integer.MAX_VALUE);
        for (Integer item : source) {
            ans.add(item);
            swimAuto(ans);
        }

        return ans;
    }

    /**
     * 上浮
     * @param ans
     */
    public static void swimAuto(List<Integer> ans) {
        int sKey = ans.size() - 1;
        if (sKey == 1) {
            return;
        }
        while (sKey > 1) {
            if (parentKey(sKey) > 0 && ans.get(parentKey(sKey)) < ans.get(sKey) ) {
                exchange(ans, parentKey(sKey), sKey);
            }
            sKey = parentKey(sKey);
        }
    }

    public static int leftChildKey(int root) {
        return root * 2;
    }
    public static int leftRightKey(int root) {
        return root * 2 + 1;
    }
    public static int parentKey(int root) {
        return root / 2;
    }
    public static <E> void exchange (List<E> list, int index1, int index2 ) {
        E tmp = list.get(index1);
        list.set(index1, list.get(index2));
        list.set(index2, tmp);
    }

    /**
     * 找最大值。
     * @param args
     */
    public static void main(String[] args) {
        Integer[] case1 = new Integer[]{1,2,23,4,5,13,1, 10};
        List<Integer> ints = Arrays.asList(case1).stream().collect(Collectors.toList());

        List<Integer> integers = HeapSortDemo(ints);

        for (int i = 1; i < integers.size(); i ++) {
            System.out.println(integers.get(i) + ",");
        }
    }
}
