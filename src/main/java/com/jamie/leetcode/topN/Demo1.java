package com.jamie.leetcode.topN;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @PackageName: com.jamie.leetcode.topN
 * @ClassName: Demo1
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/1/4 7:41 下午
 */
public class Demo1 {
    /**
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        // 小顶堆，堆顶是最小元素
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        for (int e : nums) {
            pQueue.offer(e);
            if (pQueue.size() > k) {
                pQueue.poll();
            }
        }
        return pQueue.peek();
    }


    /**
     * 给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/different-ways-to-add-parentheses
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new LinkedList<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            // 扫描算式 input 中的运算符
            if (c == '-' || c == '*' || c == '+') {
                // 以运算符为中心，分割成两个字符串，分别递归计算
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                // 通过子问题的结果，合成原问题的结果
                for (int a : left) {
                    for (int b : right) {
                        if (c == '+')
                            res.add(a + b);
                        else if (c == '-')
                            res.add(a - b);
                        else if (c == '*')
                            res.add(a * b);
                    }
                }
            }
        }
        // base case
        // 如果 res 为空，说明算式是一个数字，没有运算符
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }

}
