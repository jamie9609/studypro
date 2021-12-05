package com.jamie.leetcode.arrayStudy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 查分数组和前缀和数组。
 * @PackageName: com.jamie.leetcode.arrayStudy
 * @ClassName: ArraySkillDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/4 3:02 下午
 */
public class ArraySkillDemo {
    // 前缀和 的使用
    class NumArray {
        // 前缀和数组
        private int[] preSum;

        /* 输入一个数组，构造前缀和 */
        public NumArray(int[] nums) {
            // preSum[0] = 0，便于计算累加和
            preSum = new int[nums.length + 1];
            // 计算 nums 的累加和
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }

        /* 查询闭区间 [left, right] 的累加和 */
        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }

    /**
     * 前缀和，
     */
    class NumMatrix {
        // preSum[i][j] 记录矩阵 [0, 0, i, j] 的元素和
        private int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            if (m == 0 || n == 0) return;
            preSum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // 计算每个矩阵 [0, 0, i, j] 的元素和
                    preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] + matrix[i - 1][j - 1] - preSum[i-1][j-1];
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2 + 1][col2 + 1] + preSum[row1][col1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1];
        }
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum1(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n + 1];
        res[0] = 0;
        for (int i = 0; i < n; i ++) {
            res[i + 1] = res[i] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = i; j < n; j ++) {
                if (res[j + 1] - res[i] == k ) {
                    count ++;
                }

            }
        }
        return count;
    }

    /**
     * 用map来优化，省去外层的for循环
     * @param nums
     * @param k
     * @return
     */
    static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // map：前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer>
                preSum = new HashMap<>();
        // base case
        preSum.put(0, 1);

        int res = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            // 这是我们想找的前缀和 nums[0..j]
            int sum0_j = sum0_i - k;
            // 如果前面有这个前缀和，则直接更新答案
            if (preSum.containsKey(sum0_j))
                res += preSum.get(sum0_j);
            // 把前缀和 nums[0..i] 加入并记录出现次数
            preSum.put(sum0_i,
                    preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return res;
    }

    /**
     * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
     *
     * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
     *
     * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
     * @param bookings
     * @param n
     * @return
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] flight = new int[n];
        Arrays.fill(flight, 0);
        Difference difference = new Difference(flight);

        for (int i = 0; i < bookings.length; i ++ ) {
            difference.increment(bookings[i][0] - 1,  bookings[i][1] - 1,  bookings[i][2]);
        }
        return difference.result();
    }
    public static class Difference {
        int[] difference;
        public Difference(int[] nums){
            difference = new int[nums.length + 1];
            Arrays.fill(difference, 0);
            difference[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                difference[i] = nums[i] - nums[i - 1];
            }
        }

        /* 给闭区间 [i,j] 增加 val（可以是负数）*/
        public void increment(int i, int j, int val) {
            difference[i] += val;
            if (j < difference.length -1) {
                difference[j + 1] -= val;
            }
        }

        public int[] result() {
            int[] res = new int[difference.length];
            res[0] = difference[0];
            for (int i = 1; i < difference.length; i++) {
                res[i] = res[i - 1] + difference[i];
            }
            return res;
        }

    }


    /**
     * 你是一个开公交车的司机，公交车的最大载客量为 capacity，沿途要经过若干车站，给你一份乘客行程表 int[][] trips，其中 trips[i] = [num, start, end] 代表着有 num 个旅客要从站点 start 上车，到站点 end 下车，请你计算是否能够一次把所有旅客运送完毕（不能超过最大载客量 capacity）。
     * @param trips
     * @param capacity
     * @return
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        // 最多有 1000 个车站
        int[] nums = new int[1001];
        Difference difference = new Difference(nums);

        for (int i = 0; i < trips.length; i ++) {
            difference.increment(trips[i][1], trips[i][2] - 1, trips[i][0]);
        }
        int[] result = difference.result();
        for (int item :result) {
            System.out.println(item);
            if (item > capacity) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[] int1 = {9,0,1};
        int[] int2 = {3,3,7};
        int[][] res = new int[][]{int1,int2};

        System.out.println( carPooling(res, 3));
    }
}
