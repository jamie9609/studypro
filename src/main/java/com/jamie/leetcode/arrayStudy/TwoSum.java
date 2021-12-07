package com.jamie.leetcode.arrayStudy;

import java.util.HashMap;

/**
 * @PackageName: com.jamie.leetcode.arrayStudy
 * @ClassName: TwoSum
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/7 4:43 下午
 */
public class TwoSum {
    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> index = new HashMap<>();
        // 构造一个哈希表：元素映射到相应的索引
        for (int i = 0; i < n; i++)
            index.put(nums[i], i);

        for (int i = 0; i < n; i++) {
            int other = target - nums[i];
            if (index.containsKey(other) && index.get(other) != i)
                return new int[] {i, index.get(other)};
        }

        return new int[] {-1, -1};
    }
}
