package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @PackageName: com.jamie.leetcode.treeNode
 * @ClassName: nTree
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/30 9:03 上午
 */
public class nTree {
    @NoArgsConstructor
    @AllArgsConstructor
    class Node {
        public int val;
        public List<Node> children;
        public Node(int _val) {
            val = _val;
        }
    };


    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> ans = new LinkedList<>();
        if (Objects.isNull(root)) {
            return ans;
        }
        LinkedList<Node> waitQueue = new LinkedList<>();

        waitQueue.addLast(root);
        while (!waitQueue.isEmpty()) {
            List<Integer> tmp = new LinkedList<>();
            int n = waitQueue.size();
            for (int i = 0; i < n; i ++) {
                Node tmpNode = waitQueue.removeFirst();
                tmp.add(tmpNode.val);
                if (Objects.nonNull(tmpNode.children)) {
                    for (Node item : tmpNode.children) {
                        waitQueue.addLast(item);
                    }
                }
            }
            ans.add(tmp);
        }
        return ans;
    }



}
