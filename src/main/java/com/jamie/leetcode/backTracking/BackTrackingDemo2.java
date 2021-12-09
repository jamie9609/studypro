package com.jamie.leetcode.backTracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.backTracking
 * @ClassName: BackTrackingDemo2
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/9 10:46 上午
 */
public class BackTrackingDemo2 {
    /**
     * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示.
     * 一次移动定义为选择 0 与一个相邻的数字（上下左右）进行交换.
     * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
     * 给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
     * @param board
     * @return
     */
    public static int slidingPuzzle(int[][] board) {
        int m = 2, n = 3;
        String start = "";
        String target = "123450";

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                start = start + board[i][j];
            }
        }
        int[][] neighbor = new int[][]{
                { 1, 3 },
                { 0, 4, 2 },
                { 1, 5 },
                { 0, 4 },
                { 3, 1, 5 },
                { 4, 2 }};

        //bfs算法框架
        LinkedList<String> tracks = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        tracks.add(start);
        visited.add(start);
        int step = 0;
        while (!tracks.isEmpty()) {
            int size = tracks.size();
            for (int i = 0; i < size; i ++) {
                String cur = tracks.poll();
                if (target.equals(cur)) {
                    return step;
                }
                int zeroIndex = 0;
                // 找到0的位置。
                for (int j = 0; j < cur.length(); j ++) {
                    if (cur.charAt(j) == '0') {
                        zeroIndex = j;
                        break;
                    }
                }
                for (int item : neighbor[zeroIndex]) {
                    String new_board = cur;
                    new_board = swap(new_board, zeroIndex, item);
                    if (!visited.contains(new_board)) {
                        tracks.addLast(new_board);
                        visited.add(new_board);
                    }
                }
            }
            step ++;
        }
        return -1;
    }

    public static String swap(String s, int x, int y) {
        char[] chars = s.toCharArray();
        char tmp = chars[x];
        chars[x] = chars[y];
        chars[y] = tmp;
        StringBuilder sb = new StringBuilder();
        for (char item : chars) {
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * 给你一个整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     *
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     * @param nums
     * @param target
     * @return
     */
    public static int findTargetSumWays2(int[] nums, int target) {
        LinkedList<String> tracks = new LinkedList<>();
        HashSet<List<String>> resultHash = new HashSet<>();
        backTracking(tracks, resultHash, nums, target);
        return resultHash.size();
    }

    public static void backTracking(LinkedList<String> tracks, HashSet<List<String>> resultHash, int[] nums, int target) {
        if ( tracks.size() == nums.length ) {
            if (isTargetResult(tracks, nums, target)) {
                resultHash.add(tracks);
            }
            return;
        }

        tracks.addLast("+");
        backTracking(tracks, resultHash, nums, target);
        tracks.removeLast();


        tracks.addLast("-");
        backTracking(tracks, resultHash, nums, target);
        tracks.removeLast();
    }

    public static boolean isTargetResult(List<String> tracks, int[] nums, int target) {
        int res = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (tracks.get(i).equals("+")) {
                res += nums[i];
            } else if (tracks.get(i).equals("-")){
                res -= nums[i];
            } else {
                return false;
            }
        }
        return res == target;
    }


    private static int result = 0;
    /* 主函数 */
    public static int findTargetSumWays(int[] nums, int target) {
        if (nums.length == 0) return 0;
        backtrack(nums, 0, target);
        return result;
    }

    /* 回溯算法模板 */
    public static void backtrack(int[] nums, int i, int rest) {
        // base case
        if (i == nums.length) {
            if (rest == 0) {
                // 说明恰好凑出 target
                result++;
            }
            return;
        }
        // 给 nums[i] 选择 - 号
        rest += nums[i];
        // 穷举 nums[i + 1]
        backtrack(nums, i + 1, rest);
        // 撤销选择
        rest -= nums[i];

        // 给 nums[i] 选择 + 号
        rest -= nums[i];
        // 穷举 nums[i + 1]
        backtrack(nums, i + 1, rest);
        // 撤销选择
        rest += nums[i];
    }

    public static void main(String[] args) {
        //int[][] test1 = {{1, 2, 3}, {4, 0, 5}};
        //System.out.println(slidingPuzzle(test1));
        int[] test2 = {0,0,0,0,0,0,1};
        System.out.println(findTargetSumWays(test2, 1));
    }
}
