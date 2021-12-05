package com.jamie.leetcode.dataStructure;

import java.util.Stack;

/**
 * 请你仅使用两个栈实现先入先出队列。队列应当支持一般队列支持的所有操作
 * @PackageName: com.jamie.leetcode.dataStructure
 * @ClassName: StackQueueDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/4 2:23 下午
 */
public class StackQueueDemo {
    private Stack<Integer> s1, s2;

    public StackQueueDemo() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void push(int x) {
        s1.push(x);
    }

    public int peek() {
        if (s2.isEmpty())
            // 把 s1 元素压入 s2
            while (!s1.isEmpty())
                s2.push(s1.pop());
        return s2.peek();
    }

    public int pop() {
        peek();
        return s2.pop();
    }

    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
