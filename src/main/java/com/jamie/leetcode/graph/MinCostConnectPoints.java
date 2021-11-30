package com.jamie.leetcode.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: MinCostConnectPoints
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/29 11:06 下午
 */
public class MinCostConnectPoints {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int xi = points[i][0], yi = points[i][1];
                int xj = points[j][0], yj = points[j][1];

                // 用坐标点在 points 中的索引表示坐标点
                edges.add(new int[]{
                        i, j, Math.abs(xi - xj) + Math.abs(yi - yj)});
            }
        }

        //按权重大小排序
        Collections.sort(edges, Comparator.comparingInt(a -> a[2]));
        // 执行 Kruskal 算法
        int mst = 0;
        UFCase uf = new UFCase(n);
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];
            // 若这条边会产生环，则不能加入 mst
            if (uf.connected(u, v)) {
                continue;
            }
            // 若这条边不会产生环，则属于最小生成树
            mst += weight;
            uf.union(u, v);
        }

        return mst;
    }
}


class UFCase {
    public int count;
    public int[] parent;

    public UFCase(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        parent[rootQ] = rootP;
        count--;
    }

    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }


    private int find(int x) {
        while (parent[x] != x) {
            x = parent[x];
        }
        return x;
    }

    public int count() {
        return count;
    }
}


