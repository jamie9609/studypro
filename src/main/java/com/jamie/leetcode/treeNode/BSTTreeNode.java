package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @PackageName: com.jamie.leetcode.treeNode
 * @ClassName: BSTTreeNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/25 5:34 下午
 */
public class BSTTreeNode {
    private static int[][] memo;

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
     * 给定一个二叉搜索树的根节点 root，和一个整数 k，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     * BST的中序遍历就是一个升序数组。
     *
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
     *
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
     *
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
     *
     * @param list
     * @return
     */
    public TreeNode createBinaryTree(LinkedList<Integer> list) {
        TreeNode node = null;
        if (list == null || list.isEmpty()) {
            return null;
        }
        Integer data = list.removeFirst();
        if (data != null) {
            node = new TreeNode(data);
            node.left = createBinaryTree(list);
            node.right = createBinaryTree(list);
        }
        return node;
    }

    /**
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * 有效 二叉搜索树定义如下：
     * 节点的左子树只包含 小于 当前节点的数。
     * 节点的右子树只包含 大于 当前节点的数。
     * 所有左子树和右子树自身必须也是二叉搜索树。
     *
     * @param root
     * @return
     */
    public boolean isValidBSTFail(TreeNode root) {
        if (Objects.isNull(root)) {
            return false;
        }
        if (Objects.nonNull(root.left)) {
            if (root.left.val >= root.val || !isValidBST(root.left)) {
                return false;
            }
        }
        if (Objects.nonNull(root.right)) {
            if (root.right.val <= root.val || !isValidBST(root.right)) {
                return false;
            }
        }
        return true;
    }

    boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    // 我们通过使用辅助函数，增加函数参数列表，在参数中携带额外信息，将这种约束传递给子树的所有节点，这也是二叉树算法的一个小技巧吧。
    // 适用于通过辅助函数，做迭代

