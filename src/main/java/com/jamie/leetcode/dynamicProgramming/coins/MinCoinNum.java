package com.jamie.leetcode.dynamicProgramming.coins;

/**
 * @PackageName: com.jamie.leetcode.dynamicProgramming.coins
 * @ClassName: minCoinNum
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 9:04 下午
 */
public class MinCoinNum {

    public static int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }

        if (amount < 0) {
            return -1;
        }
        int[] ans = new int[amount + 1];
        for (int i = 0; i < ans.length; i ++) {
            ans[i] = -1;
        }
        for (int coin : coins) {
            if (coin <= amount) {
                ans[coin] = 1;
            }
        }
        return dp(coins, amount, ans);
    }

    public static int dp(int[] coins, int amount, int[] ans) {
        for (int i = 1; i < ans.length; i ++) {
            if (ans[i] > 0) {
                continue;
            }
            int minNum = Integer.MAX_VALUE;
            for (int coin : coins) {
                if ((i > coin) && ans[i - coin] > 0 ) {
                    minNum  = Math.min(minNum, ans[i - coin] + 1);
                }
            }
            if (minNum < Integer.MAX_VALUE) {
                ans[i] = minNum;
            }
        }

        return ans[amount];
    }

    public static void main(String[] args) {
        int[] coins = {5};

        System.out.println(coinChange(coins, 3));

    }

}
