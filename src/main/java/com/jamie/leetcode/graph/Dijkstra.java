package com.jamie.leetcode.graph;

import lombok.AllArgsConstructor;

import java.util.*;

/**
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: Dijkstra
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/30 10:00 上午
 */
public class Dijkstra {

    /**
     * Dijkstra 算法求解经典例题
     * 有 n 个网络节点，标记为1 到 n。
     * 给你一个列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点， wi是一个信号从源节点传递到目标节点的时间。
     * 现在，从某个节点K发出一个信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1 。
     * @param times
     * @param n
     * @param k
     * @return
     */

    public int networkDelayTime(int[][] times, int n, int k) {
        // 第一步，构造图
        List<int[]>[] graph = new LinkedList[n + 1];

        for(int i = 1; i < n + 1; i ++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : times) {
            int from = edge[0];
            int to = edge[1];
            int weight = edge[2];
            // from -> List<(to, weight)>
            // 邻接表存储图结构，同时存储权重信息
            graph[from].add(new int[]{to, weight});
        }

        int[] minDistTo = dijkstra(k, graph);

        int res = 0;

        for (int i = 1; i < minDistTo.length; i ++) {
            if (minDistTo[i] == Integer.MAX_VALUE) {
                return -1;
            }
            res = Math.max(res, minDistTo[i]);
        }
        return res;
    }

    public class State {
        // 图节点的 id
        int id;
        // 从 start 节点到当前节点的距离
        int distFromStart;

        public State(int id, int distFromStart) {
            this.id = id;
            this.distFromStart = distFromStart;
        }

    }

    /**
     * dijkstra 算法
     * 输入一个起点 start，计算从 start 到其他节点的最短距离
     * @param start
     * @param graph
     * @return
     */
    public int[] dijkstra(int start, List<int[]>[] graph) {
        // 定义：distTo[i] 的值就是起点 start 到达节点 i 的最短路径权重
        int[] minDistTo = new int[graph.length];
        Arrays.fill(minDistTo, Integer.MAX_VALUE);
        minDistTo[start] = 0;

        Queue<State> priorityQ = new PriorityQueue<>(Comparator.comparingInt(a -> a.distFromStart));

        priorityQ.offer(new State(start, 0));

        while (!priorityQ.isEmpty()) {
            State curStats = priorityQ.poll();
            int curNodeID = curStats.id;
            int curNodeDistFromStart = curStats.distFromStart;

            if (curNodeDistFromStart > minDistTo[curNodeID]) {
                continue;
            }
            for (int[] neighbor : graph[curNodeID]) {
                int nextNodeID = neighbor[0];
                int nextNodeDistance = neighbor[1] + curNodeDistFromStart;
                if (minDistTo[nextNodeID] > nextNodeDistance) {
                    minDistTo[nextNodeID] =  nextNodeDistance;
                    priorityQ.add(new State(nextNodeID, nextNodeDistance));
                }
            }
        }

        return minDistTo;
    }
}
