package com.jamie.leetcode.dynamicProgramming.dp;

/**
 * @PackageName: com.jamie.leetcode.dynamicProgramming.middleSolution
 * @ClassName: EggDrop
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/16 9:28 上午
 */
public class HardSolution1 {

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
        //用二分搜索代替线性搜索，否则超时
        /*int tmpValue = Integer.MAX_VALUE;
        for (int j = 1; j < n + 1; j ++) {
            tmpValue = Math.min(tmpValue, Math.max(helper(k - 1, j - 1), helper(k, n - j)) + 1);
        }*/
        int left = 1;
        int right = n;
        int res = Integer.MAX_VALUE;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int broken = helper(k - 1, mid - 1);
            int notBroken = helper(k, n - mid);
            if ( broken > notBroken ) {
                right  = mid - 1;
                res = Math.min(res, broken + 1) ;
            } else {
                left = mid + 1;
                res = Math.min(res, notBroken + 1) ;
            }
        }
        memo5[k][n] = res;
        return res;
    }

    /**
     * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
     *
     * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
     *
     * 求所能获得硬币的最大数量。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/burst-balloons
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] newNum = new int[nums.length + 2];
        newNum[0] = 1;
        newNum[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            newNum[i] = nums[i - 1];
        }
        int newN = newNum.length;
        // dp[i][j]表示刺破从i到j（开区间）的气球，能获得的最多硬币数量。
        int[][] dp = new int[newN + 1][newN + 1];
        //如果这里从0开始遍历，那就不正确了，因为还没算出来。
        for (int i = n; i >= 0; i --) {
            for (int j = i + 1; j < newN; j ++) {
                for (int k = i + 1; k < j; k ++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNum[k] *  newNum[i] * newNum[j]);
                }
            }

        }
        return dp[0][newN - 1];
    }


    /**
     * Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i]  。
     * 游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。
     * Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。
     * 假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回  true  ，当 Bob 赢得比赛时返回  false  。
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        //dp[i][j]表示从第i堆石头到第j堆石头中，先手（Alice）能否获胜

        Pair[][] dp = new Pair[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                dp[i][j] = new Pair(0, 0);
            }
        }
        for (int i = 0; i < n; i++) {
            dp[i][i].fir = piles[i];
            dp[i][i].sec = 0;
        }

        for(int i = n - 2; i >=0; i --) {
            for (int j = i + 1; j < n; j ++) {
                // 先手选择最左边或最右边的分数
                int left = piles[i] + dp[i + 1][j].sec;
                int right = piles[j] +  dp[i][j - 1].sec;

                if (left > right) {
                    dp[i][j].fir = left;
                    dp[i][j].sec = dp[i + 1][j].fir;
                } else {
                    dp[i][j].fir = right;
                    dp[i][j].sec = dp[i][j - 1].fir;
                }
            }
        }

        return dp[0][n - 1].fir > dp[0][n - 1].sec;
    }

    public class Pair {
        int fir, sec;
        Pair(int first, int second) {
            fir = first;
            sec = second;
        }
    }

    /**
     * 四键键盘：https://labuladong.gitee.io/algo/3/26/93/
     * @param N
     * @return
     */
    public int maxA(int N) {
        int[] dp = new int[N + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            // 按 A 键
            dp[i] = dp[i - 1] + 1;
            for (int j = 2; j < i; j++) {
                // 全选 & 复制 dp[j-2]，连续粘贴 i - j 次
                // 屏幕上共 dp[j - 2] * (i - j + 1) 个 A
                dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
            }
        }
        // N 次按键之后最多有几个 A？
        return dp[N];
    }

    public static void main(String[] args) {
        int[] case2 = {3, 1, 5, 8};
        System.out.println(maxCoins(case2));
    }

}
