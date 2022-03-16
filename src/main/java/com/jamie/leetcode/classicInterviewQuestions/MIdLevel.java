package com.jamie.leetcode.classicInterviewQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * @PackageName: com.jamie.leetcode.classicInterviewQuestions
 * @ClassName: MIdLevel
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/16 2:53 下午
 */
public class MIdLevel {

    /**
     * 给定两个以字符串形式表示的非负整数num1和num2，返回num1和num2的乘积，它们的乘积也表示为字符串形式。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/multiply-strings
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        int[] res = new int[num1.length() + num2.length()];
        for(int i = num1.length() - 1; i >= 0; i --) {
            for(int j  = num2.length() - 1; j >= 0 ; j --) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int tmpRes = res[p2] + mul;
                res[p2] = tmpRes % 10;
                res[p1] += tmpRes / 10;
            }
        }

        int i = 0;
        while (i < res.length && res[i] == 0) {
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for ( ; i < res.length; i++) {
            sb.append(res[i]);
        }
        return sb.length() == 0 ? "0" : sb.toString();
    }

    /**
     * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
     * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
     * 链接：https://leetcode-cn.com/problems/is-subsequence
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        int m = s.length();
        int n = t.length();
        List<Integer>[] index = new ArrayList[256];
        for (int i = 0; i < n; i++) {
            char c = t.charAt(i);
            if (index[c] == null) {
                index[c] = new ArrayList<>();
            }
            index[c].add(i);
        }
        //字符串t上的指针
        int j = 0;
        for (int i = 0; i < m; i ++) {
            char c = s.charAt(i);
            if (index[c] == null) {
                return false;
            }
            int position = left_bound(index[c], j);
            if (position == index[c].size()) {
                return false;
            }
            j = index[c].get(position) + 1;

        }
        return true;
    }

    //二分查找
    public static int left_bound(List<Integer> list, int tar) {
        int left = 0, right = list.size();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) < tar) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }


    public static void main(String[] args) {
        System.out.println(multiply("123", "456"));
    }
}
