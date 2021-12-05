package com.jamie.leetcode.dataStructure;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

/**
 * 用队列实现栈就比较简单粗暴了，只需要一个队列作为底层数据结构。
 * @PackageName: com.jamie.leetcode.dataStructure
 * @ClassName: StackQueueDemo2
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/4 2:35 下午
 */
public class StackQueueDemo2 {
    Queue<Integer> q = new LinkedList<>();
    int top_elem = 0;

    /** 添加元素到栈顶 */
    public void push(int x) {
        // x 是队列的队尾，是栈的栈顶
        q.offer(x);
        top_elem = x;
    }

    /** 返回栈顶元素 */
    public int top() {
        return top_elem;
    }
    /** 删除栈顶的元素并返回 */
    public int pop() {
        int size = q.size();
        while (size > 1) {
            if (size == 2) {
                top_elem = q.peek();
            }
            q.offer(q.poll());
            size--;
        }
        return q.poll();
    }
    /** 判断栈是否为空 */
    public boolean empty() {
        return q.isEmpty();
    }
}
