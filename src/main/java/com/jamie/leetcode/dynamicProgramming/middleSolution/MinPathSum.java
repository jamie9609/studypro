package com.jamie.leetcode.dynamicProgramming.middleSolution;

/**
 * @PackageName: com.jamie.leetcode.dynamicProgramming.middleSolution
 * @ClassName: MinPathSum
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/13 9:39 上午
 */
public class MinPathSum {

    /**
     * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
     * 说明：每次只能向下或者向右移动一步。
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // dp[i][j] 表示在第i行j列，路径总和最小的值。
        int[][] dp = new int[n][m];

        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < m; j ++) {
                if (i == 0) {
                    int tmp2 = 0;
                    for (int k = 0; k <= j; k ++ ) {
                        tmp2 += grid[0][k];
                    }
                    dp[0][j] = tmp2;
                }
                if (j == 0) {
                    int tmp = 0;
                    for (int k = 0; k <= i; k ++ ) {
                        tmp += grid[k][0];
                    }
                    dp[i][0] = tmp;
                }
                if (j >= 1 && i >= 1) {
                    dp[i][j] = Math.min(dp[i][ j - 1], dp[i - 1][j]) + grid[i][j];
                }
            }
        }

        return dp[n - 1][m - 1];
    }

    /**
     * 地下城游戏
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;

        // dp[i][j] 表示走到第i行j列，需要的初始血量。
        int[][] dp = new int[n][m];
        return 0;
    }

    public static void main(String[] args) {
        int[] case1 = new int[]{1,3,1};
        int[] case2 = new int[]{1,5,1};
        int[] case3 = new int[]{4,2,1};
        int[][] grid = new int[][]{case1, case2, case3};

        System.out.println(minPathSum(grid));
    }
}
