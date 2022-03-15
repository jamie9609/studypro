package com.jamie.leetcode.backTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.backTracking
 * @ClassName: BalloonGetMaxCoins
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/15 10:55 上午
 */
public class BalloonGetMaxCoins {
    /**
     * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
     * 求所能获得硬币的最大数量。
     * @param nums
     * @return
     */
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] points = new int[n + 2];
        for (int i = 1; i <= n; i++) {
            points[i] = nums[i - 1];
        }
        points[0] = 1;
        points[n + 1] = 1;
        int[][] dp = new int[n + 2][n + 2];

        for (int i = n; i >= 0; i -- ) {
            for (int j = i + 1; j < n + 2; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + points[k] * points[i] * points[j]);
                }
            }
        }
        return dp[0][n + 1];
    }

    /*
    全排列方法，取值
    public static int maxCoins(int[] nums) {
        int res = 0;
        int maxCoins = Integer.MIN_VALUE;
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(nums, res, maxCoins, track);
        return maxCoins;
    }

    public static void backTrack(int[] nums, int res, int maxCoins ,LinkedList<Integer> track) {
        if (track.size() == nums.length) {
            maxCoins = Math.max(res, maxCoins);
        }
        for (int i = 0; i < nums.length; i ++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            if (i == 0) {
                res = res + nums[i] * nums[i + 1];
            } else if (i == nums.length - 1) {
                res = res + nums[i] * nums[i - 1];
            } else {
                res = res + nums[i] * nums[i - 1] * nums[i + 1];
            }
            backTrack(nums, res, maxCoins, track);
            track.removeLast();
        }
    }*/

    /**
     * 输入一组数字，返回他们的全排列
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        backTrack(nums, track, res);
        return res;
    }

    public static void backTrack(int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i ++) {
            if (track.contains(nums[i])) {
                continue;
            }
            track.add(nums[i]);
            backTrack(nums, track, res);
            track.removeLast();
        }
    }

    public static void main(String[] args) {
        //System.out.println(permute(new int[]{1,2,3}));
        System.out.println(maxCoins(new int[]{1,5}));
    }
}
