package com.jamie.leetcode.treeNode.treeNodeAgain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @PackageName: com.jamie.leetcode.treeNode.treeNodeAgain
 * @ClassName: TreeNodeDFS
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/19 6:28 下午
 */
public class TreeNodeDFS {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }


    /**
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * https://leetcode-cn.com/problems/diameter-of-binary-tree/
     * @param root
     * @return
     */
    // 某节点的深度= 节点左子树的深度+节点右子树深度+1
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        depthTree(root);
        return max;

    }
    public int depthTree(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = depthTree(node.left);
        int rightDepth = depthTree(node.right);
        max = Math.max(leftDepth + rightDepth , max);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 二叉树的前序遍历
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<TreeNode> track = new LinkedList<>();
        if (root == null) {
            return new ArrayList<>();
        }
        TreeNode head = root;
        track.add(head);
        List<Integer> res = new ArrayList<>();
        while (head != null  && !track.isEmpty()) {
            TreeNode pollNode = track.pollLast();
            if(pollNode.right != null) {
                track.add(pollNode.right);
            }
            if (pollNode.left != null) {
                track.add(pollNode.left);
            }
            res.add(pollNode.val);
            head = track.peekLast();
        }
        return res;
    }


    /**
     * 给定一个二叉树的根节点 root ，返回它的 中序 遍历。
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if ( root == null) {
            return new ArrayList<>();
        }
        TreeNode tmpNode = root;
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        while (tmpNode != null) {
            linkedList.add(tmpNode);
            tmpNode = tmpNode.left;
        }

        while (!linkedList.isEmpty()) {
            TreeNode tmp = linkedList.pollLast();
            res.add(tmp.val);
            if (tmp.right == null) {
                continue;
            }
            tmpNode = tmp.right;

            if (tmpNode.left != null) {
                while (tmpNode != null) {
                    linkedList.add(tmpNode);
                    tmpNode = tmpNode.left;
                }
            } else {
                linkedList.add(tmpNode);
            }
        }
        return res;
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
     * 二叉树的后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return new ArrayList<>();
        }
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        List<Integer> res = new ArrayList<>();

        TreeNode tmpRoot = root;
        TreeNode pre = null;

        while (!linkedList.isEmpty() || Objects.nonNull(tmpRoot)) {
            while (tmpRoot != null ) {
                linkedList.add(tmpRoot);
                tmpRoot = tmpRoot.left;
            }

            tmpRoot = linkedList.removeLast();

            if (tmpRoot.right == null || tmpRoot.right.equals(pre)) {
                res.add(tmpRoot.val);
                pre = tmpRoot;
                tmpRoot = null;
            } else {
                linkedList.add(tmpRoot);
                tmpRoot = tmpRoot.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node = new TreeNode(3, node1, node2);
        System.out.println(preorderTraversal(node));

    }
}
