package com.jamie.leetcode.dynamicProgramming.dp;

/**
 * @PackageName: com.jamie.leetcode.dynamicProgramming.dp
 * @ClassName: PackageProblem
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/15 2:28 下午
 */
public class PackageProblem {

    /**
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     * @param nums
     * @return
     */
    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int item : nums) {
            sum += item;
        }
        int packageSum = sum / 2;
        if (packageSum * 2 != sum) {
            return false;
        }
        //dp[i][j] 表示将前i件物品装入容量为j的背包之中，背包最多装多少。
        int[][] dp = new int[nums.length][packageSum + 1];

        for (int i = 0; i < nums.length; i ++) {
            for (int j = 0; j < packageSum + 1; j ++) {
                if (i == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                if( j - nums[i - 1] < 0 ) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = Math.max(dp[i - 1][j],  dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
            }
        }
        return dp[nums.length - 1][packageSum] == packageSum;
    }


    public static void main(String[] args) {
        System.out.println(canPartition(new int[]{1,2,3,5}));
    }

}
