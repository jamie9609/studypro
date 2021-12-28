package com.jamie.leetcode.bitOperation;

/**
 * @PackageName: com.jamie.leetcode.bitOperation
 * @ClassName: easySolution
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/27 7:41 下午
 */
public class EasySolution {
    /**
     * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数
     * （也被称为汉明重量）
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            n = n & (n - 1);
            res++;
        }
        return res;
    }

    /**
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int n : nums) {
            res ^= n;
        }
        return res;
    }

    /**
     * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
     * @param n
     * @return
     */
    public int trailingZeroes2(int n) {
        int flag = 5;
        int res = 0;
        while (flag <= n) {
            res += n / flag;
            flag *= 5;
        }
        return res;
    }

    /**
     * f(x) 是 x! 末尾是 0 的数量。（回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 ）
     * 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。给定 K，找出多少个非负整数 x ，能满足 f(x) = K 。
     * @param k
     * @return
     */
    public int preimageSizeFZF(int k) {
        return (int)(right_bound(k) - left_bound(k) + 1);
    }

    /* 搜索 trailingZeroes(n) == K 的左侧边界 */
    long left_bound(int target) {
        long lo = 0, hi = 5 * target;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (trailingZeroes(mid) < target) {
                lo = mid + 1;
            } else if (trailingZeroes(mid) > target) {
                hi = mid;
            } else {
                hi = mid;
            }
        }
        return lo;
    }

    /* 搜索 trailingZeroes(n) == K 的右侧边界 */
    long right_bound(int target) {
        long lo = 0, hi = target * 5;
        while (lo < hi) {
            long mid = lo + (hi - lo) / 2;
            if (trailingZeroes(mid) < target) {
                lo = mid + 1;
            } else if (trailingZeroes(mid) > target) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo - 1;
    }

    public int trailingZeroes(long n) {
        int flag = 5;
        int res = 0;
        while (flag <= n) {
            res += n / flag;
            flag *= 5;
        }
        return res;
    }


}
