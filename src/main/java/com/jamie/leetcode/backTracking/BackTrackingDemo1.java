package com.jamie.leetcode.backTracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    public static void main(String[] args) {
        int[] case1 = {1, 2};
        System.out.println(permute(case1));
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

    public String buildStr(int n, int index) {
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
    public boolean canPartitionKSubsets(int[] nums, int k) {
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

    public boolean backtrack(int[] nums,int index, int[] bucket, int target) {
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



}
