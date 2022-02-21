package com.jamie.leetcode.quickSort;

import java.util.Arrays;

/**
 * @PackageName: com.jamie.leetcode.quickSort
 * @ClassName: QuickSort
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/2/16 11:44 上午
 */
public class QuickSort {

    private static int min;
    private static int max;
    public static void main(String[] args) {
        int[] testArr = new int[]{-99,120,10,3,22,9,9,22,1,33,-2,100,39,1};
        min = 0;
        max = testArr.length - 1;
        quickSort(testArr, 0, testArr.length - 1);
        System.out.println(Arrays.toString(testArr));
    }


    public static void quickSort(int[] arr, int left, int right) {

        if (left >= right) {
            return;
        }
        if (left < min || right > max) {
            return;
        }
        int tmp = arr[left];
        int start = left;
        int end = right;

        while ( start < end) {
            while (arr[end] > tmp && end > start) {
                end --;
            }
            while (arr[start] <= tmp && end > start) {
                start ++;
            }
            exchangeValue(arr, end, start);
        }
        arr[left] = arr[start];
        arr[start] = tmp;

        quickSort(arr, left, start - 1);
        quickSort(arr, start + 1, right);
    }

    public static void exchangeValue(int[] arr, int val1, int val2) {
        int tmp = arr[val1];
        arr[val1] = arr[val2];
        arr[val2] = tmp;
    }



}
