package com.jamie.leetcode.arrayStudy;

/**
 * 数组的归并算法
 * @PackageName: com.jamie.leetcode.arrayStudy
 * @ClassName: MergingAlgorithm
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/25 11:10 上午
 */
public class MergingAlgorithm {

    /**
     * 给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含lower和upper）之内的 区间和的个数 。
     * 区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)。
     * 链接：https://leetcode-cn.com/problems/count-of-range-sum
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    private int lower, upper;
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;

        long[] preSum = new long[nums.length + 1];

        for(int i = 0; i < nums.length; i ++) {
            preSum[i + 1] = preSum[i] + (long)nums[i];
        }
        sort(preSum);
        return count;
    }
    private long[] temp;
    private void sort(long[] preSum) {
        temp = new long[preSum.length];
        // 二分归并算法排序
        sort(preSum, 0, preSum.length -1);

    }
    private void sort(long[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);
        merge(nums, mid, lo, hi);
    }
    private int count = 0;
    private void merge(long[] nums, int mid, int lo, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp[i] = nums[i];
        }

        int start = mid + 1, end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            // 如果 nums[i] 对应的区间是 [start, end)，
            // 那么 nums[i+1] 对应的区间一定会整体右移，类似滑动窗口
            while (start <= hi && nums[start] - nums[i] < lower) {
                start++;
            }
            while (end <= hi && nums[end] - nums[i] <= upper) {
                end++;
            }
            count += end - start;
        }

        int i = lo;
        int j = mid + 1;
        for (int p = lo; p <= hi; p ++) {
            if (i == mid + 1){
                nums[p] = temp[j ++];
            } else if (j == hi + 1) {
                nums[p] = temp[i ++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j ++];
            } else {
                nums[p] = temp[i ++];
            }

        }

    }

}
