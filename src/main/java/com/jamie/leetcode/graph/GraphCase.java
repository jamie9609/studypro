package com.jamie.leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: graphCase
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/28 1:04 下午
 */
public class GraphCase {

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

    /**
     * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
     *
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程 bi 。
     *
     * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
     * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] lists = buildGraph(numCourses, prerequisites);
        // 只要没有循环依赖可以完成所有课程
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            traverse(lists, i);
        }
        return !hasCycle;
    }

    /**
     * 构建图
     * @param numCourses
     * @param prerequisites
     * @return
     */
    // 记录一次 traverse 递归经过的节点
    boolean[] onPath;
    // 记录遍历过的节点，防止走回头路
    boolean[] visited;
    // 记录图中是否有环
    boolean hasCycle = false;

    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        LinkedList<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i ++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites){
            int from = edge[1];
            int to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }


    void traverse(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            hasCycle = true;
        }

        if (visited[s] || hasCycle) {
            return;
        }
        visited[s] = true;
        onPath[s] = true;
        for (Integer t : graph[s]) {
            traverse(graph, t);
        }
        onPath[s] = false;
    }

    /**
     * 现在你总共有 numCourses 门课需要选，记为 0 到numCourses - 1。给你一个数组prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修 bi 。
     *
     * 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示：[0,1] 。
     * 返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    List<Integer> postorder = new ArrayList<>();
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph2(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];
        // 遍历图
        for (int i = 0; i < numCourses; i++) {
            traverse2(graph, i);
        }
        // 有环图无法进行拓扑排序
        if (hasCycle) {
            return new int[]{};
        }
        // 逆后序遍历结果即为拓扑排序结果
        Collections.reverse(postorder);
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = postorder.get(i);
        }
        return res;
    }


    List<Integer>[] buildGraph2(int numCourses, int[][] prerequisites) {
        // 图中共有 numCourses 个节点
        List<Integer>[] graph = new LinkedList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }

    // 图遍历函数
    void traverse2(List<Integer>[] graph, int s) {
        if (onPath[s]) {
            // 发现环
            hasCycle = true;
        }
        if (visited[s] || hasCycle) {
            return;
        }
        // 前序遍历位置
        onPath[s] = true;
        visited[s] = true;
        for (int t : graph[s]) {
            traverse2(graph, t);
        }
        // 后序遍历位置
        postorder.add(s);
        onPath[s] = false;
    }


}
