package com.jamie.leetcode.graph;

/**
 * 并查集算法。主要是解决图论中「动态连通性」问题的
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: UnionFind
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/29 11:49 上午
 */
public class UnionFind {
    // 连通分量的个数
    private int count;
    // 存储一棵树
    private int[] parent;
    // 记录一颗树的重量
    private int[] size;

    //n为图中节点个数。初始化时，树的重量为1；
    public UnionFind(int n) {
        this.count = n;
        parent = new int[n];
        size =  new int[n];
        for (int i = 0; i < n; i ++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        //大树接小树

        if(size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }

        count --;
    }

    // 判断节点 p 和节点 q 是否连通
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    // 返回节点 x 的连通分量根节点
    private int find(int x) {
        while(parent[x] != x) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }

    // 返回图中的连通分量个数
    public int count() {
        return count;
    }




}
/**
 *  给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。
 *  可以用连通图并查集算法的方式解决：可以把那些不需要被替换的 O 看成一个拥有独门绝技的门派，它们有一个共同祖师爷叫 dummy，这些 O 和 dummy 互相连通，而那些需要被替换的 O 与 dummy 不连通。
 */
class Solution {
    public void solve(char[][] board) {
        if (board.length == 0) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        UnionFind unionFind = new UnionFind(m * n + 1);
        int dummy = m * n;

        for (int i = 0; i < m ; i ++) {
            if(board[i][0] == 'O') {
                unionFind.union(i * n , dummy);
            }
            if (board[i][n - 1] == 'O') {
                unionFind.union( i * n + n - 1, dummy);
            }
        }

        for (int j = 0; j < n; j ++) {
            if (board[0][j] == 'O') {
                unionFind.union(j, dummy);
            }
            if (board[m - 1][j] == 'O') {
                unionFind.union(n * (m - 1) + j, dummy);
            }
        }


        //使用方向数组来进行搜索。将非边缘的O与上下左右的O连通起来。
        int[][] dirct = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int i = 1; i < m - 1; i ++) {
            for (int j = 1; j < n - 1; j ++) {
                if (board[i][j] == 'O'){
                    for (int k = 0; k < 4; k ++) {
                        int x = i + dirct[k][0];
                        int y = j + dirct[k][1];
                        if (board[x][y] == 'O') {
                            unionFind.union(x * n + y , i * n + j);
                        }
                    }
                }
            }
        }

        for (int i = 1; i < m - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                if (!unionFind.connected(i * n + j, dummy)) {
                    board[i][j] = 'X';
                }
            }
        }

    }
}