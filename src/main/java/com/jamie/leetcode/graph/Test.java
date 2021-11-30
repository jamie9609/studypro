package com.jamie.leetcode.graph;

import java.util.*;

/**
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: test
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/29 11:07 上午
 */
public class Test {

    /**
     * 默写
     * @param graph
     * @return
     */
    public boolean[] visited;
    public boolean[] colored;
    public boolean ok = true;
    public boolean isBipartite(int[][] graph) {
        int n =  graph.length;

        visited = new boolean[n];
        colored = new boolean[n];

        for (int x = 0; x < n; x ++) {
            if (!visited[x]) {
                helpers(graph, x);
            }
        }
        return ok;
    }

    public void helpers(int[][] graph, int n) {
        if (!ok) {
            return;
        }
        visited[n] = true;
        for (int m : graph[n]) {
            if (!visited[m]) {
                colored[m] = !colored[n];
                helpers(graph, m);
            }else {
                if (colored[m] == colored[n]) {
                    ok =false;
                }
            }
        }
    }

    private boolean isok = true;
    private boolean[] color;
    private boolean[] isVisited;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        // 图节点编号从 1 开始
        color = new boolean[n + 1];
        visited = new boolean[n + 1];
        // 转化成邻接表表示图结构
        List<Integer>[] graph = buildGraph(n, dislikes);

        for (int v = 1; v <= n; v++) {
            if (!visited[v]) {
                traverse(graph, v);
            }
        }

        return ok;
    }

    // 建图函数
    private List<Integer>[] buildGraph(int n, int[][] dislikes) {
        // 图节点编号为 1...n
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : dislikes) {
            int v = edge[1];
            int w = edge[0];
            // 「无向图」相当于「双向图」
            // v -> w
            graph[v].add(w);
            // w -> v
            graph[w].add(v);
        }
        return graph;
    }

    // 和之前的 traverse 函数完全相同
    private void traverse(List<Integer>[] graph, int v) {
        if (!ok) return;
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                traverse(graph, w);
            } else {
                if (color[w] == color[v]) {
                    ok = false;
                }
            }
        }
    }

    int networkDelayTime(int[][] times, int n, int k) {
        // 节点编号是从 1 开始的，所以要一个大小为 n + 1 的邻接表
        List<int[]>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        // 构造图
        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            // from -> List<(to, weight)>
            // 邻接表存储图结构，同时存储权重信息
            graph[from].add(new int[]{to, weight});
        }
        // 启动 dijkstra 算法计算以节点 k 为起点到其他节点的最短路径
        int[] distTo = dijkstra(k, graph);

        // 找到最长的那一条最短路径
        int res = 0;
        for (int i = 1; i < distTo.length; i++) {
            if (distTo[i] == Integer.MAX_VALUE) {
                // 有节点不可达，返回 -1
                return -1;
            }
            res = Math.max(res, distTo[i]);
        }
        return res;
    }

    class State {
        // 图节点的 id
        int id;
        // 从 start 节点到当前节点的距离
        int distFromStart;

        State(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }
    }

    // 输入一个起点 start，计算从 start 到其他节点的最短距离
    int[] dijkstra(int start, List<int[]>[] graph) {
        // 定义：distTo[i] 的值就是起点 start 到达节点 i 的最短路径权重
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        // base case，start 到 start 的最短距离就是 0
        distTo[start] = 0;

        // 优先级队列，distFromStart 较小的排在前面
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });
        // 从起点 start 开始进行 BFS
        pq.offer(new State(start, 0));

        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curNodeID = curState.id;
            int curDistFromStart = curState.distFromStart;

            if (curDistFromStart > distTo[curNodeID]) {
                continue;
            }

            // 将 curNode 的相邻节点装入队列
            for (int[] neighbor : graph[curNodeID]) {
                int nextNodeID = neighbor[0];
                int distToNextNode = distTo[curNodeID] + neighbor[1];
                // 更新 dp table
                if (distTo[nextNodeID] > distToNextNode) {
                    distTo[nextNodeID] = distToNextNode;
                    pq.offer(new State(nextNodeID, distToNextNode));
                }
            }
        }
        return distTo;
    }


}
