package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @PackageName: com.jamie.leetcode.treeNode
 * @ClassName: ClassicTreeNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/25 11:27 上午
 */
public class ClassicTreeNode {
    @AllArgsConstructor
    @NoArgsConstructor
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 给定一棵树的前序遍历 preorder 与中序遍历 inorder。请构造二叉树并返回其根节点。
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTree1(int[] preorder, int[] inorder) {
        return helper(preorder, 0 , preorder.length -1,
                inorder, 0, inorder.length - 1);
    }

    public TreeNode helper (int[] preorder, int preOrderIndexStart, int preOrderIndexEnd,
                            int[] inorder, int inOrderIndexStart, int inOrderIndexEnd) {
        if ( (preOrderIndexStart > preOrderIndexEnd) || (inOrderIndexStart > inOrderIndexEnd) ) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preOrderIndexStart]);
        int rootIndex = -1;
        for (int i = inOrderIndexStart; i <= inOrderIndexEnd; i ++) {
            if (inorder[i] == root.val) {
                rootIndex = i;
                break;
            }
        }

        int inorderDistance = rootIndex - inOrderIndexStart;

        root.left = helper(preorder, preOrderIndexStart + 1,  preOrderIndexStart + inorderDistance, inorder, inOrderIndexStart, rootIndex - 1);
        root.right = helper(preorder, preOrderIndexStart + inorderDistance + 1, preOrderIndexEnd, inorder, rootIndex + 1, inOrderIndexEnd);
        return root;
    }

    /**
     * 从中序与后序遍历序列构造二叉树
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return helper2(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    public TreeNode helper2 (int[] inorder, int inOrderIndexStart, int inOrderIndexEnd,
                            int[] postorder, int postorderIndexStart, int postorderIndexEnd) {
        if ( (inOrderIndexStart > inOrderIndexEnd) || (postorderIndexStart > postorderIndexEnd) ) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorderIndexEnd]);
        int rootIndex = -1;
        for (int i = inOrderIndexStart; i <= inOrderIndexEnd; i ++) {
            if (inorder[i] == root.val) {
                rootIndex = i;
                break;
            }
        }

        int inorderDistance = inOrderIndexEnd - rootIndex;

        root.left = helper2(inorder, inOrderIndexStart,  rootIndex - 1, postorder, postorderIndexStart, postorderIndexEnd - inorderDistance -1);
        root.right = helper2(inorder, rootIndex + 1, inOrderIndexEnd,
                postorder, postorderIndexEnd - inorderDistance , postorderIndexEnd - 1);
        return root;
    }

    /**
     * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
     * 两棵树重复是指它们具有相同的结构以及相同的结点值。
     * 方法：遍历每个节点，把以每个节点为根节点的二叉树序列化，找到序列化一样的树即可。
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        if (Objects.isNull(root)){
            return null;
        }
        helpers(root);
        return res;
    }
    //记录所有子树出现的次数
    HashMap<String, Integer> cache = new HashMap<>();
    // 记录重复的子树根节点
    LinkedList<TreeNode> res = new LinkedList<>();

    public String helpers(TreeNode root) {
        if (Objects.isNull(root)) {
            return "#";
        }
        String right = helpers(root.right);
        String left = helpers(root.left);

        String result = right + "," + left + "," + root.val;
        Integer num = cache.getOrDefault(result, 0);
        if (num == 1) {
            res.add(root);
        }
        cache.put(result, num + 1);
        return result;
    }


    /**
     * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 情况 1
        if (left != null && right != null) {
            return root;
        }
        // 情况 2
        if (left == null && right == null) {
            return null;
        }
        // 情况 3
        return left == null ? right : left;
    }

    /**
     * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
     *
     * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
     * 若最底层为第 h 层，则该层包含 1~2h个节点。
     *
     * @return
     */
    public int countNodes(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        int leftDeep, rightDeep;
        TreeNode tmpLeft = root;
        TreeNode tmpRight = root;
        leftDeep = 0;
        rightDeep = 0;
        while (Objects.nonNull(tmpLeft)) {
            tmpLeft = tmpLeft.left;
            leftDeep ++;
        }

        while (Objects.nonNull(tmpRight)) {
            tmpRight = tmpRight.right;
            rightDeep ++;
        }
        if (leftDeep == rightDeep) {
            return (int)Math.pow(2, rightDeep) - 1;
        }
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

}
