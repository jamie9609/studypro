package com.jamie.leetcode.dataStructure;

import java.util.PriorityQueue;

/**
 * 数据流的中位数
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * @PackageName: com.jamie.leetcode.dataStructure
 * @ClassName: MedianFinder
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/2 12:16 上午
 */
public class MedianFinder {
    private PriorityQueue<Integer> small;
    private PriorityQueue<Integer> large;

    public MedianFinder() {
        // 小顶堆
        large = new PriorityQueue<>();
        // 大顶堆
        small = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        if (small.size() >= large.size()) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }

    }

    public double findMedian() {
        if (large.size() < small.size()) {
            return small.peek();
        } else if (large.size() > small.size()){
            return large.peek();
        }
        return (large.peek() + small.peek()) / 2.0;
    }

}
