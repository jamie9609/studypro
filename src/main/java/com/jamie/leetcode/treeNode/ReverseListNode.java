package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * @PackageName: com.jamie.leetcode.treeNode
 * @ClassName: ReverseListNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/16 10:04 下午
 */
public class ReverseListNode {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListNode{
        int val;
        ListNode next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;

        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }


    public ListNode reverseGroup(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = head;

        while (cur != tail) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode start = head;
        ListNode end = head;
        for(int i = 0; i < k; i ++) {
            if (end == null ) {
                return head;
            }
            end = end.next;
        }
        ListNode newHead = reverseGroup(start, end);

        start.next = reverseKGroup(end, k);
        return newHead;
    }

    /**
     * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
     * @param head
     * @return
     */
    //最简单的方法：反转链表做比较
    public static boolean isPalindrome2(ListNode head) {
        ListNode tmpNode = new ListNode();
        tmpNode.next = head;

        ListNode listNode = deepCopy(head);

        ListNode pre, cur, next;
        pre = null;
        cur = head;
        while (Objects.nonNull(cur)) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        while (Objects.nonNull(listNode)) {
            if (listNode.val != pre.val) {
                return false;
            }
            pre = pre.next;
            listNode = listNode.next;
        }
        return true;
    }

    public static ListNode deepCopy(ListNode h) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        while (h != null) {
            linkedList.add(h.val);
            h = h.next;
        }

        ListNode pre = null;
        ListNode newHead;
        while (!linkedList.isEmpty()) {
            newHead = new ListNode( linkedList.pollLast(), pre);
            pre = newHead;
        }
        return pre;
    }

    public static boolean isPalindrome(ListNode head) {
        if (Objects.isNull(head)) {
            return false;
        }
        ListNode left = head, right = head;

        while (Objects.nonNull(right) && Objects.nonNull(right.next) && Objects.nonNull(left.next)) {
            left = left.next;
            right = right.next.next;
        }
        if (Objects.nonNull(right) ) {
            left = left.next;
        }
        ListNode leftFlag = head;

        ListNode rightFlag = reverse2(left);

        while (Objects.nonNull(rightFlag)) {
            if (leftFlag.val != rightFlag.val) {
                return false;
            }
            leftFlag = leftFlag.next;
            rightFlag = rightFlag.next;
        }
        return true;
    }

    public static ListNode reverse2(ListNode head) {
        ListNode pre, cur, next;
        pre = null;
        cur = head;
        while (Objects.nonNull(cur)) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static List<Integer> getNodeList(ListNode listNode) {
        List<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        return list;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, null);
        /*ListNode node3 = new ListNode(2, null);
        ListNode node4 = new ListNode(1, null);*/
        node1.next = node2;
        /*node2.next = node3;
        node3.next = node4;*/



        //System.out.println(getNodeList(deepCopy(node1)));

        System.out.println(isPalindrome2(node1));
        //System.out.println(getNodeList(node1));

    }

}
