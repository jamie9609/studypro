package com.jamie.leetcode.dataStructure;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.dataStructure
 * @ClassName: MonotoneQueue
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/3 4:36 下午
 */
public class MonotoneQueue {

    /**
     * 使用单调队列。用双向链表来实现。
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * 返回滑动窗口中的最大值。
     * @param nums
     * @param k
     * @return
     */

    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                //先把窗口的前 k - 1 填满
                window.push(nums[i]);
            } else {
                // 窗口开始向前滑动
                // 移入新元素
                window.push(nums[i]);
                // 将当前窗口中的最大元素记入结果
                res.add(window.max());
                // 移出最后的元素
                window.pop(nums[i - k + 1]);
            }
        }
        // 将 List 类型转化成 int[] 数组作为返回值
        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    class MonotonicQueue {
        private LinkedList<Integer> q = new LinkedList<>();
        // 在队尾添加元素 n
        void push(int n) {
            while (!q.isEmpty() && q.getLast() < n) {
                q.pollLast();
            }
            q.addLast(n);
        }
        // 返回当前队列中的最大值
        int max() {
            return q.getFirst();
        };
        // 队头元素如果是 n，删除它
        void pop(int n) {
            if (q.getFirst() == n) {
                q.removeFirst();
            }
        };
    }
    

}