    /**
     * 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val
     *
     * @param root
     * @param min
     * @param max
     * @return
     */
    boolean helper(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min.val) {
            return false;
        }
        if (max != null && root.val >= max.val) {
            return false;
        }
        return helper(root.left, min, root) &&
                helper(root.right, root, max);

    }

    /* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        // base case

        // 若 root.val 不符合 max 和 min 的限制，说明不是合法 BST
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        // 限定左子树的最大值是 root.val，右子树的最小值是 root.val
        return isValidBST(root.left, min, root)
                && isValidBST(root.right, root, max);
    }


    /**
     * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。
     * 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (Objects.isNull(root)) {
            return null;
        }
        return helper2(root, val);
    }

    public TreeNode helper2(TreeNode root, int val) {
        if (Objects.isNull(root)) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val) {
            return helper2(root.left, val);
        } else {
            return helper2(root.right, val);
        }
    }

    /**
     * 给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。
     * 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (Objects.isNull(root)) {
            return new TreeNode(val);
        }
        TreeNode ans = root;

        if (val > ans.val) {
            ans.right = insertIntoBST(ans.right, val);
        } else {
            ans.left = insertIntoBST(ans.left, val);
        }
        return root;
    }

    /**
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中key对应的节点，并保证二叉搜索树的性质不变。
     * 返回二叉搜索树（有可能被更新）的根节点的引用。
     * 一般来说，删除节点可分为两个步骤：
     * 首先找到需要删除的节点；
     * 如果找到了，删除它。
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (Objects.isNull(root)) {
            return null;
        }
        if (root.val == key) {
            //找到节点，进行删除
            if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
                return null;
            }
            if (Objects.nonNull(root.left) && Objects.isNull(root.right)) {
                return root.left;
            }
            if (Objects.nonNull(root.right) && Objects.isNull(root.left)) {
                return root.right;
            }
            if (Objects.nonNull(root.right) && Objects.nonNull(root.left)) {
                TreeNode min = getMin(root.right);
                //移除最小的节点
                TreeNode minNode = removeMin(root.right);
                min.left = root.left;
                min.right = minNode;
                root = min;
            }
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }

    public TreeNode getMin(TreeNode root) {
        while (Objects.nonNull(root.left)) {
            root = root.left;
        }
        return root;
    }

    // 删除以 root 为根的 BST 中的最小的节点，利用 BST 的特性
    private TreeNode removeMin(TreeNode root) {
        // base case
        if (root.left == null) {
            return root.right;
        }
        root.left = removeMin(root.left);
        return root;
    }

    /**
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     *
     * @param n
     * @return
     */
    int[][] ans;

    public int numTrees(int n) {
        ans = new int[n + 1][n + 1];
        return helpers(1, n);
    }

    public int helpers(int left, int right) {
        if (right < left) {
            return 1;
        }
        if (ans[left][right] != 0) {
            return ans[left][right];
        }
        int res = 0;
        for (int i = left; i <= right; i ++ ){
            int leftNums = helpers(left, i - 1);
            int rightNums = helpers(i + 1, right);
            res += leftNums * rightNums;
        }
        ans[left][right] = res;
        return res;
    }

    List<TreeNode>[][] res2;

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new LinkedList<>();
        }
        return helpers3(1, n);
    }
    public List<TreeNode> helpers3(int left, int right) {
        LinkedList<TreeNode> ans = new LinkedList<>();
        if (right < left) {
            ans.add(null);
            return ans;
        }
        /*if ( !res2[left][right].isEmpty()) {
            return res2[left][right];
        }*/

        for (int i = left; i <= right; i ++) {
            List<TreeNode> leftTree = helpers3(left, i - 1);
            List<TreeNode> rightTree = helpers3(i + 1, right);

            for (TreeNode leftItem : leftTree) {
                for (TreeNode  rightItem : rightTree) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = leftItem;
                    treeNode.right = rightItem;
                    ans.add(treeNode);
                }
            }
        }
        //res2[left][right] = ans;
        return ans;
    }

    /**
     * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
     * res[0] 记录以 root 为根的二叉树是否是 BST，若为 1 则说明是 BST，若为 0 则说明不是 BST；
     * res[1] 记录以 root 为根的二叉树所有节点中的最小值；
     * res[2] 记录以 root 为根的二叉树所有节点中的最大值；
     * res[3] 记录以 root 为根的二叉树所有节点值之和。
     * @param root
     * @return
     */
    int maxSum = 0;
    public int maxSumBST(TreeNode root) {
        traverse2(root);
        return maxSum;
    }

    public int[] traverse2(TreeNode root) {
        if (Objects.isNull(root)) {
            return new int[]{1, Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }
        // 递归计算左右子树
        int[] left = traverse2(root.left);
        int[] right = traverse2(root.right);

        int[] res = new int[4];
        if (left[0] == 1 && right[0] == 1 &&
                root.val > left[2] && root.val < right[1]) {
            res[0] = 1;
            res[1] = Math.min(left[1], root.val);
            res[2] = Math.max(right[2], root.val);
            res[3] = left[3] + right[3] + root.val;
            maxSum = Math.max(maxSum, res[3]);
        }else {
            res[0] = 0;
        }
        return res;
    }


    /**
     * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，
     * 采取相反方式重构得到原数据。请设计一个算法来实现二叉树的序列化与反序列化。
     * 这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     *
     * @param root
     * @return
     */

    // 代表分隔符的字符
    public static String SEP1 = ",";
    // 代表 null 空指针的字符
    public static String NULL1 = "#";
    // 用于拼接字符串
    public static StringBuilder sb = new StringBuilder();
    // Encodes a tree to a single string.
    public static String serialize1(TreeNode root) {

        helper4(root);
        return sb.toString();
    }

    public static void helper4(TreeNode root) {
        if (Objects.isNull(root)) {
            sb.append(NULL1).append(SEP1);
            return;
        }

        sb.append(root.val).append(SEP1);
        helper4(root.left);
        helper4(root.right);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize1(String data) {
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP1)) {
            nodes.addLast(s);
        }
        return helper4(nodes);
    }

    public static TreeNode helper4(LinkedList<String> nodes) {
        if (nodes.isEmpty()) {
            return null;
        }
        String s = nodes.removeFirst();

        if (s.equals(NULL1)) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(s));

        root.left = helper4(nodes);
        root.right = helper4(nodes);
        return root;
    }

    /**
     *  二叉树的序列化与反序列化
     *  https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/
     *  含空指针
     * @param root
     * @return
     */
    public static String SEP = ",";
    public static String NULL = "#";
    // Encodes a tree to a single string.
    public static StringBuilder stringBuilder = new StringBuilder();
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        builders(root);
        return stringBuilder.toString();
    }
    public static void builders(TreeNode root) {
        if (root == null) {
            stringBuilder.append(NULL).append(SEP);
            return;
        }
        stringBuilder.append(root.val).append(SEP);
        builders(root.left);
        builders(root.right);
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.equals("")) {
            return null;
        }
        LinkedList<String> nodes = new LinkedList<>();
        for (String s : data.split(SEP)) {
            nodes.addLast(s);
        }
        return builders2(nodes);
    }

    public static TreeNode builders2(LinkedList<String> stringsList) {
        if (stringsList.isEmpty()) {
            return null;
        }
        String s = stringsList.removeFirst();
        if (s.equals(NULL)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(s));
        node.left = builders2(stringsList);
        node.right = builders2(stringsList);
        return node;
    }



    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        System.out.println(serialize(treeNode));
        System.out.println(deserialize(serialize(treeNode)));


    }
}
