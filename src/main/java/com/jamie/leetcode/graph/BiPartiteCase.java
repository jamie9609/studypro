package com.jamie.leetcode.graph;

import java.util.LinkedList;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: biPartiteCase
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/29 10:47 上午
 */
public class BiPartiteCase {
    /**
     * 如果图是二分图，返回 true ；否则，返回 false 。
     * @param graph
     * @return
     */
    // 记录图是否符合二分图性质
    private boolean ok = true;
    // 记录图中节点的颜色，false 和 true 代表两种不同颜色
    private boolean[] color;
    // 记录图中节点是否被访问过
    private boolean[] visited;

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        color = new boolean[n];
        visited = new boolean[n];

        for (int i = 0; i < n; i ++) {
            if (!visited[i]) {
                reserve(graph, i);
            }
        }
        return ok;
    }

    public void  reserve(int[][] graph, int v){
        if (!ok) {
            return;
        }
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                reserve(graph, w);
            } else {
                if (color[w] == color[v]) {
                    ok = false;
                }
            }
        }
    }

    /**
     * 可能的二分法
     * 给定一组 N 人（编号为 1, 2, ..., N）， 我们想把每个人分进任意大小的两组。
     * 每个人都可能不喜欢其他人，那么他们不应该属于同一组。
     * 形式上，如果 dislikes[i] = [a, b]，表示不允许将编号为 a 和 b 的人归入同一组。
     * 当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
     * @param n
     * @param dislikes
     * @return
     */
    private boolean isOk = true;
    private boolean[] isColor ;
    private boolean[] isVisit ;

    public boolean possibleBipartition(int n, int[][] dislikes) {
        isColor = new boolean[n + 1];
        isVisit = new boolean[n + 1];
        List<Integer>[] graph = buildGraph(n, dislikes);

        for (int v = 1; v < n + 1; v ++) {
            if (!isVisit[v]) {
                helpers(graph, v);
            }
        }
        return isOk;
    }

    public List<Integer>[] buildGraph(int n, int[][] dislikes) {
        List<Integer>[] graph = new LinkedList[n + 1];

        for (int i = 1; i <=n ; i ++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : dislikes) {
            int v = edge[0];
            int w = edge[1];

            graph[v].add(w);
            graph[w].add(v);
        }
        return graph;
    }

    public void helpers(List<Integer>[] graph, int v) {
        if (!isOk) {
            return;
        }
        isVisit[v] = true;
        for (Integer item : graph[v]) {
            if ( !isVisit[item]) {
                isColor[item] = !isColor[v];
                helpers(graph, item);
            } else {
                if (isColor[item] == isColor[v]) {
                    isOk = false;
                }
            }
        }

    }


}
