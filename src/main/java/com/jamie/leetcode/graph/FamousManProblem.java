package com.jamie.leetcode.graph;

import java.util.LinkedList;

/**
 * 名流问题
 * 给你 n 个人的社交关系（你知道任意两个人之间是否认识），然后请你找出这些人中的「名人」。
 * 所谓「名人」有两个条件：
 * 1、所有其他人都认识「名人」。
 * 2、「名人」不认识任何其他人。
 * @PackageName: com.jamie.leetcode.graph
 * @ClassName: FamousManProblem
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/30 8:43 上午
 */
public class FamousManProblem {

    /**
     * 先用算法找出一个可能是名人的人，再去验证。
     * 因为依靠两个人的关系，就能排除一个不是名人的人
     * @param n
     * @return
     */
    public int findCelebrity(int n) {
        if (n == 1) {
            return 0;
        }
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i ++) {
            q.addFirst(i);
        }
        //一直排除直到最后一个人停止循环
        while (q.size() >= 2) {
            int cand = q.removeFirst();
            int other = q.removeFirst();
            if (knows(cand, other) || !knows(other, cand)) {
                // cand 不可能是名人，排除，让 other 归队
                q.addFirst(other);
            } else {
                // other 不可能是名人，排除，让 cand 归队
                q.addFirst(cand);
            }
        }
        int cand = q.removeFirst();
        for (int i = 0; i < n; i ++) {
            if (cand == i) {
                continue;
            }
            if (knows(cand, i) || !knows(i, cand)) {
                return -1;
            }
        }

        return cand;
    }

    public boolean knows(int cand, int other) {
        //不需要关注实现细节
        return false;
    }
}
