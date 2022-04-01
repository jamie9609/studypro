package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.treeNode
 * @ClassName: BinarySearchTreeDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/29 11:04 上午
 */
public class BinarySearchTreeDemo {

    @NoArgsConstructor
    @AllArgsConstructor
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }

    /* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
    public boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }

        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }
        return isValidBST(root.left, min, root) && isValidBST(root.right, root, max);
    }


    /**
     * 给定二叉搜索树（BST）的根节点root和一个整数值val。
     * 你需要在 BST 中找到节点值等于val的节点。 返回以该节点为根的子树。 如果节点不存在，则返回null。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        return helpers(root, val);
    }

    public TreeNode helpers(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val > val ) {
            return helpers(root.left, val);
        }
        if (root.val < val) {
            return helpers(root.right, val);
        }
        return root;
    }


    /**
     *给定二叉搜索树（BST）的根节点root和要插入树中的值value，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
     * 链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // 找到空位置插入新节点
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        }
        if (root.val > val) {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }


    /** 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     来源：力扣（LeetCode）
     链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
     著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null ) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode minNode = getMin(root.right);
            root.right = deleteNode(root.right, minNode.val);
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else if (root.val > key) {
            root.left =  deleteNode(root.left, key);
        }
        return root;
    }

    public TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * https://leetcode-cn.com/problems/unique-binary-search-trees/
     * @param n
     * @return
     */
    public int numTrees(int n) {
        memo = new int[n + 1][ n + 1];
        return count(1, n);
    }

    public int[][] memo;

    public int count(int left, int right) {
        if (left > right) {
            return 1;
        }
        if (memo[left][right] > 0) {
            return memo[left][right];
        }
        int res = 0;
        for (int i = left; i <= right; i ++ ) {
            int iLeft = count(left, i - 1);
            int iRight = count(i + 1 , right);
            res += iLeft * iRight;
        }
        memo[left][right] = res;
        return res;
    }


    /**
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return build(1, n);
    }

    public List<TreeNode> build(int lo, int hi) {
        List<TreeNode> res = new LinkedList<>();
        if (lo > hi) {
            res.add(null);
            return res;
        }
        for (int i = lo; i <= hi; i++) {
            List<TreeNode> leftTree = build(lo, i - 1);
            List<TreeNode> rightTree = build(i + 1, hi);
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
