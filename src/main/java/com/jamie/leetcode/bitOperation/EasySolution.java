package com.jamie.leetcode.bitOperation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

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

    /**
     * 统计所有小于非负整数 n 的质数的数量。
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i < n; i++) {
            // i 的倍数不可能是素数了
            if (isPrime[i]) {
                for (int j = 2 * i; j < n; j += i)
                    isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }

    public boolean isPrime(int n) {
        for (int i = 2; i < n ; i ++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    int base = 1337;
    public int superPow(int a, int[] b) {
        if (b.length == 0) {
            return 1;
        }
        LinkedList<Integer> nums = new LinkedList<>();
        for (int i = 0 ; i < b.length; i ++) {
            nums.addFirst(b[i]);
        }
        return helper2(a, nums);
    }
    // 计算 a 的 k 次方然后与 base 求模的结果
    public int mypow(int a, int k) {
        a %= base;
        int res = 1;

        for (int i = 0; i < k; i ++) {
            res *= a;
            res %= base;
        }
        return res;
    }

    public int helper2(int a, LinkedList<Integer> nums) {
        if (nums.isEmpty()) {
            return 1;
        }
        Integer first = nums.removeFirst();
        int part1 = mypow(a, first);
        int part2 = mypow(helper2(a, nums), 10);
        return (part1 * part2) % base;
    }

    public int missingNumber(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int tmp = (1 + n) * n /2;
        int res = 0;
        for (int i : nums) {
            res += i;
        }
        return tmp - res;
    }

    /**
     * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复。
     *
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int dup = -1;
        for (int i = 0; i < n; i++) {
            // 现在的元素是从 1 开始的
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0)
                dup = Math.abs(nums[i]);
            else
                nums[index] *= -1;
        }

        int missing = -1;
        for (int i = 0; i < n; i++)
            if (nums[i] > 0)
                // 将索引转换成元素
                missing = i + 1;

        return new int[]{dup, missing};
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    ListNode head;
    public void Solution(ListNode head) {
        this.head = head;
    }

    /**
     * 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。
     * @return
     */
    public int getRandom() {
        Random random = new Random();
        int i = 0, res = 0;
        ListNode p = head;
        // while 循环遍历链表
        while (p != null) {
            i++;
            // 生成一个 [0, i) 之间的整数
            // 这个整数等于 0 的概率就是 1/i
            if (0 == random.nextInt(i)) {
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }

    /**
     * 返回链表中 k 个随机节点的值
     */
    public int[] getRandom(ListNode head, int k) {
        Random r = new Random();
        int[] res = new int[k];
        ListNode p = head;

        for (int j = 0; j < k && p != null; j ++) {
            res[j] = p.val;
            p = p.next;
        }

        int i = k;

        while (p != null) {
            int j = r.nextInt(++i);
            // 这个整数小于 k 的概率就是 k/i
            if (j < k) {
                res[j] = p.val;
            }
            p = p.next;
        }
        return res;
    }

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != nums[slow]) {
                slow++;
                // 维护 nums[0..slow] 无重复
                nums[slow] = nums[fast];
            }
            fast++;
        }
        // 数组长度为索引 + 1
        return slow + 1;
    }
}
