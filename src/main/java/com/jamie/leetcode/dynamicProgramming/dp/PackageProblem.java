package com.jamie.leetcode.dynamicProgramming.dp;

import java.util.Arrays;
import java.util.HashMap;

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

    /**
     * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
     * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
     * 假设每一种面额的硬币有无限个。
     * 题目数据保证结果符合 32 位带符号整数。
     * @param amount
     * @param coins
     * @return
     */
    public static int change(int amount, int[] coins) {
        //dp[i][j] 表示只用前i种硬币，凑出j金额，总共有几种方法
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i ++) {
            for (int j = 0; j <= amount; j ++) {
                if (i == 0 && j != 0) {
                    dp[i][j] = 0;
                    continue;
                }
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }
                if (j - coins[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
            }
        }
        return dp[coins.length][amount];
    }

    /**
     * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
     * @param nums
     * @param k
     * @return
     */
    // 从数字的视角，去装到桶里。
    public static boolean canPartitionKSubsets2(int[] nums, int k) {
        if (k > nums.length) {
            return false;
        }
        int sum = 0;
        for (int item : nums) {
            sum += item;
        }
        if (sum % k != 0) {
            return false;
        }

        int[] bucket = new int[k];
        int target = sum / k;

        Arrays.sort(nums);
        for (int i = 0, j = nums.length - 1; i < j; i++, j--) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return helpers3(nums, 0, target, bucket);
    }

    public static boolean helpers3(int[] nums, int index, int target, int[] bucket) {
        if (index == nums.length) {
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != target) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < bucket.length; i ++) {
            if (nums[index] + bucket[i] > target) {
                continue;
            }
            bucket[i] += nums[index];
            if (helpers3(nums, index + 1, target, bucket)) {
                return true;
            }
            bucket[i] -= nums[index];
        }
        return false;
    }


    //从桶的视角，去选择数字添加
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) {
            return false;
        }
        int sum = 0;
        for (int item : nums) {
            sum += item;
        }
        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;
        int used = 0;
        return helpers4(k, 0, nums, 0, used, target);
    }

    public static HashMap<Integer, Boolean> memo2 = new HashMap<>();


    public static boolean helpers4(int k, int bucket, int[] nums, int start, int used, int target) {
        if (k == 0) {
            return true;
        }

        if (bucket == target) {
            boolean res = helpers4(k - 1, 0, nums, 0, used, target);
            // 缓存结果
            memo2.put(used, res);
            return res;
        }

        if (memo2.containsKey(used)) {
            return memo2.get(used);
        }

        for (int i = start; i < nums.length; i ++ ) {
            if (((used >> i) & 1) == 1) {
                continue;
            }
            if (bucket + nums[i] > target) {
                continue;
            }
            used |= 1 << i;
            bucket += nums[i];
            if (helpers4(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            used ^= 1 << i;
            bucket -= nums[i];
        }
        return false;
    }



    public static void main(String[] args) {
        //System.out.println(canPartition(new int[]{1,2,3,5}));

        //System.out.println(change(5, new int[]{1,2,5}));

        System.out.println(canPartitionKSubsets(new int[]{2,2,2,2,3,4,5}, 4));

    }

}
