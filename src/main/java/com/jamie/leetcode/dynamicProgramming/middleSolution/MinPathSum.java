package com.jamie.leetcode.dynamicProgramming.middleSolution;

import java.util.*;

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
    public static int calculateMinimumHP2(int[][] dungeon) {
        int n = dungeon.length;
        int m = dungeon[0].length;

        // dp[i][j] 表示走到第i行j列，需要的最少血量。第一位表示历史最低血量，第二位表示当前血量
        int[][] dp = new int[n][m];

        for(int i = 0; i < n; i ++ ) {
            for (int j = 0; j < m; j ++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (i == 0 ) {
                    if (dungeon[i][j] >= 0) {
                        dp[0][j] = dp[0][j - 1];
                    } else {
                        dp[0][j] = dp[0][j - 1] + dungeon[0][j];
                    }
                }
                if (j == 0) {
                    if (dungeon[i][j] >= 0) {
                        dp[i][0] = dp[i - 1][0];
                    } else {
                        dp[i][0] = dp[i - 1][0]  + dungeon[i][0];
                    }
                }
                if (j >= 1 && i >= 1) {
                    if (dungeon[i][j] >= 0) {
                        Math.max(dp[i][j - 1], dp[i - 1][j]);

                    } else {
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]) + dungeon[i][j];
                    }
                }

            }
        }
        return dp[n - 1][m - 1] * (-1);
    }

    public static int calculateMinimumHP(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // 备忘录中都初始化为 -1
        memo = new int[m][n];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        return dp(grid, 0, 0);
    }

    // 备忘录，消除重叠子问题
    public static int[][] memo;

    /* 定义：从 (i, j) 到达右下角，需要的初始血量至少是多少 */
    public static int dp(int[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;
        // base case
        if (i == m - 1 && j == n - 1) {
            return grid[i][j] >= 0 ? 1 : -grid[i][j] + 1;
        }
        if (i == m || j == n) {
            return Integer.MAX_VALUE;
        }
        // 避免重复计算
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        // 状态转移逻辑
        int res = Math.min(
                dp(grid, i, j + 1),
                dp(grid, i + 1, j)
        ) - grid[i][j];
        // 骑士的生命值至少为 1
        memo[i][j] = res <= 0 ? 1 : res;

        return memo[i][j];
    }

    int[][] nums;
    public int findRotateSteps(String ring, String key) {
        Map<Character, List<Integer>> cache = new HashMap<>();
        for (int i = 0; i < ring.length(); i++) {
            char c = ring.charAt(i);
            cache.putIfAbsent(c, new ArrayList<>());
            cache.get(c).add(i);
        }
        int index = 0;
        nums = new int[ring.length()][key.length()];
        for (int i = 0; i < nums.length; i++) {
            Arrays.fill(nums[i], -1);
        }
        return minStep(cache, ring.length(), index, 0, key) + key.length();
    }

    private int minStep(Map<Character, List<Integer>> cache, int ringLength, int index, int salt, String key) {

        if (index == key.length()) {
            return 0;
        }
        if (nums[salt][index] != -1) {
            return nums[salt][index];
        }
        int min = Integer.MAX_VALUE;
        char c = key.charAt(index);
        List<Integer> list = cache.get(c);
        for (int i : list) {
            int maxValue = Math.max(i, salt);
            int minValue = Math.min(i, salt);
            int curStep = Math.min(maxValue - minValue, ringLength - maxValue + minValue);
            min = Math.min(min, curStep + minStep(cache, ringLength, index + 1, i, key));
        }
        nums[salt][index] = min;
        return min;
    }

    /**
     * K 站中转内最便宜的航班
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    // 哈希表记录每个点的入度
    // to -> [from, price]
    HashMap<Integer, List<int[]>> indegree;
    int src, dst;
    // 备忘录
    int[][] memo3;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 将中转站个数转化成边的条数
        k++;
        this.src = src;
        this.dst = dst;
        // 初始化备忘录，全部填一个特殊值
        memo3 = new int[n][k + 1];
        for (int[] row : memo3) {
            Arrays.fill(row, -888);
        }

        indegree = new HashMap<>();
        for (int[] f : flights) {
            int from = f[0];
            int to = f[1];
            int price = f[2];
            // 记录谁指向该节点，以及之间的权重
            indegree.putIfAbsent(to, new LinkedList<>());
            indegree.get(to).add(new int[] {from, price});
        }
        return dp(dst, k);
    }

    // 定义：从 src 出发，k 步之内到达 s 的最短路径权重
    public int dp(int s, int k) {
        // base case
        if (s == src) {
            return 0;
        }
        if (k == 0) {
            return -1;
        }
        // 查备忘录，防止冗余计算
        if (memo3[s][k] != -888) {
            return memo3[s][k];
        }
        // 初始化为最大值，方便等会取最小值
        int res = Integer.MAX_VALUE;
        if (indegree.containsKey(s)) {
            // 当 s 有入度节点时，分解为子问题
            for (int[] v : indegree.get(s)) {
                int from = v[0];
                int price = v[1];
                // 从 src 到达相邻的入度节点所需的最短路径权重
                int subProblem = dp(from, k - 1);
                // 跳过无解的情况
                if (subProblem != -1) {
                    res = Math.min(res, subProblem + price);
                }
            }
        }
        // 存入备忘录
        memo3[s][k] = res == Integer.MAX_VALUE ? -1 : res;
        return  memo3[s][k];
    }



    public static void main(String[] args) {
        int[] case1 = new int[]{-2,-3,3};
        int[] case2 = new int[]{-5,-10,1};
        int[] case3 = new int[]{10,30,-5};
        int[][] grid = new int[][]{case1, case2, case3};

        System.out.println(calculateMinimumHP(grid));
    }
}
