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

    public static void main(String[] args) {
        int[] case1 = {2};
        System.out.println(coinChange(case1, 4));
    }

}
