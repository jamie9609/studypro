package com.jamie.leetcode.topN;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackageName: com.jamie.leetcode.topN
 * @ClassName: treeNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/29 10:37 上午
 */
public class treeNodeProblem {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    int res = 0;
    int rank = 0;
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }
    public void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }

        traverse(root.left, k);
        rank ++;
        if (rank == k ) {
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }


    /**
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node的新值等于原树中大于或等于node.val的值之和。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/convert-bst-to-greater-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public TreeNode bstToGst(TreeNode root) {
        helper(root);
        return root;
    }

    // 记录累加和
    int sum = 0;
    public void helper (TreeNode root) {
        if (root == null) {
            return;
        }
        helper(root.right);
        sum += root.val;
        // 将 BST 转化成累加树
        root.val = sum;
        helper(root.left);
    }

}


