package com.jamie.leetcode.graph;

import java.util.*;

/**
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: Dijkstra2
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/30 2:50 下午
 */
public class Dijkstra2 {

    /**
     * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
     * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
     * 请你返回从左上角走到右下角的最小 体力消耗值 。
     * @return
     */


    class NodeState {
        // 矩阵中的一个位置
        int a, b;
        // 从起点 (0, 0) 到当前位置的最小体力消耗（距离）
        int effortFromStartNode;

        NodeState(int a, int b, int effortFromStartNode) {
            this.effortFromStartNode = effortFromStartNode;
            this.a = a;
            this.b = b;
        }
    }

    public int minimumEffortPath(int[][] heights) {
        // 构建加权无向图
        int m = heights.length;
        int n = heights[0].length;
        // 定义：从(0,0)到(i, j)的最小体力消耗是minEffortTo[i][j];
        int[][] minEffortTo = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(minEffortTo[i], Integer.MAX_VALUE);
        }

        // base case，起点到起点的最小消耗就是 0
        minEffortTo[0][0] = 0;

        Queue<NodeState> priorityQ = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.effortFromStartNode)
        );
        priorityQ.offer(new NodeState(0, 0, 0));

        while (!priorityQ.isEmpty()) {
            NodeState curNode = priorityQ.poll();
            int curX = curNode.a;
            int curY = curNode.b;
            int curEffortFromStartNode = curNode.effortFromStartNode;

            //如果到终点就提前结束
            if (curX == m - 1 && curY == n - 1) {
                return curEffortFromStartNode;
            }

            if (curEffortFromStartNode > minEffortTo[curX][curY]) {
                continue;
            }

            for (int[] item : iDistance(heights, curX, curY)) {
                int nextNodeX = item[0];
                int nextNodeY = item[1];

                int nextEffortFromStartNode = Math.max(
                        minEffortTo[curX][curY],
                        Math.abs(heights[curX][curY] - heights[nextNodeX][nextNodeY])
                );

                if (nextEffortFromStartNode < minEffortTo[nextNodeX][nextNodeY]) {
                    minEffortTo[nextNodeX][nextNodeY] = nextEffortFromStartNode;
                    priorityQ.add(new NodeState(nextNodeX, nextNodeY, nextEffortFromStartNode));
                }
            }
        }
        return -1;
    }

        // 方向数组，上下左右的坐标偏移量
    int[][] dirs = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};

    public List<int[]> iDistance(int[][] matrix, int x, int y) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                continue;
            }
            neighbors.add(new int[]{nx, ny});
        }
        return neighbors;
    }
}
