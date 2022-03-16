package com.jamie.leetcode.classicInterviewQuestions;

import java.util.*;

/**
 * 经典面试题之：括号问题
 * @PackageName: com.jamie.leetcode.classicInterviewQuestions
 * @ClassName: BracketsProblem
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/15 4:41 下午
 */
public class BracketsProblem {
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
     * 有效字符串需满足：
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Character[] leftChars = new Character[]{'(', '{', '['};
        List<Character> leftBrackets = Arrays.asList(leftChars);
        Character[] rightChars = new Character[]{')', '}', ']'};
        List<Character> rightBrackets = Arrays.asList(rightChars);

        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> relationMap = new HashMap<>();
        //建立映射关系
        relationMap.put(')', '(');
        relationMap.put('}', '{');
        relationMap.put(']', '[');

        for (int i = 0; i < s.length(); i ++) {
            if (leftBrackets.contains(s.charAt(i))) {
                stack.push(s.charAt(i));
            } else if (rightBrackets.contains(s.charAt(i))) {
                if (stack.isEmpty()) {
                    return false;
                }
                if (!Objects.equals(stack.pop(), relationMap.get(s.charAt(i)))) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 只有满足下面几点之一，括号字符串才是有效的：
     * 它是一个空字符串，或者
     * 它可以被写成  AB  （A  与  B  连接）, 其中  A 和  B  都是有效字符串，或者
     * 它可以被写作  (A)，其中  A  是有效字符串。
     * 给定一个括号字符串 s ，移动N次，你就可以在字符串的任何位置插入一个括号。
     * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))" 。
     * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
     *
     * 链接：https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid
     * @param s
     * @return
     */
    public int minAddToMakeValid(String s) {
        // res 记录插入次数
        int res = 0;
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

    public static void main(String[] args) {
        System.out.println(isValid("]"));
    }
}
