package com.jamie.leetcode.graph;

/**
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: UnionFindCase1
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/29 3:34 下午
 */
public class UnionFindCase1 {
    /**
     * 给定一个由表示变量之间关系的字符串方程组成的数组，每个字符串方程 equations[i] 的长度为 4，并采用两种不同的形式之一："a==b" 或"a!=b"。在这里，a 和 b 是小写字母（不一定不同），表示单字母变量名。
     * 只有当可以将整数分配给变量名，以便满足所有给定的方程时才返回true，否则返回 false。
     *
     * 用并查集的方法解决十分优雅：将 equations 中的算式根据 == 和 != 分成两部分，先处理 == 算式，使得他们通过相等关系各自勾结成门派（连通分量）；然后处理 != 算式，检查不等关系是否破坏了相等关系的连通性。
     * @param equations
     * @return
     */
    public static boolean equationsPossible(String[] equations) {
        UF uf = new UF(26);
        if (equations.length == 0) {
            return false;
        }
        for (int i = 0; i < equations.length; i ++) {
            if (equations[i].charAt(1) == '=') {
                uf.union(equations[i].charAt(0) - 'a', equations[i].charAt(3) - 'a');
            }
        }

        for (int i = 0; i < equations.length; i ++) {
            if (equations[i].charAt(1) == '!') {
                if (uf.connected(equations[i].charAt(0) - 'a', equations[i].charAt(3) - 'a')) {
                    return false;
                }

            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] strings = new String[3];
        strings[0] = "a==b";
        strings[1] = "b!=c";
        strings[2] = "c==a";
        System.out.println(equationsPossible(strings));
    }

}

class UF {
    public int count;
    public int[] parent;

    public UF(int n) {
        this.count = n;
        parent = new int[n];
        for (int i = 0; i < n ; i ++) {
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
        count --;
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