package com.jamie.leetcode.subsequenceStudy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;

/**
 * @PackageName: com.jamie.leetcode.subsequenceStudy
 * @ClassName: editDistance
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/9 2:22 下午
 */
public class DynamicProgram {
    /**
     * 给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
     *
     * 你可以对一个单词进行如下三种操作：
     *
     * 插入一个字符
     * 删除一个字符
     * 替换一个字符
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        //返回 word1 和 word2 的最小编辑距离
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;

        for (int i = 1; i <= m; i ++) {
            for (int j = 1; j <=n; j ++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    //对“dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。”的说明 更容易理解些： dp[i-1][j-1]到dp[i][j]需要进行替换操作，dp[i-1][j]到d[i][j]需要进行删除操作，dp[i][j-1] 到d[i][j]需要进行添加操作。
                    //dp[i - 1][j] + 1 表示word1字符串在i-1的位置删除一个新字符；
                    //dp[i - 1][j - 1] + 1 表示word1字符串在i-1的位置更换一个新字符；
                    // dp[i][j - 1] + 1 表示word2字符串在i-1的位置删除一个新字符。
                    // word1的插入和word2的删除是等价的。
                    dp[i][j] = min(dp[i - 1][j] + 1,dp[i - 1][j - 1] + 1, dp[i][j - 1] + 1);
                }
            }
        }
        return dp[m][n];

    }

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


    /**
     *
     * 俄罗斯套娃问题：https://labuladong.gitee.io/algo/3/24/75/
     *
     * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
     * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
     *
     * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
     *
     *  解法：先对宽度 w 进行升序排序，如果遇到 w 相同的情况，则按照高度 h 降序排序。之后把所有的 h 作为一个数组，在这个数组上计算 LIS(最长子序列) 的长度就是答案。
     *  // 如果w相同，把h按降序排序是因为：相同的w，只能有一个。否则套不进去。
     * @param envelopes
     * @return
     */
    public static int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        Arrays.sort(envelopes, (a, b) -> {return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];});

        int[] height = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i ++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS2(height);
    }

    /**
     * 计算最长上升子序列
     * @param nums
     * @return
     */
    public static int lengthOfLIS2(int[] nums) {
        int n = nums.length;
        //dp[i] 以nums[i]结尾的最长上升子序列个数。
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i = 0; i < nums.length; i ++) {

            for (int j = 0; j < i; j ++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < dp.length; i ++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }


    /**
     * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        //dp[i] 以nums[i]结尾的连续子序列的和。
        int[] dp = new int[n];
        dp[0] = nums[0];

        for (int i = 1; i < n; i ++) {

            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i ++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }


}
