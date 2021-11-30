package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @PackageName: com.jamie.leetcode.treeNode
 * @ClassName: MidTreeNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/25 10:10 上午
 */
public class MidTreeNode {
    @NoArgsConstructor
    @AllArgsConstructor
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper1 (int[] nums, int left, int right) {
        if (left < 0 || right < 0 || left > right) {
            return null;
        }
        int maxValue = Integer.MIN_VALUE;
        int maxValueIndex = -1;
        for (int i = left; i <= right; i ++) {
            if (nums[i] > maxValue) {
                maxValue = nums[i];
                maxValueIndex = i;
            }
        }
        TreeNode treeNode = new TreeNode();
        treeNode.val = maxValue;
        treeNode.left = helper(nums, left, maxValueIndex - 1);
        treeNode.right = helper(nums, maxValueIndex + 1, right);
        return treeNode;
    }

    public TreeNode helper(int[] nums, int lo, int hi) {
        // base case
        if (lo > hi) {
            return null;
        }

        // 找到数组中的最大值和对应的索引
        int index = -1, maxVal = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxVal < nums[i]) {
                index = i;
                maxVal = nums[i];
            }
        }

        TreeNode root = new TreeNode(maxVal);
        // 递归调用构造左右子树
        root.left = helper(nums, lo, index - 1);
        root.right = helper(nums, index + 1, hi);

        return root;
    }


    /**
     * 层序遍历
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (Objects.isNull(root)) {
            return ans;
        }
        LinkedList<TreeNode> waitQueue = new LinkedList<>();

        waitQueue.addLast(root);
        while (!waitQueue.isEmpty()) {
            List<Integer> tmp = new LinkedList<>();
            int n = waitQueue.size();
            for (int i = 0; i < n; i ++) {
                TreeNode tmpNode = waitQueue.removeFirst();
                tmp.add(tmpNode.val);
                if (Objects.nonNull(tmpNode.left)) {
                    waitQueue.addLast(tmpNode.left);
                }
                if (Objects.nonNull(tmpNode.right)) {
                    waitQueue.addLast(tmpNode.right);
                }
            }
            ans.add(tmp);
        }
        return ans;
    }
}
