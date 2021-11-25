package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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

}
