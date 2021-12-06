package com.jamie.leetcode.binarySearch;

/**
 * 二分查找的细节理解：https://labuladong.gitee.io/algo/2/21/57/
 * @PackageName: com.jamie.leetcode.binarySearch
 * @ClassName: BinarySearch
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/6 4:01 下午
 */
public class BinarySearch {

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length -1;

        while (left <= right) {
            int mid = left + (right - left) /2;
            if ( nums[mid] == target ) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
     * 如果数组中不存在目标值 target，返回 [-1, -1]。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int leftSide = 0;
        int rightSide = 0;
        int leftMin = 0;
        int rightMin = nums.length;

        //先找左边界。
        while (leftMin < rightMin) {
            int mid = leftMin + (rightMin - leftMin) /2;
            if ( nums[mid] == target ) {
                rightMin = mid;
            } else if (nums[mid] > target) {
                rightMin = mid;
            } else if (nums[mid] < target) {
                leftMin = mid + 1;
            }
        }

        if (leftMin == nums.length || target != nums[leftMin]) {
            return new int[]{-1, -1};
        }


        if (leftMin == 0 && nums[leftMin] > target) {
            return new int[]{-1, -1};
        }
        leftSide = leftMin;


        //再找右边界。
        int leftMax = 0;
        int rightMax = nums.length;
        while (leftMax < rightMax) {
            int mid = leftMax + (rightMax - leftMax) / 2;
            if (nums[mid] == target) {
                leftMax = mid + 1;
            } else if (nums[mid] > target) {
                rightMax = mid;
            } else if (nums[mid] < target) {
                leftMax = mid + 1;
            }
        }
        rightSide = rightMax - 1;

        return new int[]{leftSide, rightSide};
    }

}
