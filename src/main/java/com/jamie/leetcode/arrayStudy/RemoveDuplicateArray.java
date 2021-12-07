package com.jamie.leetcode.arrayStudy;

import java.util.HashMap;
import java.util.Stack;

/**
 * 数组去重系列
 * @PackageName: com.jamie.leetcode
 * @ClassName: RemoveDuplicateArray
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/12/7 3:24 下午
 */
public class RemoveDuplicateArray {
    /**
     * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
     * @param s
     * @return
     */

    public static String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Integer> cMap = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) {
            cMap.put(s.charAt(i), cMap.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (int i = 0; i < s.length(); i ++) {

            if (stack.contains(s.charAt(i))) {
                cMap.put(s.charAt(i), cMap.getOrDefault(s.charAt(i), 0)  - 1);
                continue;
            }
            while (!stack.isEmpty() && stack.peek() > s.charAt(i)) {
                if (cMap.get(stack.peek()) > 0) {
                    stack.pop();
                } else {
                    break;
                }
            }

            stack.push(s.charAt(i));
            cMap.put(s.charAt(i), cMap.getOrDefault(s.charAt(i), 0)  - 1);
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println( removeDuplicateLetters("bbcaac"));
    }
}
