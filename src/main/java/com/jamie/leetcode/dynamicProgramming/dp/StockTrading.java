package com.jamie.leetcode.dynamicProgramming.dp;

/**
 * @PackageName: com.jamie.leetcode.dynamicProgramming.middleSolution
 * @ClassName: StockTrading
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/16 4:36 下午
 */
public class StockTrading {

    /**
     * 给定一个数组 prices ，它的第  i 个元素  prices[i] 表示一支给定股票第 i 天的价格。
     *
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int n = prices.length;
        // dp[i][0]表示在交易的第i天，不持有股票的最大利润；dp[i][1]表示在交易的第i天，持有股票的最大利润
        //只能交易一次
        int[][] dp = new int[n][2];
        dp[0][1] = - prices[0];
        for (int i = 1; i < n; i ++ ) {
            dp[i][1] = Math.max( dp[i - 1][1],  - prices[i]);
            dp[i][0] = Math.max( dp[i - 1][0], dp[i - 1][1] + prices[i] );
        }
        return dp[n - 1][0];
    }

    public static int maxProfitDemo2(int[] prices) {
        int n = prices.length;
        // dp[i][0]表示在交易的第i天，不持有股票的最大利润；dp[i][1]表示在交易的第i天，持有股票的最大利润
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < n; i ++) {
            dp[i][0] = Math.max(dp[i - 1][0] , dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1] , - prices[i]);
        }
        return dp[n - 1][0];
    }


    /**
     * 给定一个数组 prices ，其中  prices[i] 是一支给定股票第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        // dp[i][0]表示在交易的第i天，不持有股票的最大利润；dp[i][1]表示在交易的第i天，持有股票的最大利润
        int[][] dp = new int[n][2];
        dp[0][1] = - prices[0];
        for (int i = 1; i < n; i ++ ) {
            dp[i][1] = Math.max( dp[i - 1][1], dp[i - 1][0] - prices[i] );
            dp[i][0] = Math.max( dp[i - 1][0], dp[i - 1][1] + prices[i] );
        }
        return dp[n - 1][0];
    }

    /**
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        int maxK = 2;
        int n = prices.length;
        // dp[i][][0]表示在交易的第i天，不持有股票的最大利润；dp[i][1]表示在交易的第i天，持有股票的最大利润
        int[][][] dp = new int[n][3][2];
        for (int i = 0; i < n; i ++ ) {
            for (int k = maxK; k >= 1; k--) {
                if (i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][1] = Math.max( dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i] );
                dp[i][k][0] = Math.max( dp[i - 1][k][0], dp[i - 1][k][1] + prices[i] );
            }
        }
        return dp[n - 1][2][0];
    }


    public static int maxProfitDemo3(int[] prices) {
        int n = prices.length;
        int maxCount = 2;
        // dp[i][k][0]表示在交易的第i天，不持有股票的最大利润；dp[i][k][1]表示在交易的第i天，持有股票的最大利润
        int[][][] dp = new int[n][3][2];
        for (int i = 0; i < n; i ++ ) {
            for (int k = maxCount; k >= 1; k--) {
                if (i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
            }
        }
        return dp[n - 1][2][0];
    }


    /**
     * 给定一个整数数组  prices ，它的第 i 个元素  prices[i] 是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfit4(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int maxK = k;
        int n = prices.length;
        // dp[i][][0]表示在交易的第i天，不持有股票的最大利润；dp[i][1]表示在交易的第i天，持有股票的最大利润
        int[][][] dp = new int[n][maxK + 1][2];
        for (int i = 0; i < n; i ++ ) {
            for (int x = maxK; x >= 1; x--) {
                if (i == 0) {
                    dp[i][x][0] = 0;
                    dp[i][x][1] = -prices[i];
                    continue;
                }
                dp[i][x][1] = Math.max( dp[i - 1][x][1], dp[i - 1][x - 1][0] - prices[i] );
                dp[i][x][0] = Math.max( dp[i - 1][x][0], dp[i - 1][x][1] + prices[i] );
            }
        }
        return dp[n - 1][maxK][0];
    }

    public static int maxProfitDemo4(int k, int[] prices) {
        if ( prices.length == 0) {
            return 0;
        }
        int maxInt = k;
        int n = prices.length;
        int[][][] dp = new int[n][maxInt + 1][2];

        for (int i = 0; i < n; i ++ ) {
            for (int x = maxInt; x >=1; x --) {
                if (i == 0) {
                    dp[i][x][0] = 0;
                    dp[i][x][1] = -prices[i];
                    continue;
                }
                dp[i][x][1] =  Math.max(dp[i - 1][x][1], dp[i - 1][x - 1][0] - prices[i]);
                dp[i][x][0] =  Math.max(dp[i - 1][x][0], dp[i - 1][x ][1] + prices[i]);
            }
        }
        return dp[n - 1][maxInt][0];

    }

    /**
     * 给定一个整数数组，其中第  i  个元素代表了第  i  天的股票价格 。  
     *
     * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
     *
     * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
     * @param prices
     * @return
     */
    public static int maxProfit4(int[] prices) {
        int n = prices.length;
        // dp[i][0]表示在交易的第i天，不持有股票的最大利润；dp[i][1]表示在交易的第i天，持有股票的最大利润
        int[][] dp = new int[n][2];
        dp[0][1] = - prices[0];
        for (int i = 1; i < n; i ++ ) {
            dp[i][0] = Math.max( dp[i - 1][0], dp[i - 1][1] + prices[i]);
            if(i >= 2) {
                dp[i][1] = Math.max( dp[i - 1][1], dp[i - 2][0] - prices[i] );
            } else {
                dp[i][1] = Math.max( dp[i - 1][1], - prices[i] );
            }
        }
        return dp[n - 1][0];
    }

    public static int maxProfitDemo4(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];
        dp[0][1] = -prices[0];
        for (int i = 0; i < n; i ++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            if (i > 2) {
                dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[1]);
            } else {
                dp[i][1] = Math.max(dp[i - 1][1],- prices[1] );
            }
        }
        return dp[n - 1][0];
    }

    /**
     * 给定一个整数数组  prices，其中第  i  个元素代表了第  i  天的股票价格 ；整数  fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     * @param prices
     * @param fee
     * @return
     */
    public int maxProfit5(int[] prices, int fee) {
        int n = prices.length;
        // dp[i][0]表示在交易的第i天，不持有股票的最大利润；dp[i][1]表示在交易的第i天，持有股票的最大利润
        int[][] dp = new int[n][2];
        dp[0][1] = - prices[0];
        for (int i = 1; i < n; i ++ ) {
            dp[i][1] = Math.max( dp[i - 1][1], dp[i - 1][0] - prices[i] );
            dp[i][0] = Math.max( dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
        }
        return dp[n - 1][0];
    }


    public static void main(String[] args) {
        int[] case1 = {7, 1, 5, 3, 6, 4};
        //System.out.println(maxProfit(case1));
        //System.out.println(maxProfitDemo2(case1));
        System.out.println(maxProfitDemo3(case1));
        System.out.println(maxProfit4(2, case1));
        System.out.println(maxProfitDemo4(2, case1));
    }
}
