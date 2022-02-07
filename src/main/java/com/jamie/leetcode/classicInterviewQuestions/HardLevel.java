package com.jamie.leetcode.classicInterviewQuestions;

import org.yaml.snakeyaml.util.ArrayStack;

import java.util.*;

/**
 * @PackageName: com.jamie.leetcode.classicInterviewQuestions
 * @ClassName: HardLevel
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/1/5 4:49 下午
 */
public class HardLevel {
    /**
     * 接雨水
     * @param height
     * @return
     */
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        int res = 0;
        int[] l_max = new int[n];
        int[] r_max = new int[n];
        l_max[0] = height[0];
        r_max[n - 1] = height[n - 1];
        // 从左向右计算 l_max
        for (int i = 1; i < n; i++)
            l_max[i] = Math.max(height[i], l_max[i - 1]);
        // 从右向左计算 r_max
        for (int i = n - 2; i >= 0; i--)
            r_max[i] = Math.max(height[i], r_max[i + 1]);

        for (int i = 1; i < n - 1; i++)
            res += Math.min(l_max[i], r_max[i]) - height[i];
        return res;
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int res = 0;
        while (left < right) {
            // [left, right] 之间的矩形面积
            int cur_area = Math.min(height[left], height[right]) * (right - left);
            res = Math.max(res, cur_area);
            // 双指针技巧，移动较低的一边
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return res;
    }

    /**
     * 给你一个字符串 s，找到 s 中最长的回文子串。
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i ++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s, i, i + 1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;

        }
        return res;
    }

    public String palindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
            l--; r++;
        }
        return s.substring(l + 1, r);
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Deque<Character> left = new ArrayDeque<>();

        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                left.push(c);
            } else {
                if (!left.isEmpty()  && left.peek() == leftOf(c))
                    left.pop();
                else
                    return false;
            }
        }
        return left.isEmpty();
    }
    public char leftOf(char c) {
        if (c == '}') {
            return '{';
        }
        if (c == ')') {
            return '(';
        }
        return '[';
    }

    /**
     * 给定一个由 '(' 和 ')' 括号组成的字符串 S，我们需要添加最少的括号（ '(' 或是 ')'，可以在任何位置），以使得到的括号字符串有效。
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        // res 记录插入次数
        int res = 0;
        // need 变量记录右括号的需求量
        int need = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                need++;
            }
            if (s.charAt(i) == ')') {
                need--;
                if (need == -1) {
                    need = 0;
                    res++;
                }
            }
        }
        return res + need;
    }

    /**
     * 给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：
     * 1\任何左括号 '(' 必须对应两个连续的右括号 '))' 。
     * 2\左括号 '(' 必须在对应的连续两个右括号 '))' 之前。
     * @param s
     * @return
     */
    public int minInsertions(String s) {
        //need 记录需右括号的需求量
        // res 记录插入次数
        int res = 0, need = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                need += 2;
                if (need % 2 == 1) {
                    res ++ ;
                    need-- ;
                }
            }

            if (s.charAt(i) == ')') {
                need--;
                if (need == -1) {
                    res++;
                    need = 1;
                }
            }
        }
        return res + need;
    }

    /**
     * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
     * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
     *
     * 解答方法：
     * 获取最左下和最右上的坐标来计算面积，并与所有矩形面积之和比较
     *
     * 若不相等： 肯定不能构成完美矩形
     * 若相等： 要么可以构成完美矩形，要么必定有重叠部分
     *
     * 若为完美矩形，除了最外面的四个顶点外，其他点必定出现 22 次或 44 次
     *
     * 把每个矩形的四个点放入 setset 检查，每出现 22 遍就移除，最后检查是否只剩下四个顶点
     *
     * @param rectangles
     * @return
     */
    Set<Integer> set;
    public boolean isRectangleCover(int[][] rectangles) {
        long area = 0;
        set = new HashSet();

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for(int[] rec : rectangles){
            int x1 = rec[0], y1 = rec[1], x2 = rec[2], y2 = rec[3];
            area += (long)(x2 - x1) * (y2 - y1);

            if(x1 < minX || y1 < minY){
                minX = x1;
                minY = y1;
            }
            if(x2 > maxX || y2 > maxY){
                maxX = x2;
                maxY = y2;
            }

            put(getKey(x1, y1));
            put(getKey(x1, y2));
            put(getKey(x2, y1));
            put(getKey(x2, y2));
        }

        if( area != ((long)(maxX - minX) * (maxY - minY))) {
            return false;
        }
        return set.size() == 4 && set.contains(getKey(minX, minY))
                && set.contains(getKey(minX, maxY))
                && set.contains(getKey(maxX, minY))
                && set.contains(getKey(maxX, maxY));
    }

    private void put(int key){
        if(!set.add(key)) {
            set.remove(key);
        }
    }

    private int getKey(int x, int y){
        return x * 100001 + y;
    }

}
