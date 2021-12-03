package com.jamie.leetcode.dataStructure;

import java.util.HashMap;
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

    /**
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。
     * 请你找出 nums1 中每个元素在 nums2 中的下一个比其大的值。
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stackHelpers = new Stack<>();
        int[] ans = new int[nums1.length];
        HashMap<Integer, Integer> value2Ans = new HashMap<>();

        for (int i = nums2.length - 1; i >= 0; i --) {

            while (!stackHelpers.isEmpty() && nums2[i] >= stackHelpers.peek()) {
                stackHelpers.pop();
            }
            if (stackHelpers.isEmpty()) {
                value2Ans.put(nums2[i] , -1);
            } else {
                value2Ans.put(nums2[i] , stackHelpers.peek());
            }
            stackHelpers.push(nums2[i]);
        }

        for (int i = 0; i < nums1.length; i ++) {
            ans[i] = value2Ans.get(nums1[i]);
        }
        return ans;
    }


    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        int[] case2 = new int[]{137,59,92,122,52,131,79,236};
        int[] case1 = new int[]{137,59,92,122,52,131,79,236};
        for (int x : nextGreaterElement(case1, case2)) {
            sb.append(x);
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

}
