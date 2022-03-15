package com.jamie.leetcode.dynamicProgramming.dp;

/**
 * 高楼扔鸡蛋
 * 你面前有一栋从 1 到 N 共 N 层的楼，然后给你 K 个鸡蛋（K 至少为 1）。现在确定这栋楼存在楼层 0 <= F <= N，在这层楼将鸡蛋扔下去，鸡蛋恰好没摔碎（高于 F 的楼层都会碎，低于 F 的楼层都不会碎）。现在问你，最坏情况下，你至少要扔几次鸡蛋，才能确定这个楼层 F 呢？
 * @PackageName: com.jamie.leetcode.dynamicProgramming.dp
 * @ClassName: EggDrop
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/14 8:06 下午
 */
public class EggDrop {

    public static int superEggDrop2(int k, int n) {
        memo = new int[k + 1][n + 1];
        return helper(k, n);
    }

    public static int helper(int k, int n) {
        if (n == 0) {
            return 0;
        }
        if (k == 1){
            return n;
        }
        if (memo[k][n] > 0) {
            return memo[k][n];
        }

        Integer res = Integer.MAX_VALUE;
        for(int i = 1; i < n + 1; i ++) {
            res = Math.min(res, Math.max(helper(k - 1, i - 1) , helper(k, n - i)) + 1);
        }
        memo[k][n] = res;
        return res;
    }

    //用二分搜索替代线性搜索，减少运算时间
    static int[][] memo = null;

    public static int superEggDrop(int k, int n) {
        memo = new int[k + 1][n + 1];
        return helper2(k, n);
    }

    public static int helper2 (int k, int n) {
        if (n == 0) {
            return 0;
        }
        if (k == 1){
            return n;
        }
        if (memo[k][n] > 0) {
            return memo[k][n];
        }
        Integer res = Integer.MAX_VALUE;
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = (high + low) / 2;
            int broken = helper2(k - 1, mid - 1);
            int not_broken = helper2(k, n - mid);
            if (broken > not_broken) {
                high = mid - 1;
                res = Math.min(res, broken + 1);
            } else {
                low = mid + 1;
                res = Math.min(res, not_broken + 1);
            }
        }
        memo[k][n] = res;
        return res;
    }

    public static void main(String[] args) {
        int i = superEggDrop2(3, 200);
        System.out.println(i);

        int x = superEggDrop(3, 200);
        System.out.println(x);
    }
}
