package com.jamie.leetcode.dynamicProgramming.dp;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.dynamicProgramming.dp
 * @ClassName: NQueueProblem
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/15 2:59 下午
 */
public class NQueueProblem {

    /**
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     * 每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
     * 链接：https://leetcode-cn.com/problems/n-queens
     * @param n
     * @return
     */
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        LinkedList<Integer> stack = new LinkedList<>();
        helper(n, stack, res);
        return res;
    }

    public static void helper (int n , LinkedList<Integer> stack, List<List<String>> res) {
        if (stack.size() == n) {
            res.add(fillStack(n, stack));
        }

        for (int i = 0; i < n; i ++) {
            if (!isValid(i, stack, n)) {
                continue;
            }
            stack.add(i);
            helper(n, stack, res);
            stack.removeLast();
        }
    }

    public static boolean isValid(int index,  LinkedList<Integer> stack, int nScape) {
        int n = stack.size();

        for (Integer integer : stack) {
            if (index == integer) {
                return false;
            }
        }
        // 非法位置校验
        int leftIndex = index;
        int rightIndex = index;
        for(int i = n - 1; i >= 0; i --) {
            leftIndex --;
            if (leftIndex >= 0 && stack.get(i).equals(leftIndex)) {
                return false;
            }
            rightIndex ++;
            if (rightIndex < nScape && stack.get(i).equals(rightIndex)) {
                return false;
            }
        }
        return true;
    }

    public static List<String> fillStack (int n, LinkedList<Integer> stack) {
        List<String> res = new ArrayList<>();
        for (Integer item : stack) {
            StringBuilder s = new StringBuilder("");
            for (int i = 0; i < n; i ++) {
                if (i == item) {
                    s.append("Q");
                    continue;
                }
                s.append(".");
            }
            res.add(s.toString());
        }
        return res;
    }

    public static void main(String[] args) {
        LinkedList<Integer> stack = new LinkedList<>();
        stack.add(0);
        stack.add(2);
        //System.out.println(fillStack(3, stack));

        for (int i = 0; i < 3; i++) {
            System.out.println(isValid(i, stack, 3));
        }

        System.out.println(solveNQueens(4));
    }
}
