package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @PackageName: com.jamie.leetcode.treeNode
 * @ClassName: TraversalTree
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/25 6:50 下午
 */
public class TraversalTree {

    @AllArgsConstructor
    @NoArgsConstructor
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(Integer val) {
            this.val = val;
        }
    }
    /**
     * 二叉树的前序遍历，非递归。
     * @param root
     * @return
     */
    public static List<Integer> preorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return new ArrayList<>();
        }
        TreeNode head = root;
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> waitList = new LinkedList<>();
        waitList.add(head);
        while (!waitList.isEmpty()) {
            TreeNode tmpRoot = waitList.removeLast();
            res.add(tmpRoot.val);
            if (Objects.nonNull(tmpRoot.right)) {
                waitList.add(tmpRoot.right);
            }
            if (Objects.nonNull(tmpRoot.left)) {
                waitList.add(tmpRoot.left);
            }
        }
        return res;
    }

    /**
     * 二叉树的中序遍历，非递归解法
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> waitList = new LinkedList<>();
        TreeNode tmpRoot = root;
        while (Objects.nonNull(tmpRoot)) {
            waitList.add(tmpRoot);
            tmpRoot = tmpRoot.left;
        }
        while (!waitList.isEmpty()) {
            TreeNode tmp = waitList.removeLast();
            res.add(tmp.val);
            if (Objects.isNull(tmp.right)) {
                continue;
            }
            tmpRoot = tmp.right;
            if (Objects.nonNull(tmpRoot.left)) {
                while (Objects.nonNull(tmpRoot)) {
                    waitList.add(tmpRoot);
                    tmpRoot = tmpRoot.left;
                }
            }else {
                waitList.add(tmpRoot);
            }
        }
        return res;
    }


    /**
     * 二叉树的后序遍历
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (Objects.isNull(root)) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> waitList = new LinkedList<>();
        TreeNode tmpRoot = root;
        TreeNode pre = null;
        while (!waitList.isEmpty() || Objects.nonNull(tmpRoot)) {
            while (Objects.nonNull(tmpRoot)) {
                waitList.add(tmpRoot);
                tmpRoot = tmpRoot.left;
            }
            tmpRoot = waitList.removeLast();

            if (Objects.isNull(tmpRoot.right) || tmpRoot.right.equals(pre)) {
                res.add(tmpRoot.val);
                pre = tmpRoot;
                tmpRoot = null;
            } else {
                waitList.add(tmpRoot);
                tmpRoot = tmpRoot.right;
            }
        }
        return res;
    }



    public static TreeNode createBinaryTree(LinkedList<Integer> list){
        TreeNode node = null;
        if(list == null || list.isEmpty()){
            return null;
        }
        Integer data = list.removeFirst();
        if( data != null){
            node = new TreeNode(data);
            node.left = createBinaryTree(list);
            node.right = createBinaryTree(list);
        }
        return node;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list =  new LinkedList<Integer>();
        list.add(1);
        list.add(null);
        list.add(2);
        list.add(3);
        TreeNode binaryTree = createBinaryTree(list);
        System.out.println(binaryTree.toString());
        List<Integer> integers = inorderTraversal(binaryTree);
        System.out.println(integers);

    }
}
