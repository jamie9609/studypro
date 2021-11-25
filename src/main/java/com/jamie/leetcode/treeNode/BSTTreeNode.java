package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.Objects;

/**
 * @PackageName: com.jamie.leetcode.treeNode
 * @ClassName: BSTTreeNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/25 5:34 下午
 */
public class BSTTreeNode {
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
     * 给定一个二叉搜索树的根节点 root，和一个整数 k，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     * BST的中序遍历就是一个升序数组。
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return res;
    }

    // 记录结果
    int res = 0;
    // 记录当前元素的排名
    int rank = 0;

    /**
     * 计算第k小的元素，将结果和排名记录下来。
     * @param root
     * @param k
     */
    public void traverse(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        traverse(root.left, k);
        /* 中序遍历代码位置 */
        //记录当前元素的排名
        rank++;
        if (k == rank) {
            // 找到第 k 小的元素
            res = root.val;
            return;
        }
        traverse(root.right, k);
    }

    /**
     * 以上还不高效，
     * 那么回到这个问题，想找到第 k 小的元素，或者说找到排名为 k 的元素，如果想达到对数级复杂度，关键也在于每个节点得知道他自己排第几。
     * 比如说你让我查找排名为 k 的元素，当前节点知道自己排名第 m，那么我可以比较 m 和 k 的大小：
     * 1、如果 m == k，显然就是找到了第 k 个元素，返回当前节点就行了。
     * 2、如果 k < m，那说明排名第 k 的元素在左子树，所以可以去左子树搜索第 k 个元素。
     * 3、如果 k > m，那说明排名第 k 的元素在右子树，所以可以去右子树搜索第 k - m - 1 个元素。
     * 这样就可以将时间复杂度降到 O(logN) 了。
     * class TreeNode {
     *     int val;
     *     // 以该节点为根的树的节点总数
     *     int size;
     *     TreeNode left;
     *     TreeNode right;
     * }
     * 这就是我们之前说的，需要在二叉树节点中维护额外信息。每个节点需要记录，以自己为根的这棵二叉树有多少个节点。
     * 有了 size 字段，外加 BST 节点左小右大的性质，对于每个节点 node 就可以通过 node.left 推导出 node 的排名，从而做到我们刚才说到的对数级算法。
     *
    */

    /**
     * 给定一个二叉搜索树，请将它的每个节点的值替换成树中大于或者等于该节点值的所有节点值之和。
     * @param root
     * @return
     */

    public TreeNode convertBST(TreeNode root) {
        traverse(root);
        return root;
    }

    int sum = 0;
    public void traverse(TreeNode root) {
        if (Objects.isNull(root)) {
            return;
        }
        traverse(root.right);
        //中序遍历代码；
        sum += root.val;
        root.val = sum;
        traverse(root.left);
    }

    /**
     * 构建二叉树
     * @param list
     * @return
     */
    public TreeNode createBinaryTree(LinkedList<Integer> list){
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


}
