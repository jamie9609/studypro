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

        String result = right + left + String.valueOf(root.val);
        Integer num = cache.getOrDefault(result, 0);
        if (num == 1) {
            res.add(root);
        }
        cache.put(result, num + 1);
        return result;
    }

}
