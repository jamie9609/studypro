package com.jamie.leetcode.graph;

import java.util.*;

/**
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: Dijkstra3
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/30 3:48 下午
 */
public class Dijkstra3 {
    /**
     * 给你一个由 n 个节点（下标从 0 开始）组成的无向加权图，该图由一个描述边的列表组成，其中 edges[i] = [a, b] 表示连接节点 a 和 b 的一条无向边，且该边遍历成功的概率为 succProb[i] 。
     * 指定两个节点分别作为起点 start 和终点 end ，请你找出从起点到终点成功概率最大的路径，并返回其成功概率。
     * 如果不存在从 start 到 end 的路径，请 返回 0 。只要答案与标准答案的误差不超过 1e-5 ，就会被视作正确答案。
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<double[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i ++){
            graph[i] = new LinkedList<>();
        }

        for (int i = 0; i < edges.length; i ++ ) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight  = succProb[i];
            // 无向图就是双向图；先把 int 统一转成 double，待会再转回来
            graph[from].add(new double[]{(double)to, weight});
            graph[to].add(new double[]{(double)from, weight});
        }
        return dijkstra(start, end, graph);
    }

    class State {
        // 图节点的 id
        int id;
        // 从 start 节点到达当前节点的概率
        double probFromStart;

        State(int id, double probFromStart) {
            this.id = id;
            this.probFromStart = probFromStart;
        }
    }

    double dijkstra(int start, int end, List<double[]>[] graph) {
        // 定义：probTo[i] 的值就是节点 start 到达节点 i 的最大概率
        double[] probTo = new double[graph.length];
        Arrays.fill(probTo, -1);
        // base case，start 到 start 的概率就是 1
        probTo[start] = 1;

        Queue<State> priorityQ = new PriorityQueue<>((a, b) -> Double.compare(b.probFromStart, a.probFromStart));

        priorityQ.offer(new State(start, 1));

        while (!priorityQ.isEmpty()) {
            State curState = priorityQ.poll();
            int curNodeId = curState.id;
            double curNodeProbFromStart = curState.probFromStart;

            if (curNodeId == end) {
                return curNodeProbFromStart;
            }

            if ( curNodeProbFromStart < probTo[curNodeId] ) {
                continue;
            }

            for ( double[] item : graph[curNodeId]) {
                int nextNodeId = (int) item[0];
                double nextNodeProbFromStart = probTo[curNodeId] * item[1];
                if (probTo[nextNodeId] < nextNodeProbFromStart) {
                    probTo[nextNodeId] = nextNodeProbFromStart;
                    priorityQ.offer(new State(nextNodeId, nextNodeProbFromStart));
                }
            }
        }
        return 0.0;
    }

}
