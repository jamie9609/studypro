package com.jamie.leetcode.treeNode.treeNodeAgain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

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


    /**
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * https://leetcode-cn.com/problems/invert-binary-tree/
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode leftNode = invertTree(root.left);
        TreeNode rightNode = invertTree(root.right);
        root.left = rightNode;
        root.right = leftNode;
        return root;
    }


    /**
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        flatten(root.left);
        flatten(root.right);
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = null;
        root.right = left;
        TreeNode tmpNode = root;
        while (tmpNode.right != null) {
            tmpNode = tmpNode.right;
        }
        tmpNode.right = right;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        public Node(int _val) {
            val = _val;
        }
    };

    /**
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点.填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     * 初始状态下，所有next 指针都被设置为 NULL。
     * 链接：https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null || root.left == null) {
            return null;
        }

        connectTwoNode(root.left, root.right);
        return root;
    }

    public void connectTwoNode(Node left, Node right) {
        if (left == null || right == null ) {
            return;
        }
        left.next = right;

        connectTwoNode(left.left, left.right);
        connectTwoNode(right.left, right.right);
        connectTwoNode(left.right, right.left);
    }


    /**
     * 给定一个不重复的整数数组nums 。最大二叉树可以用下面的算法从nums 递归地构建:
     * 创建一个根节点，其值为nums 中的最大值。
     * 递归地在最大值左边的子数组前缀上构建左子树。
     * 递归地在最大值 右边 的子数组后缀上构建右子树。
     * 返回nums 构建的 最大二叉树 。
     * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {

        if (nums.length == 0) {
            return null;
        }

        return helper(nums, 0, nums.length - 1);

    }

    public TreeNode helper (int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        int maxIndex = -1;
        int maxValue = Integer.MIN_VALUE;
        for (int i = start; i <= end; i ++ ) {
            if (nums[i] > maxValue) {
                maxIndex = i;
                maxValue = nums[i];
            }
        }
        TreeNode treeNode = new TreeNode(maxValue);
        treeNode.left = helper(nums, start, maxIndex - 1);
        treeNode.right = helper(nums, maxIndex + 1, end);
        return treeNode;
    }


    /**
     * 给定两个整数数组preorder 和 inorder，其中preorder 是二叉树的先序遍历， inorder是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param preorder
     * @param inorder
     * @return
     */
    // 用递归的方式去生成，不要拘泥于局部
    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return build(preorder, 0 ,preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    public TreeNode build(int[] preorder, int preStart, int preEnd,
                          int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        int headNode = preorder[preStart];
        int headIndex = -1;
        for (int i = inStart; i <= inEnd; i ++) {
            if (headNode == inorder[i]) {
                headIndex = i;
            }
        }
        TreeNode root = new TreeNode(headNode);
        root.left = build(preorder, preStart + 1 ,headIndex - inStart + preStart , inorder,  inStart, headIndex - 1);
        root.right = build(preorder,  headIndex - inStart + preStart + 1 , preEnd, inorder, headIndex + 1, inEnd);
        return root;
    }


    /**
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗二叉树。
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param inorder
     * @param postorder
     * @return
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return builds2(inorder, 0, inorder.length - 1, postorder, 0 ,postorder.length -1);
    }

    public TreeNode builds2(int[] inorder, int inStart, int inEnd,
                            int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        int rootValue = postorder[postEnd];
        int rootIndex = -1;
        for (int i = inStart; i <= inEnd; i ++) {
            if (inorder[i] == rootValue) {
                rootIndex = i;
            }
        }
        TreeNode treeNode = new TreeNode(rootValue);
        treeNode.left = builds2(inorder, inStart ,rootIndex - 1,  postorder, postStart, rootIndex - inStart + postStart -1);
        treeNode.right = builds2(inorder, rootIndex + 1, inEnd, postorder, rootIndex - inStart + postStart , postEnd - 1);
        return treeNode;
    }

    /**
     *  根据前序和后序遍历构造二叉树
     *  给定两个整数数组，preorder和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
     *
     * 如果存在多个答案，您可以返回其中 任何 一个。
     * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param preorder
     * @param postorder
     * @return
     */
    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        return builds3(preorder, 0, preorder.length - 1, postorder, 0 ,postorder.length -1);
    }

    public TreeNode builds3(int[] preorder, int preStart, int preEnd,
                            int[] postorder, int postStart, int postEnd) {
        if(preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }

        int rootValue = preorder[preStart];
        int leftRootVal = preorder[preStart + 1];
        int index = 0;
        for (int i = postStart; i < postEnd; i++) {
            if (postorder[i] == leftRootVal) {
                index = i;
                break;
            }
        }

        int leftSize = index - postStart + 1;

        TreeNode root = new TreeNode(rootValue);

        root.left = builds3(preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, index);
        root.right = builds3(preorder, preStart + leftSize + 1, preEnd,
                postorder, index + 1, postEnd - 1);
        return root;
    }


    /**
     * 给定一棵二叉树 root，返回所有重复的子树。
     * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
     * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/find-duplicate-subtrees
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param root
     * @return
     */

    HashMap<String, Integer> memo = new HashMap<>();
    LinkedList<TreeNode> res = new LinkedList<>();

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }
    public String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }
        String left = traverse(root.left);
        String right = traverse(root.right);

        String subTree = left + "," + right+ "," + root.val;

        int freq = memo.getOrDefault(subTree, 0);
        // 多次重复也只会被加入结果集一次
        if (freq == 1) {
            res.add(root);
        }
        memo.put(subTree, freq + 1);
        return subTree;
    }

    public static void main(String[] args) {

        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node = new TreeNode(3, node1, node2);
        System.out.println(preorderTraversal(node));

    }
}
