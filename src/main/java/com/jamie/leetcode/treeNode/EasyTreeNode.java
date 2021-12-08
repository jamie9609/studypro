package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.*;

/**
 * @PackageName: com.jamie.leetcode.treeNode
 * @ClassName: EasyTreeNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/25 8:53 上午
 */
public class EasyTreeNode {
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
    @NoArgsConstructor
    @AllArgsConstructor
    public class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public TreeNode invertTree(TreeNode root) {
        if (Objects.isNull(root)) {
            return null;
        }
        if (Objects.nonNull(root.left)) {
            invertTree(root.left);
        }
        if (Objects.nonNull(root.right)) {
            invertTree(root.right);
        }
        TreeNode tmpNode;
        tmpNode = root.left;
        root.left = root.right;
        root.right = tmpNode;
        return root;
    }

    /**
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 二叉树的问题难点在于，如何把题目的要求细化成每个节点需要做的事情，但是如果只依赖一个节点的话，肯定是没办法连接「跨父节点」的两个相邻节点的。
     * 那么，我们的做法就是增加函数参数，一个节点做不到，我们就给他安排两个节点，「将每一层二叉树节点连接起来」可以细化成「将每两个相邻节点都连接起来」：
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (Objects.isNull(root)) {
            return null;
        }
        connectTwoNode(root.left, root.right);
        return root;
    }
    public void connectTwoNode(Node node1, Node node2) {
        if (Objects.isNull(node1) || Objects.isNull(node2)) {
            return;
        }
        node1.next = node2;
        connectTwoNode(node1.left, node1.right);
        connectTwoNode(node2.left, node2.right);
        connectTwoNode(node1.right, node2.left);
    }

    /**
     * 普通队列方式实现
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        if (Objects.isNull(root)) {
            return null;
        }
        LinkedList<Node> nodeList = new LinkedList<>();
        nodeList.add(root);
        while (!nodeList.isEmpty()) {
            Node tmp = nodeList.get(0);
            int size = nodeList.size();
            for (int i = 1; i < size; i ++) {
                tmp.next = nodeList.get(i);
                tmp = nodeList.get(i);
            }

            for(int i = 0; i < size; i ++) {
                Node tmp1 = nodeList.remove();
                if(tmp1.left!=null) {
                    nodeList.add(tmp1.left);
                }
                if(tmp1.right!=null) {
                    nodeList.add(tmp1.right);
                }
            }
        }
        return root;
    }

    /**
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表
     * @param root
     */
    public void flatten(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        flatten(root.left);
        flatten(root.right);

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.right = left;
        root.left = null;

        TreeNode p = root;
        while (Objects.nonNull(p.right)){
            p = p.right;
        }
        p.right = right;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (Objects.isNull(root)) {
            return false;
        }
        if ( Objects.isNull(root.right) &&  Objects.isNull(root.left) ) {
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val) ||
                hasPathSum(root.right, targetSum - root.val);
    }

    /**
     * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(Objects.isNull(root)) {
            return null;
        }
        if (root == p || root == q) {
            return root;
        }
        TreeNode leftNode = lowestCommonAncestor(root.left, p, q);
        TreeNode rightNode = lowestCommonAncestor(root.right, p, q);

        if (leftNode != null && rightNode != null) {
            return root;
        }
        if (leftNode == null && rightNode == null) {
            return null;
        }
        return leftNode == null ? rightNode : leftNode;
    }


    public static int minDepth(TreeNode root) {
        if (Objects.isNull(root)) {
            return 0;
        }
        LinkedList<TreeNode> res = new LinkedList<>();
        res.add(root);
        int deep = 1;
        while (!res.isEmpty()) {
            int size = res.size();
            for (int i = 0; i < size; i ++) {
                TreeNode tmp = res.removeFirst();
                if (Objects.nonNull(tmp.left)) {
                    res.addLast(tmp.left);
                }
                if (Objects.nonNull(tmp.right)) {
                    res.addLast(tmp.right);
                }
                if (Objects.isNull(tmp.left) && Objects.isNull(tmp.right) ){
                    return deep;
                }
            }
            deep ++;
        }

        return deep;
    }

    public static TreeNode buildTree (Integer[] treeCaseNum) {
        if (treeCaseNum.length == 0) {
            return null;
        }
        LinkedList<TreeNode> ans = new LinkedList<>();
        for (int i = 0; i < treeCaseNum.length; i ++) {
            if(treeCaseNum[i] == null) {
                ans.addLast(null);
                continue;
            }
            ans.addLast(new TreeNode(treeCaseNum[i]) );
        }

        for (int i = 0; i < ans.size() / 2; i ++) {
            if (i * 2 + 1 < ans.size()) {
                ans.get(i).left = ans.get(i * 2 + 1);
            }
            if (i * 2 + 2 < ans.size()) {
                ans.get(i).right = ans.get(i * 2 + 2);
            }
        }
        return ans.getFirst();
    }

    /**
     * 打开转盘锁 (BFS)
     *
     * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
     * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        // 记录需要跳过的死亡密码
        Set<String> deads = new HashSet<>();

        for (String s : deadends) {
            deads.add(s);
        }
        // 记录已经穷举过的密码，防止走回头路
        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();

        // 从起点开始启动广度优先搜索
        int step = 0;
        q.offer("0000");
        visited.add("0000");

        while (!q.isEmpty()) {
            int sz = q.size();
            /* 将当前队列中的所有节点向周围扩散 */
            for (int i = 0; i < sz; i++) {
                String cur = q.poll();

                /* 判断是否到达终点 */
                if (deads.contains(cur)) {
                    continue;
                }
                if (cur.equals(target)) {
                    return step;
                }
                /* 将一个节点的未遍历相邻节点加入队列 */
                for (int j = 0; j < 4; j++) {
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up);
                    }
                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down);
                    }
                }
            }
            /* 在这里增加步数 */
            step++;
        }
        // 如果穷举完都没找到目标密码，那就是找不到了
        return -1;
    }

    // 将 s[j] 向上拨动一次
    public String plusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '9')
            ch[j] = '0';
        else
            ch[j] += 1;
        return new String(ch);
    }
    // 将 s[i] 向下拨动一次
    public String minusOne(String s, int j) {
        char[] ch = s.toCharArray();
        if (ch[j] == '0')
            ch[j] = '9';
        else
            ch[j] -= 1;
        return new String(ch);
    }

    public static void main(String[] args) {
        Integer[] testCase = {3, 9, 20, null, null, 15, 7};
        System.out.println(minDepth(buildTree(testCase)));

    }

}

