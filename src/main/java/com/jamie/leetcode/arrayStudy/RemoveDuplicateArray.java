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

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
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

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        slow.next = null;
        return head;
    }


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        slow.next = null;
        return head;
    }

    public void moveZeroes(int[] nums) {

        int p = removeElement(nums, 0);
        for (; p < nums.length; p++) {
            nums[p] = 0;
        }
    }

    public int removeElement(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }
        return slow;
    }

    public static void main(String[] args) {
        System.out.println( removeDuplicateLetters("bbcaac"));
    }
}
