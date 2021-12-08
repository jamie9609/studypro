package com.jamie.leetcode.dynamicProgramming.easySolution;

import java.util.Arrays;

/**
 * @PackageName: com.jamie.leetcode.dynamicProgramming.easySolution
 * @ClassName: EasyDemo1
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/7 7:55 下午
 */
public class EasyDemo1 {

    /**
     * 斐波拉契数列
     * @param n
     * @return
     */
    public int fib(int n) {
        int[] memo =  new int[n + 1];
        return helper(memo, n);
    }
    public int helper(int[] memo, int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = helper(memo, n - 1) + helper(memo, n - 2);
        return memo[n];
    }

    /**
     * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
     * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
     * 你可以认为每种硬币的数量是无限的。
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];

        if (amount == 0) {
            return 0;
        }

        Arrays.fill(dp, -1);
        dp[0] = 0;

        for (int item : coins) {
            if (item <= amount) {
                dp[item] = 1;
            }
        }

        for (int i = 2; i <= amount; i ++) {
            int tmpValue = Integer.MAX_VALUE;
            for (int item : coins) {
                if ( i - item >= 0 &&  dp[i - item] >=0 ) {
                    tmpValue = Math.min(tmpValue, dp[i - item]);
                }
            }
            dp[i] = tmpValue == Integer.MAX_VALUE ? -1 : tmpValue + 1;
        }
        return dp[amount];
    }

    /**
     * 给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。
     * 子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        /**
         * dp[i] 表示以nums[i]结尾的最长子序列长度。
         */
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i ++) {
            for (int j = 0; j < i; j ++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res = Math.max(res, dp[i]);
        }
        return res;
    }




    public static void main(String[] args) {
        int[] case1 = {10,9,2,5,3,7,101,18};
        //System.out.println(coinChange(case1, 4));
        System.out.println(lengthOfLIS(case1));
    }

}
