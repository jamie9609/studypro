package com.jamie.leetcode.dataStructure;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/**
 * 
 * 实现 FreqStack，模拟类似栈的数据结构的操作的一个类。
 * FreqStack 有两个函数：
 * push(int x)，将整数 x 推入栈中。
 * pop()，它移除并返回栈中出现最频繁的元素。
 * 如果最频繁的元素不只一个，则移除并返回最接近栈顶的元素。
 *
 * @PackageName: com.jamie.leetcode.dataStructure
 * @ClassName: FreqStack
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/1 9:03 下午
 */
public class FreqStack {
    HashMap<Integer, Integer> key2Freq;
    HashMap<Integer, LinkedList<Integer>> freq2Key;
    int maxCount;
    public FreqStack() {
        maxCount = 0;
        key2Freq = new HashMap<>();
        freq2Key = new HashMap<>();
    }

    public void push(int val) {
        if (key2Freq.containsKey(val)) {
            int count = key2Freq.get(val);
            if (freq2Key.containsKey(count + 1)) {
                freq2Key.get(count + 1).addFirst(val);
            } else {
                LinkedList<Integer> tmp = new LinkedList<>();
                tmp.add(val);
                freq2Key.put(count + 1, tmp);
                maxCount ++;
            }
            key2Freq.put(val, key2Freq.get(val) + 1);
        }else {
            key2Freq.put(val, 1);
            if (freq2Key.containsKey(1)){
                freq2Key.get(1).addFirst(val);
            } else {
                LinkedList<Integer> tmp = new LinkedList<>();
                tmp.add(val);
                freq2Key.put(1, tmp);
                maxCount ++;
            }
        }
    }

    public int pop() {
        int first = freq2Key.get(maxCount).removeFirst();
        if (freq2Key.get(maxCount).isEmpty()) {
            freq2Key.remove(maxCount);
            maxCount --;
        }
        if (key2Freq.get(first) == 1) {
            key2Freq.remove(first);
        }else {
            key2Freq.put(first, key2Freq.get(first) -1);
        }

        return first;
    }

    public static void main(String[] args) {
        FreqStack freqStack = new FreqStack();
        int[] case1 = new int[]{4,0,9,3,4,2};
        for (int i : case1) {
            freqStack.push(i);
        }
        freqStack.pop();
        freqStack.push(1);
        freqStack.pop();
        freqStack.push(1);
        freqStack.pop();
        freqStack.push(4);
        System.out.println(freqStack.maxCount);
        System.out.println(freqStack.freq2Key.get(2));
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();
        freqStack.pop();

    }
}
