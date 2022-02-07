package com.jamie.leetcode.classicInterviewQuestions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * 考场座位
 * 在考场里，一排有 N 个座位，分别编号为 0, 1, 2, ..., N-1 。
 * 当学生进入考场后，他必须坐在能够使他与离他最近的人之间的距离达到最大化的座位上。如果有多个这样的座位，他会坐在编号最小的座位上。(另外，如果考场里没有人，那么学生就坐在 0 号座位上。)
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/exam-room
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @PackageName: com.jamie.leetcode.classicInterviewQuestions
 * @ClassName: ExamRoom
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/1/5 8:21 下午
 */
public class ExamRoom {

    // 将端点 p 映射到以 p 为左端点的线段
    private Map<Integer, int[]> startMap;
    // 将端点 p 映射到以 p 为右端点的线段
    private Map<Integer, int[]> endMap;
    // 根据线段长度从小到大存放所有线段
    private TreeSet<int[]> pq;
    private int N;

    public ExamRoom(int N) {
        this.N = N;
        startMap = new HashMap<>();
        endMap = new HashMap<>();
        pq = new TreeSet<>((a, b) -> {
            // 算出两个线段的长度
            int distA = distance(a);
            int distB = distance(b);
            // 长度更长的更大,排后面,如果长度相同,就比较索引
            if (distA == distB)
                return b[0] - a[0];
            return distA - distB;
        });
        // 在有序集合中先放一个虚拟线段
        addInterval(new int[] {-1, N});
    }

    /* 去除一个线段 */
    private void removeInterval(int[] intv) {
        pq.remove(intv);
        startMap.remove(intv[0]);
        endMap.remove(intv[1]);
    }

    /* 增加一个线段 */
    private void addInterval(int[] intv) {
        pq.add(intv);
        startMap.put(intv[0], intv);
        endMap.put(intv[1], intv);
    }

    /* 计算一个线段的长度 */
    private int distance(int[] intv) {
        int x = intv[0];
        int y = intv[1];
        if (x == -1) return y;
        if (y == N) return N - 1 - x;
        // 中点和端点之间的长度
        return (y - x) / 2;
    }

    public int seat() {
        // 从有序集合拿出最长的线段
        int[] longest = pq.last();
        int x = longest[0];
        int y = longest[1];
        int seat;
        if (x == -1) { // 情况一
            seat = 0;
        } else if (y == N) { // 情况二
            seat = N - 1;
        } else { // 情况三
            seat = (y - x) / 2 + x;
        }
        // 将最长的线段分成两段
        int[] left = new int[] {x, seat};
        int[] right = new int[] {seat, y};
        removeInterval(longest);
        addInterval(left);
        addInterval(right);
        return seat;
    }

    public void leave(int p) {
        // 将 p 左右的线段找出来
        int[] right = startMap.get(p);
        int[] left = endMap.get(p);
        // 合并两个线段成为一个线段
        int[] merged = new int[] {left[0], right[1]};
        removeInterval(left);
        removeInterval(right);
        addInterval(merged);
    }

    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence2(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == s.length();
    }

    public boolean isSubsequence(String s, String t) {
        int m = s.length(), n = t.length();
        // 对 t 进行预处理
        ArrayList<Integer>[] index = new ArrayList[256];
        for (int i = 0; i < n; i++) {
            char c = t.charAt(i);
            if (index[c] == null)
                index[c] = new ArrayList<>();
            index[c].add(i);
        }

        // 串 t 上的指针
        int j = 0;
        // 借助 index 查找 s[i]
        for (int i = 0; i < m; i++) {
            char c = s.charAt(i);
            // 整个 t 压根儿没有字符 c
            if (index[c] == null) return false;
            int pos = left_bound(index[c], j);
            // 二分搜索区间中没有找到字符 c
            if (pos == index[c].size()) {
                return false;
            }
            // 向前移动指针 j
            j = index[c].get(pos) + 1;
        }
        return true;
    }


    // 查找左侧边界的二分查找
    public int left_bound(ArrayList<Integer> arr, int tar) {
        int lo = 0, hi = arr.size();
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (tar > arr.get(mid)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

}
