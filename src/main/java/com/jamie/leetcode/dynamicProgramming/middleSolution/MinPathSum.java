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

    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     * @param s
     * @param p
     * @return
     */
    HashMap memo4;
    public boolean isMatch(String s, String p) {
        memo4 = new HashMap<String, Boolean>();
        return dp(s, 0, p, 0);
    }

    public boolean dp(String s, int i, String p, int j) {
        int m = s.length(), n = p.length();
        // base case
        if (j == n) {
            return i == m;
        }
        if (i == m) {
            if ((n - j) % 2 == 1) {
                return false;
            }
            for ( ; j + 1 < n; j += 2) {
                if (p.charAt(j + 1) != '*') {
                    return false;
                }
            }
            return true;
        }

        // 记录状态 (i, j)，消除重叠子问题
        String key = i + "," + j;
        if (memo4.containsKey(key)) {
            return (boolean) memo4.get(key);
        }

        boolean res = false;

        if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                // 通配符匹配0 次，或者多次；
                res = dp(s, i, p, j + 2)
                        || dp(s, i + 1, p, j);
            } else {
                //正常往后走
                res = dp(s, i + 1, p, j + 1);
            }
        } else {
            if (j < n - 1 && p.charAt(j + 1) == '*') {
                res = dp(s, i, p, j + 2);
            } else {
                res = false;
            }
        }
        // 将当前结果记入备忘录
        memo4.put(key, res);
        return res;
    }


    /**
     * 给你 k 枚相同的鸡蛋，并可以使用一栋从第 1 层到第 n 层共有 n 层楼的建筑。
     * 已知存在楼层 f ，满足 0 <= f <= n ，任何从 高于 f 的楼层落下的鸡蛋都会碎，从 f 楼层或比它低的楼层落下的鸡蛋都不会破。
     * 每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 x 扔下（满足 1 <= x <= n）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 重复使用 这枚鸡蛋。
     * 请你计算并返回要确定 f 确切的值 的 最小操作次数 是多少？
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/super-egg-drop
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param k
     * @param n
     * @return
     */
    // 数组规模太大，超出时间限制。
    public static int superEggDrop3(int k, int n) {
        //dp[i][j] 表示i个鸡蛋，j层楼，至少需要操作的次数
        int[][] dp = new int[k + 1][n + 1];

        for ( int i = 1; i < k + 1; i ++ ) {
            dp[i][1] = 1;
        }
        for (int j = 1; j < n + 1; j ++) {
            dp[1][j] = j;
        }
        if (n > 1 && k > 1) {
            for (int i = 2; i < k + 1; i ++ ) {
                for (int j = 2; j < n + 1; j ++) {
                    int tmp = Integer.MAX_VALUE;
                    for (int p = 1; p < j ; p ++ ) {
                        tmp = Math.min(tmp, Math.max(dp[i - 1][p - 1],dp[i][j - p]) + 1);
                    }
                    dp[i][j] = tmp;
                }
            }
        }
        return dp[k][n];
    }

    static int[][] memo5 = null;

    public static int superEggDrop(int k, int n) {
        memo5 = new int[k + 1][n +1];
        return helper(k, n);
    }

    public static int helper(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        if (memo5[k][n] > 0) {
            return memo5[k][n];
        }

        int tmpValue = Integer.MAX_VALUE;
        for (int j = 1; j < n + 1; j ++) {
            tmpValue = Math.min(tmpValue, Math.max(helper(k - 1, j - 1), helper(k, n - j)) + 1);
        }

        memo5[k][n] = tmpValue;
        return tmpValue;
    }


    
    public static void main(String[] args) {
        /*int[] case1 = new int[]{-2,-3,3};
        int[] case2 = new int[]{-5,-10,1};
        int[] case3 = new int[]{10,30,-5};
        int[][] grid = new int[][]{case1, case2, case3};*/

        //System.out.println(calculateMinimumHP(grid));

        System.out.println(superEggDrop(1,2));
    }
}
