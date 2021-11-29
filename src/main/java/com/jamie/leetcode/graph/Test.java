package com.jamie.leetcode.graph;

import java.util.LinkedList;
import java.util.List;

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
}
