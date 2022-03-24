package com.jamie.leetcode.quickSort;

import java.util.ArrayList;
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


    /**
     * 给你一个整数数组 nums，请你将该数组升序排列。
     * @param nums
     * @return
     */
    public static int[] sortArray(int[] nums) {
        return helpers(nums, 0, nums.length -1);
    }

    public static int[] helpers(int[] nums, int start, int end) {
        if (start > end) {
            return nums;
        }

        int tmpFlag = nums[start];
        int left = start;
        int right = end;
        while (left < right) {
            while (nums[right] > tmpFlag) {
                right --;
            }
            nums[left] = nums[right];

            while (left < right && nums[left] <= tmpFlag) {
                left ++;
            }
            nums[right] = nums[left];
        }
        nums[left] = tmpFlag;

        helpers(nums, start, left - 1);
        helpers(nums, left + 1, end);
        return nums;
    }

    /*public void exchange2(int[] nums, int index1, int index2) {
        int num = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = num;
    }*/


    /**
     * 给你一个整数数组 nums ，按要求返回一个新数组counts 。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于nums[i] 的元素的数量。

     * 链接：https://leetcode-cn.com/problems/count-of-smaller-numbers-after-self
     * @param nums
     * @return
     */
    // 归并排序所用的辅助数组
    private Pair[] temp;
    // 记录每个元素后面比自己小的元素个数
    private int[] count;
    // 归并排序的解法
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        temp = new Pair[n];
        Pair[] arr = new Pair[n];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        sort(arr, 0, n - 1);

        for (int c : count) {
            res.add(c);
        }
        return res;
    }

    private void sort(Pair[] arr, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) /2;
        sort(arr, lo ,mid);
        sort(arr, mid + 1 ,hi);
        merge(arr, lo, mid, hi);
    }

    private void merge(Pair[] arr, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = arr[i];
        }
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                arr[p] = temp[j++];
            } else if (j == hi + 1) {
                arr[p] = temp[i++];
                count[arr[p].index] += j - mid - 1;
            } else if (temp[i].val > temp[j].val) {
                arr[p] = temp[j++];
            } else {
                arr[p] = temp[i++];
                count[arr[p].index] += j - mid - 1;
            }
        }
    }

    class Pair {
        // 记录原数组中的值，及对应的索引
        int val;
        int index;
        public Pair (int val, int index){
            this.val = val;
            this.index = index;
        }
    }


    /**
     * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {
        tempNums = new int[nums.length];
        sort(nums, 0, nums.length - 1);
        return countNum;
    }
    private int[] tempNums;
    // 归并排序
    private void sort(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    // 记录「翻转对」的个数
    private int countNum = 0;
    private void merge(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            tempNums[i] = nums[i];
        }

        // 进行效率优化，维护左闭右开区间 [mid+1, end) 中的元素乘 2 小于 nums[i]
        // 为什么 end 是开区间？因为这样的话可以保证初始区间 [mid+1, mid+1) 是一个空区间
        int end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            // nums 中的元素可能较大，乘 2 可能溢出，所以转化成 long
            while (end <= hi && (long)nums[i] > (long)nums[end] * 2) {
                end++;
            }
            countNum += end - (mid + 1);
        }

        // 数组双指针技巧，合并两个有序数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                nums[p] = tempNums[j++];
            } else if (j == hi + 1) {
                nums[p] = tempNums[i++];
            } else if (tempNums[i] > tempNums[j]) {
                nums[p] = tempNums[j++];
            } else {
                nums[p] = tempNums[i++];
            }
        }
    }

    public static void main(String[] args) {
        int[] test2 = new int[]{2,32,21,4,5,99,819,21,0,12,-1};
        System.out.println(quickSort2(test2));
        System.out.println(quickHeatSort2(test2));
        System.out.println(sortArray(test2));
    }
}
