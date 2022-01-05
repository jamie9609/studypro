package com.jamie.leetcode.classicInterviewQuestions;

import java.util.*;

/**
 * @PackageName: com.jamie.leetcode.classicInterviewQuestions
 * @ClassName: spliteNums
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/1/5 2:31 下午
 */
public class spliteNums {


    /**
     * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        HashMap<Integer, Integer> need = new HashMap<>();
        HashMap<Integer, Integer> freq = new HashMap<>();

        for (int item :  nums) {
            freq.put(item, freq.getOrDefault(item, 0) + 1);
        }

        for (int v : nums) {
           if (freq.get(v) == 0) {
               continue;
           }
           // 优先接到其他子序列后面。
           if (need.containsKey(v) && need.get(v) > 0) {
               freq.put(v, freq.getOrDefault(v, 0) - 1);
               need.put(v, need.getOrDefault(v, 0) - 1);
               need.put(v + 1, need.getOrDefault(v + 1, 0) + 1);
           } else if ( freq.get(v) > 0 && freq.containsKey(v + 1) && freq.get(v + 1) > 0 && freq.containsKey(v + 2)&& freq.get(v + 2) > 0) {
               freq.put(v, freq.getOrDefault(v, 0) - 1);
               freq.put(v + 1, freq.getOrDefault(v + 1, 0) - 1);
               freq.put(v + 2, freq.getOrDefault(v + 2, 0) - 1);
               need.put(v + 3, need.getOrDefault(v + 3, 0) + 1);
           } else {
                return false;
           }
        }
        return true;
    }

    /**
     * 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
     * 一次煎饼翻转的执行过程如下：
     * 选择一个整数 k ，1 <= k <= arr.length
     * 反转子数组 arr[0...k-1]（下标从 0 开始）
     * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
     *
     * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
     *
     * @param cakes
     * @return
     */
    // 记录反转操作序列
    private static LinkedList<Integer> res = new LinkedList<>();

    public static List<Integer> pancakeSort(int[] cakes) {
        sort(cakes, cakes.length);
        return res;
    }

    public static void sort(int[] cakes, int n) {
        // base case
        if (n == 1) {
            return;
        }
        // 寻找最大饼的索引
        int maxCake = 0;
        int maxCakeIndex = 0;
        for (int i = 0; i < n; i++) {
            if (cakes[i] > maxCake) {
                maxCakeIndex = i;
                maxCake = cakes[i];
            }
        }
        // 第一次翻转，将最大饼翻到最上面
        reverse(cakes, 0, maxCakeIndex);
        res.add(maxCakeIndex + 1);
        // 第二次翻转，将最大饼翻到最下面
        reverse(cakes, 0, n - 1);
        res.add(n);

        // 递归调用
        sort(cakes, n - 1);
    }

    public static void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    /**
     * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
     * @param n1
     * @param n2
     * @return
     */
    public static String multiply(String n1, String n2) {
        int n = n1.length();
        int m = n2.length();
        int[] res = new int[n + m];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                int a = n1.charAt(i) - '0';
                int b = n2.charAt(j) - '0';
                int r = a * b;
                r += res[i + j + 1];
                res[i + j + 1] = r % 10;
                res[i + j] += r / 10;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n + m; i++) {
            if (sb.length() == 0 && res[i] == 0) {
                continue;
            }
            sb.append(res[i]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }


    /**
     * 基本计算器。给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * @param s
     * @return
     */
    char LEFT = '(';
    char RIGHT = ')';

    public int calculate(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Deque<Character> q = new ArrayDeque<>();

        for (char c : s.toCharArray())
            if (c != ' ') {
                q.offer(c);
            }
        q.offer(' ');   // 确保最后一个 preNum 能被计算
        return helper(q);
    }
    private int helper(Deque<Character> q) {
        int res = 0;
        int preNum = 0;
        int curNum = 0;
        char preOp = '+';

        while (!q.isEmpty()) {
            char c = q.poll();
            if (Character.isDigit(c))  // 数字，比如 235, 568
                curNum = curNum * 10 + c - '0';
            else if (c == LEFT)       // 左括号，直接跟递归要结果
                curNum = helper(q);
            else {                    // 其他符号： + - * / )
                switch(preOp) {
                    case '+':
                        res += preNum;
                        preNum = curNum;
                        break;
                    case '-':
                        res += preNum;
                        preNum = -curNum;
                        break;
                    case '*':
                        preNum = preNum * curNum;
                        break;
                    case '/':
                        preNum = preNum / curNum;
                        break;
                }
                preOp = c;
                curNum = 0;
                if (c == RIGHT) {
                    break;
                }
            }
        }

        res += preNum;  // 别忘了等待本次计算的 preNum
        return res;
    }


    public static void main(String[] args) {
        int[] case1 = {3, 2, 4, 1};
        //System.out.println(pancakeSort(case1));
        System.out.println(multiply("123","456"));
    }
}
