package com.jamie.leetcode.topN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.topN
 * @ClassName: Demo2
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/1/5 11:06 上午
 */
public class Demo2 {
    /**
     * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
     *
     * 只有当c <= a且b <= d时，我们才认为区间[a,b) 被区间[c,d) 覆盖。
     *
     * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/remove-covered-intervals
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]){
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        int left = intervals[0][0];
        int right = intervals[0][1];

        int res = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] intv = intervals[i];
            // 情况一，找到覆盖区间
            if (left <= intv[0] && right >= intv[1]) {
                res++;
            }
            // 情况二，找到相交区间，合并
            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }
            // 情况三，完全不相交，更新起点和终点
            if (right < intv[0]) {
                left = intv[0];
                right = intv[1];
            }
        }
        return intervals.length - res;
    }

    /**
     * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]){
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        int left = intervals[0][0];
        int right = intervals[0][1];

        List<int[]> res = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            int[] intv = intervals[i];
            // 情况一，找到覆盖区间
            if (left <= intv[0] && right >= intv[1]) {
                continue;
            }
            // 情况二，找到相交区间，合并
            if (right >= intv[0] && right <= intv[1]) {
                right = intv[1];
            }
            // 情况三，完全不相交，更新起点和终点
            if (right < intv[0]) {
                res.add(new int[]{left, right});
                left = intv[0];
                right = intv[1];
            }
        }
        res.add(new int[]{left, right});

        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i ++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    /**
     * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList ，其中 firstList[i] = [starti, endi] 而secondList[j] = [startj, endj] 。每个区间列表都是成对 不相交 的，并且 已经排序 。返回这 两个区间列表的交集 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/interval-list-intersections
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param firstList
     * @param secondList
     * @return
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        int i = 0, j = 0;
        List<int[]> res = new ArrayList<>();

        while (i < firstList.length && j < secondList.length) {
            int a1 = firstList[i][0];
            int a2 = firstList[i][1];
            int b1 = secondList[j][0];
            int b2 = secondList[j][1];
            if (b2  >=  a1 && a2 >= b1) {
                res.add(new int[]{Math.max(a1, b1), Math.min(a2, b2)});
            }
            if (b2 < a2) {
                j += 1;
            } else {
                i += 1;
            }
        }

        int[][] ans = new int[res.size()][2];
        for (int k = 0; k < res.size(); k ++) {
            ans[k] = res.get(k);
        }
        return ans;
    }
}
