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

    /**
     * 二分法的活学活用：从题目中抽象出一个自变量 x，一个关于 x 的函数 f(x)，以及一个目标值 target。
     * 如果是单调递增或者递减函数，就可以用二分求解。
     * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
     * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/koko-eating-bananas
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param piles
     * @param h
     * @return
     */

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1000000000 + 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (f(piles, mid) <= h) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    // 定义：速度为 x 时，需要 f(x) 小时吃完所有香蕉，f(x) 随着 x 的增加单调递减
    public int f(int[] piles, int x) {
        int hours = 0;
        for (int i = 0; i < piles.length; i++) {
            hours += piles[i] / x;
            if (piles[i] % x > 0) {
                hours++;
            }
        }
        return hours;
    }


    /**
     * 传送带上的包裹必须在 D 天内从一个港口运送到另一个港口。
     * 传送带上的第 i 个包裹的重量为 weights[i]。每一天，我们都会按给出重量的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     * 返回能在 D 天内将传送带上的所有包裹送达的船的最低运载能力。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param weights
     * @param days
     * @return
     */
    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 1;
        //每次搬运的左边界或者右边界
        for (int w : weights) {
            left = Math.max(left, w);
            right += w;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (daysFunc(weights, mid) <= days) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     *
     */
    public static int daysFunc(int[] weights, int coverWeight) {
        int days = 0;
        int bucket = 0;
        for (int i = 0; i < weights.length; i ++) {
            if (i == weights.length - 1 && bucket > 0) {
                days ++;
            }
            if (bucket + weights[i] <= coverWeight) {
                bucket += weights[i];
                continue;
            }
            days ++;
            bucket = weights[i];
        }
        return days;
    }

    /**
     * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
     * 设计一个算法使得这 m 个子数组各自和的最大值最小。
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m) {
        int lo = getMax(nums), hi = getSum(nums) + 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int n = split(nums, mid);
            if (n == m) {
                hi = mid;
            } else if (n < m) {
                hi = mid;
            } else if (n > m) {
                lo = mid + 1;
            }
        }
        return lo;
    }

    /* 辅助函数，若限制最大子数组和为 max，
    计算 nums 至少可以被分割成几个子数组 */
    public int split(int[] nums, int max) {
        // 至少可以分割的子数组数量
        int count = 1;
        // 记录每个子数组的元素和
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > max) {
                // 如果当前子数组和大于 max 限制
                // 则这个子数组不能再添加元素了
                count++;
                sum = nums[i];
            } else {
                // 当前子数组和还没达到 max 限制
                // 还可以添加元素
                sum += nums[i];
            }
        }
        return count;
    }

    // 计算数组中的最大值
    public int getMax(int[] nums) {
        int res = 0;
        for (int n : nums)
            res = Math.max(n, res);
        return res;
    }

    // 计算数组元素和
    int getSum(int[] nums) {
        int res = 0;
        for (int n : nums)
            res += n;
        return res;
    }
    public static void main(String[] args) {
        int[] case1 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(daysFunc(case1, 16));
    }


}
