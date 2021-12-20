package com.jamie.leetcode.dynamicProgramming.dp;

/**
 * KMP 算法（Knuth-Morris-Pratt 算法）
 * 字符串匹配算法
 * @PackageName: com.jamie.leetcode.dynamicProgramming.dp
 * @ClassName: KMP
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/17 5:47 下午
 */
public class KMP {
    private static int[][] dp;
    private static String pat;

    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 影子状态 X 初始为 0
        int X = 0;
        // 当前状态 j 从 1 开始
        for (int j = 1; j < M; j++) {
            //收集匹配不同的字符会出现的情况。
            for (int c = 65; c < 70; c++) {
                if (pat.charAt(j) == c)
                    dp[j][c] = j + 1;
                else
                    dp[j][c] = dp[X][c];
            }
            // 更新影子状态
            X = dp[X][pat.charAt(j)];
        }
    }

    public static int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++) {
            // 计算 pat 的下一个状态
            j = dp[j][txt.charAt(i)];
            // 到达终止态，返回结果
            if (j == M) {
                return i - M + 1;
            }
        }
        // 没到达终止态，匹配失败
        return -1;
    }

    /**
     * 时间复杂度O(m * n)的算法，简单。
     * @param ss
     * @param pp
     * @return
     */
    public int strStr(String ss, String pp) {
        int n = ss.length(), m = pp.length();
        char[] s = ss.toCharArray(), p = pp.toCharArray();
        // 枚举原串的「发起点」
        for (int i = 0; i <= n - m; i++) {
            // 从原串的「发起点」和匹配串的「首位」开始，尝试匹配
            int a = i, b = 0;
            while (b < m && s[a] == p[b]) {
                a++;
                b++;
            }
            // 如果能够完全匹配，返回原串的「发起点」下标
            if (b == m) return i;
        }
        return -1;
    }

    //注意代码中 for 循环的变量初始值，可以这样理解：后者是在 txt 中匹配 pat，前者是在 pat 中匹配 pat[1..end]，
    // 状态 X 总是落后状态 j 一个状态，与 j 具有最长的相同前缀。
    // 所以把 X 比喻为影子状态，似乎也有一点贴切。
    // https://labuladong.gitee.io/algo/3/26/96/

    public static void main(String[] args) {
        KMP kmp = new KMP("ABABC");
        System.out.println(kmp.search("ABAABCCCABABC"));


    }
}
