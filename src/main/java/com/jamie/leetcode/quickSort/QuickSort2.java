package com.jamie.leetcode.quickSort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @PackageName: com.jamie.leetcode.quickSort
 * @ClassName: QuickSort2
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/16 5:02 下午
 */
public class QuickSort2 {

    public static List<Integer> quickSort2(int[] list) {
        quickSort(list, 0, list.length - 1);
        return Arrays.stream(list).boxed().collect(Collectors.toList());
    }

    public static void quickSort(int[] list, int left, int right) {
        if (left < right) {
            int start = left;
            int end = right;
            int tmp = list[start];
            while (start < end) {
                while (start < end && list[end] >= tmp) {
                    end--;
                }
                list[start] = list[end];
                while (start < end && list[start] <= tmp) {
                    start++;
                }
                list[end] = list[start];
            }
            list[start] = tmp;
            quickSort(list, left, start - 1);
            quickSort(list, start + 1, right);
        }
    }


    public static void main(String[] args) {
        int[] test2 = new int[]{2,32,21,32323,4,5,6,10,3,222,9};
        System.out.println(quickSort2(test2));
    }
}
