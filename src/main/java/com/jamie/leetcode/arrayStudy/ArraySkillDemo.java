package com.jamie.leetcode.arrayStudy;

import java.util.*;

/**
 * 查分数组和前缀和数组。
 * @PackageName: com.jamie.leetcode.arrayStudy
 * @ClassName: ArraySkillDemo
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/4 3:02 下午
 */
public class ArraySkillDemo {
    // 前缀和 的使用
    class NumArray {
        // 前缀和数组
        private int[] preSum;

        /* 输入一个数组，构造前缀和 */
        public NumArray(int[] nums) {
            // preSum[0] = 0，便于计算累加和
            preSum = new int[nums.length + 1];
            // 计算 nums 的累加和
            for (int i = 1; i < preSum.length; i++) {
                preSum[i] = preSum[i - 1] + nums[i - 1];
            }
        }

        /* 查询闭区间 [left, right] 的累加和 */
        public int sumRange(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
    }

    /**
     * 前缀和，
     */
    class NumMatrix {
        // preSum[i][j] 记录矩阵 [0, 0, i, j] 的元素和
        private int[][] preSum;

        public NumMatrix(int[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            if (m == 0 || n == 0) return;
            preSum = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    // 计算每个矩阵 [0, 0, i, j] 的元素和
                    preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] + matrix[i - 1][j - 1] - preSum[i-1][j-1];
                }
            }

        }

