package com.jamie.leetcode.dataStructure;

import java.util.Stack;

/**
 * @PackageName: com.jamie.leetcode.dataStructure
 * @ClassName: MonotoneStack
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/3 8:57 上午
 */
public class MonotoneStack {
    /**
     * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stackHelper = new Stack<>();
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 2 * n - 1; i >= 0; i --) {

            while (!stackHelper.isEmpty() && stackHelper.peek() <= nums[i % n] ) {
                stackHelper.pop();
            }

            ans[i % n] = stackHelper.isEmpty()? -1 : stackHelper.peek();
            stackHelper.push(nums[i % n]);
        }
        return ans;
    }

    /**
     * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     * 与上面不同，此处索引入栈
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stackHelper = new Stack<>();
        int[] ans = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i --) {

            while (!stackHelper.isEmpty() && temperatures[stackHelper.peek()] <= temperatures[i] ) {
                stackHelper.pop();
            }

            ans[i] = stackHelper.isEmpty()? 0 : stackHelper.peek() - i;
            stackHelper.push(i);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] case1 = new int[]{89,62,70,58,47,47,46,76,100,70};
        System.out.println(dailyTemperatures(case1));
    }

}
