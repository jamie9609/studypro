package com.jamie.leetcode.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: graphCase
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/28 1:04 下午
 */
public class graphCase {

    /**
     * 给你一个有n个节点的 有向无环图（DAG），请你找出所有从节点 0到节点 n-1的路径并输出（不要求按特定顺序）
     * 二维数组的第 i 个数组中的单元都表示有向图中 i 号节点所能到达的下一些节点，空就是没有下一个结点了。
     * @param graph
     * @return
     */
    public static List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        LinkedList<Integer> path = new LinkedList<>();
        traverse(graph, 0, path);
        return res;
    }

    /**
     * 图的遍历框架
     */
    // 记录所有路径
    public static List<List<Integer>> res = new LinkedList<>();

    static void traverse(int[][] graph, int s, LinkedList<Integer> path) {
        // 添加节点 s 到路径
        path.add(s);
        int n = graph.length;
        if (s == n - 1) {
            // 到达终点
            res.add(new LinkedList<>(path));
            path.removeLast();
            return;
        }
        // 递归每个相邻节点
        for (int v : graph[s]) {
            traverse(graph, v, path);
        }
        // 从路径移出节点 s
        path.removeLast();
    }

    public static void main(String[] args) {
        int[][] graph = new int[4][];
        graph[0] = new int[]{1,2};
        graph[1] = new int[]{3};
        graph[2] = new int[]{3};

        System.out.println(allPathsSourceTarget(graph));
    }
}