        public int sumRegion(int row1, int col1, int row2, int col2) {
            return preSum[row2 + 1][col2 + 1] + preSum[row1][col1] - preSum[row1][col2 + 1] - preSum[row2 + 1][col1];
        }
    }

    /**
     * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回该数组中和为 k 的连续子数组的个数。
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum1(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n + 1];
        res[0] = 0;
        for (int i = 0; i < n; i ++) {
            res[i + 1] = res[i] + nums[i];
        }
        int count = 0;
        for (int i = 0; i < n; i ++) {
            for (int j = i; j < n; j ++) {
                if (res[j + 1] - res[i] == k ) {
                    count ++;
                }

            }
        }
        return count;
    }

    /**
     * 用map来优化，省去外层的for循环
     * @param nums
     * @param k
     * @return
     */
    static int subarraySum(int[] nums, int k) {
        int n = nums.length;
        // map：前缀和 -> 该前缀和出现的次数
        HashMap<Integer, Integer>
                preSum = new HashMap<>();
        // base case
        preSum.put(0, 1);

        int res = 0, sum0_i = 0;
        for (int i = 0; i < n; i++) {
            sum0_i += nums[i];
            // 这是我们想找的前缀和 nums[0..j]
            int sum0_j = sum0_i - k;
            // 如果前面有这个前缀和，则直接更新答案
            if (preSum.containsKey(sum0_j))
                res += preSum.get(sum0_j);
            // 把前缀和 nums[0..i] 加入并记录出现次数
            preSum.put(sum0_i,
                    preSum.getOrDefault(sum0_i, 0) + 1);
        }
        return res;
    }

    /**
     * 这里有 n 个航班，它们分别从 1 到 n 进行编号。
     *
     * 有一份航班预订表 bookings ，表中第 i 条预订记录 bookings[i] = [firsti, lasti, seatsi] 意味着在从 firsti 到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi 个座位。
     *
     * 请你返回一个长度为 n 的数组 answer，里面的元素是每个航班预定的座位总数。
     * @param bookings
     * @param n
     * @return
     */
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] flight = new int[n];
        Arrays.fill(flight, 0);
        Difference difference = new Difference(flight);

        for (int i = 0; i < bookings.length; i ++ ) {
            difference.increment(bookings[i][0] - 1,  bookings[i][1] - 1,  bookings[i][2]);
        }
        return difference.result();
    }
    public static class Difference {
        int[] difference;
        public Difference(int[] nums){
            difference = new int[nums.length + 1];
            Arrays.fill(difference, 0);
            difference[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                difference[i] = nums[i] - nums[i - 1];
            }
        }

        /* 给闭区间 [i,j] 增加 val（可以是负数）*/
        public void increment(int i, int j, int val) {
            difference[i] += val;
            if (j < difference.length -1) {
                difference[j + 1] -= val;
            }
        }

        public int[] result() {
            int[] res = new int[difference.length];
            res[0] = difference[0];
            for (int i = 1; i < difference.length; i++) {
                res[i] = res[i - 1] + difference[i];
            }
            return res;
        }

    }


    /**
     * 你是一个开公交车的司机，公交车的最大载客量为 capacity，沿途要经过若干车站，给你一份乘客行程表 int[][] trips，其中 trips[i] = [num, start, end] 代表着有 num 个旅客要从站点 start 上车，到站点 end 下车，请你计算是否能够一次把所有旅客运送完毕（不能超过最大载客量 capacity）。
     * @param trips
     * @param capacity
     * @return
     */
    public static boolean carPooling(int[][] trips, int capacity) {
        // 最多有 1000 个车站
        int[] nums = new int[1001];
        Difference difference = new Difference(nums);

        for (int i = 0; i < trips.length; i ++) {
            difference.increment(trips[i][1], trips[i][2] - 1, trips[i][0]);
        }
        int[] result = difference.result();
        for (int item :result) {
            System.out.println(item);
            if (item > capacity) {
                return false;
            }
        }
        return true;
    }


    public int[] twoSum(int[] numbers, int target) {
        if (numbers.length == 0) {
            return null;
        }
        int start = 0;
        int end = numbers.length - 1;

        while (start < end) {
            if (numbers[start] + numbers[end] == target) {
                break;
            }
            if (numbers[start] + numbers[end] > target) {
                end --;
            } else {
                start ++ ;
            }
        }

        int[] ans = new int[2];
        ans[0] = start + 1;
        ans[1] = end + 1;
        return ans;

    }

    /**
     * 反转数组，用双指针。
     * @param s
     */
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            // 交换 arr[left] 和 arr[right]
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++; right--;
        }
    }

    /**
     * 最小覆盖子串
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>();
        HashMap<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i ++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }

        int left = 0, right = 0;
        int len = s.length();
        // 记录最小覆盖子串的起始索引及长度
        int start = 0, lenStr = Integer.MAX_VALUE;
        //满足 need 条件的字符个数
        int valid = 0;
        //移动窗口，左闭右开的区间
        while (right < len) {
            char rightChar = s.charAt(right);
            right ++;

            if ( need.containsKey(rightChar)) {
                window.put(rightChar, window.getOrDefault(rightChar, 0) + 1);
                if (window.get(rightChar).equals(need.get(rightChar))) {
                    valid ++;
                }
            }
            while (valid == need.size()) {

                if (right - left < lenStr) {
                    start = left;
                    lenStr = right -left;
                }
                char deleteChar = s.charAt(left);
                left ++;

                if (need.containsKey(deleteChar)) {
                    if (window.get(deleteChar).equals(need.get(deleteChar))){
                        valid --;
                    }
                    window.put(deleteChar, window.getOrDefault(deleteChar, 0) - 1);
                }
            }
        }

        return  lenStr == Integer.MAX_VALUE ? "" : s.substring(start, start + lenStr);

    }

    /**
     * 给你两个字符串  s1  和  s2 ，写一个函数来判断 s2 是否包含 s1  的排列。如果是，返回 true ；否则，返回 false 。
     * 换句话说，s1 的排列之一是 s2 的 子串 。
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkInclusion(String s1, String s2) {
        if (s1.length() == 0 ) {
            return true;
        }
        if (s1.length() > s2.length()) {
            return false;
        }
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> target = new HashMap<>();
        for (int i = 0; i < s1.length(); i ++) {
            target.put( s1.charAt(i), target.getOrDefault(s1.charAt(i), 0) + 1);
        }
        //左闭右开的区间
        int left = 0, right = s1.length();

        for (int i = 0; i < right; i ++) {
            window.put( s2.charAt(i), window.getOrDefault(s2.charAt(i), 0) + 1);
        }
        if (equalMap(window, target)) {
            return true;
        }

        while ( right < s2.length()) {
            Character rightVal = s2.charAt(right);
            Character leftVal = s2.charAt(left);
            right ++;
            left ++;
            window.put(rightVal, window.getOrDefault(rightVal, 0)  + 1);
            if(window.get(leftVal) > 1) {
                window.put(leftVal, window.get(leftVal) - 1);
            } else {
                window.remove(leftVal);
            }
            if (equalMap(window, target)) {
                return true;
            }
        }
        return false;
    }


    public static boolean equalMap ( Map<Character, Integer> window, Map<Character, Integer> target) {
        if (window.isEmpty() && target.isEmpty()) {
            return true;
        }

        if (window.size() != target.size()) {
            return false;
        }

        for (Map.Entry<Character, Integer> item : window.entrySet()) {
            if (!target.containsKey(item.getKey())) {
                return false;
            }
            if (!target.get(item.getKey()).equals(item.getValue())) {
                return false;
            }
        }
        return true;
    }


    /**
     * 给定两个字符串s 和 p，找到 s 中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> window = new HashMap<>();
        Map<Character, Integer> target = new HashMap<>();
        if (p.length() == 0 || s.length() == 0) {
            return new LinkedList<>();
        }

        int left = 0, right = 0;

        List<Integer> ans = new ArrayList<>();

        //满足条件的字符个数
        int valid = 0;

        for (int i = 0; i < p.length(); i ++) {
            target.put(p.charAt(i), target.getOrDefault(p.charAt(i), 0) + 1);
        }

        while (right < s.length()) {
            char rightVal = s.charAt(right);
            right ++;
            if (target.containsKey(rightVal)) {
                window.put(rightVal, window.getOrDefault(rightVal, 0 ) + 1);
                if (window.get(rightVal).equals(target.get(rightVal))) {
                    valid ++;
                }
            }
            if (right - left <= p.length()) {
                if (valid == target.size()) {
                    ans.add(left);
                }
                continue;
            }

            char leftVal = s.charAt(left);
            left ++;
            if (target.containsKey(leftVal)) {
                if (window.get(leftVal).equals(target.get(leftVal))) {
                    valid --;
                }
                window.put(leftVal, window.getOrDefault(leftVal, 0 ) - 1);
            }
            if (valid == target.size()) {
                ans.add(left);
            }

        }
        return ans;
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> target = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        if (s.length() == 0) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        while (right < s.length()) {
            char rightVal = s.charAt(right);
            char leftVal = s.charAt(left);
            right ++;
            if (!target.containsKey(rightVal)) {
                ans = Math.max(ans, right - left);
                target.put(rightVal, 1);
            }else {
                left ++;
                right --;
                target.remove(leftVal);
            }
        }
        return ans;
    }


    public static void main(String[] args) {

        /*int[] int1 = {9,0,1};
        int[] int2 = {3,3,7};
        int[][] res = new int[][]{int1,int2};

        System.out.println( carPooling(res, 3));*/
        String s = "bbbbbb";
        String p = "b";
        //System.out.println(findAnagrams(s, p));
        System.out.println(lengthOfLongestSubstring(s));
    }
}
