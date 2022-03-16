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

    //分治递归快排
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

    //小顶堆快排（从大到小）
    public static List<Integer> quickHeatSort2(int[] list) {
        // 构建小顶堆
        int n = list.length;
        if ( n == 1) {
            return Arrays.stream(list).boxed().collect(Collectors.toList());
        }

        for (int i = getParentIndex(n - 1); i >= 0; i --) {
            upFloat(list, i, n - 1);
        }

        for(int i = 0; i < n - 1; i ++) {
            exchange(list, 0, n - 1 - i);
            upFloat(list, 0, n - 2 - i);
        }
        return Arrays.stream(list).boxed().collect(Collectors.toList());
    }


    public static void upFloat (int[] list, int index, int end) {
        int leftChildIndex = getLeftChildIndex(index);
        int rightChildIndex = getRightChildIndex(index);

        if (rightChildIndex > end && leftChildIndex > end) {
            return;
        }

        int minChildIndex = 0;

        if (rightChildIndex > end) {
            minChildIndex = leftChildIndex;
        } else if ( list[leftChildIndex] <= list[rightChildIndex] ){
            minChildIndex = leftChildIndex;
        } else {
            minChildIndex = rightChildIndex;
        }

        if (list[index] < list[minChildIndex]) {
            return;
        }
        exchange(list, index, minChildIndex);
        upFloat(list, minChildIndex, end);
    }


    public static void exchange(int[] list, int oldIndex, int newIndex) {
        int tmp = list[oldIndex];
        list[oldIndex] = list[newIndex];
        list[newIndex] = tmp;
    }

    public static int getParentIndex(int n) {
        return (n - 1) / 2;
    }

    public static int getLeftChildIndex(int n) {
        return 2 * n + 1;
    }

    public static int getRightChildIndex(int n) {
        return 2 * n + 2;
    }


    public static void main(String[] args) {
        int[] test2 = new int[]{2,32,21,4,5,99,819,21,0,12,-1};
        System.out.println(quickSort2(test2));
        System.out.println(quickHeatSort2(test2));
    }
}
