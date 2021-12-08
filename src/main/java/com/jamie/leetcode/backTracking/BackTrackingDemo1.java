package com.jamie.leetcode.backTracking;

import org.omg.CORBA.TRANSACTION_MODE;

import java.util.*;

/**
 * 回溯算法解析
 * @PackageName: com.jamie.leetcode.backTracking
 * @ClassName: BackTrackingDemo1
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/8 11:08 上午
 */
public class BackTrackingDemo1 {
    /**
     * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列。你可以 按任意顺序 返回答案。
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> track = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        backTracking(nums, track, res);
        return res;
    }


    public static void backTracking(int[] nums, LinkedList<Integer> track, List<List<Integer>> res) {
        // 触发条件
        if( track.size() == nums.length ) {
            //这里不能直接用track, 因为java类似引用传递，直接放进去值会被覆盖，需要new新值。
            res.add(new ArrayList<>(track));
            return;
        }

        for (Integer item : nums) {
            if (track.contains(item))
                continue;
            track.add(item);
            backTracking(nums, track, res);
            track.removeLast();
        }
    }


    /**
     * n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     *
     * 给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/n-queens
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        LinkedList<String> track = new LinkedList<>();
        backTracking(ans, track, n);
        return ans;
    }

    public void backTracking(List<List<String>> ans, LinkedList<String> track, int n) {
        if (track.size() == n) {
            ans.add(new ArrayList<>(track));
            return;
        }

        for (int i = 0 ; i < n; i ++) {
            if (!isValid(i, track, n)) {
                continue;
            }
            track.add(buildStr(n, i));
            backTracking(ans, track, n);
            track.removeLast();
        }
    }

    public boolean isValid(int index, List<String> track, int n) {
        int row = track.size();
        // 检查列是否有皇后互相冲突
        for (String item : track) {
            if (item.charAt(index) == 'Q') {
                return false;
            }
        }
        // 检查右上方是否有皇后互相冲突
        for (int i = row - 1, j = index + 1;
             i >= 0 && j < n; i--, j++) {
            if (track.get(i).charAt(j) == 'Q')
                return false;
        }
        // 检查左上方是否有皇后互相冲突
        for (int i = row - 1, j = index - 1;
             i >= 0 && j >= 0; i--, j--) {
            if (track.get(i).charAt(j) == 'Q')
                return false;
        }
        return true;
    }

    public static String buildStr(int n, int index) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i ++) {
            if (i == index) {
                sb.append("Q");
                continue;
            }
            sb.append(".");
        }
        return sb.toString();
    }


    /**
     * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
     * @param nums
     * @param k
     * @return
     */
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if (k > nums.length) {
            return false;
        }
        int sum = 0;
        for (int v : nums) {
            sum += v;
        }
        if (sum % k != 0) {
            return false;
        }
        // k 个桶（集合），记录每个桶装的数字之和
        int[] bucket = new int[k];
        // 理论上每个桶（集合）中数字的和
        int target = sum / k;
        // 穷举，看看 nums 是否能划分成 k 个和为 target 的子集
        return backtrack(nums, 0, bucket, target);
    }

    public static boolean backtrack(int[] nums,int index, int[] bucket, int target) {
        if ( index == nums.length ) {
            for (int i = 0; i < bucket.length; i++) {
                if (bucket[i] != target) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < bucket.length; i++) {

            if (bucket[i] + nums[index] > target) {
                continue;
            }
            // 将 nums[index] 装入 bucket[i]
            bucket[i] += nums[index];
            // 递归穷举下一个数字的选择
            if (backtrack(nums, index + 1, bucket, target)) {
                return true;
            }
            bucket[i] -= nums[index];
        }
        return false;
    }

    /**
     * 子集与全排列的 结构略有不同。需要挨个选择。
     * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
     * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> track = new LinkedList<>();
        res.add(new ArrayList<>());
        backTracking(nums, res, track, 0);
        return res;
    }

    public static void backTracking (int[] nums, List<List<Integer>> res, LinkedList<Integer> track, int start) {
        res.add(new ArrayList<>(track));

        for (int i = start; i < nums.length; i ++) {
            track.add(nums[i]);
            backTracking(nums, res, track, i + 1);
            track.removeLast();
        }
    }

    /**
     * 组合问题。和上面全排列问题的根本不同，在于backTracking算法里的 入参是i+1 还是 start+1。i+1表示不回头的遍历。
     * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
     * 你可以按 任何顺序 返回答案。
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> track = new ArrayList<>();
        backTracking2(n, res, track, k, 1);
        return res;
    }


    public static void backTracking2 (int n, List<List<Integer>> res, List<Integer> track, int k, int start) {
        if (track.size() == k) {
            res.add(new ArrayList<>(track));
            return;
        }

        for (int i = start; i <= n; i ++) {
            if (track.contains(i)) {
                continue;
            }
            track.add(i);
            backTracking2(n, res, track, k, i + 1);
            track.remove(track.size() - 1);
        }
    }


    /**
     * 解数独
     * 编写一个程序，通过填充空格来解决数独问题。
     * @param board
     */
    public void solveSudoku(char[][] board) {
        backTracking3(board, 0, 0);
    }

    public boolean backTracking3(char[][] board, int i, int j) {
        int m = 9, n = 9;
        if (j == n) {
            return backTracking3(board, i + 1, 0);
        }
        if (i == m) {
            // 找到一个可行解，继续下一行
            return true;
        }

        if (board[i][j] != '.') {
            // 如果有预设数字，不用我们穷举
            return backTracking3(board, i, j + 1);
        }

        for (char ch = '1'; ch <= '9'; ch ++ ) {
            if (!isValid3(board, i, j, ch)) {
                continue;
            }
            board[i][j] = ch;
            if (backTracking3(board, i ,j +1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }

    boolean isValid3(char[][] board, int r, int c, char n) {
        for (int i = 0; i < 9; i++) {
            if (board[r][i] == n) {
                return false;
            }

            if (board[i][c] == n) {
                return false;
            }
            // tips: 判断 3 x 3 方框是否存在重复
            if (board[(r/3)*3 + i/3][(c/3)*3 + i%3] == n)
                return false;
        }
        return true;
    }


    /**
     * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        String traces = "";
        backTracking4(n, n, res, traces);
        return res;
    }
    // 可用的左括号数量为 left 个，可用的右括号数量为 right 个
    public static void backTracking4(int left, int right, List<String> res, String traces) {
        if (left == 0 && right == 0) {
            res.add(traces);
        }
        if (right < left) {
            return;
        }
        if (left < 0 || right < 0) {
            return;
        }

        traces = traces + "(";
        backTracking4(left - 1, right, res, traces);
        traces = traces.substring(0, traces.length() - 1);

        traces = traces + ")";
        backTracking4(left, right - 1, res, traces);
        traces = traces.substring(0, traces.length() - 1);
    }




    public static void main(String[] args) {
        System.out.println( generateParenthesis(3));
    }
}
