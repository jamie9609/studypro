package com.jamie.leetcode.dynamicProgramming.listNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @PackageName: com.jamie.leetcode.dynamicProgramming.listNode
 * @ClassName: mergeLists
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/23 10:28 下午
 */
public class NoCircleListNode {

    @NoArgsConstructor
    @AllArgsConstructor
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) {
            return l2;
        }
        if (Objects.isNull(l2)) {
            return l1;
        }

        ListNode ans = new ListNode(-1, new ListNode());
        ListNode ansFlag = ans;

        ListNode l1Flag = l1;
        ListNode l2Flag = l2;

        while (Objects.nonNull(l1Flag) && Objects.nonNull(l2Flag)) {
            if (l1Flag.val >= l2Flag.val) {
                ans.next = l2Flag;
                l2Flag = l2Flag.next;
            } else {
                ans.next = l1Flag;
                l1Flag = l1Flag.next;
            }
            ans = ans.next;
        }

        if (Objects.nonNull(l2Flag)) {
            ans.next = l2Flag;
        }

        if (Objects.nonNull(l1Flag)) {
            ans.next = l1Flag;
        }
        return ansFlag.next;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    public ListNode middleNode(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        ListNode fast = head, slow = head;

        while (Objects.nonNull(slow.next) && Objects.nonNull(fast.next) && Objects.nonNull(fast.next.next)) {
            slow = slow.next;
            fast = fast.next.next;
        }
        if (Objects.nonNull(fast.next)) {
            return slow;
        }
        return slow.next;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (Objects.isNull(headA) || Objects.isNull(headB)) {
            return null;
        }
        ListNode flagA = headA, flagB = headB;
        int reverse1 = 1, reverse2 = 1;
        while (true) {
            if (flagA.equals(flagB)) {
                return flagA;
            }
            if (Objects.isNull(flagA.next)){
                flagA = headB;
                reverse1 ++;
            } else {
                flagA = flagA.next;
            }

            if (Objects.isNull(flagB.next)) {
                flagB = headA;
                reverse2 ++;
            } else {
                flagB = flagB.next;
            }

            if (reverse1 > 2 && reverse2 > 2) {
                return null;
            }
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (Objects.isNull(head) || n < 1) {
            return null;
        }
        ListNode pre = new ListNode(-1, head);
        ListNode fast = pre, slow = pre;

        for (int i = 1; i <= n ; i ++ ){
            if (Objects.isNull(fast.next)){
                return null;
            }
            fast = fast.next;
        }

        while (Objects.nonNull(fast.next)) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return pre.next;
    }

    public ListNode reverseList(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        if (Objects.isNull(head.next)) {
            return head;
        }
        ListNode list = reverseList(head.next);

        head.next.next = head;
        head.next = null;
        return list;
    }

    /**
     * 反转链表前n个节点
     * @param head
     * @param n
     * @return
     */
    ListNode successor = null;
    public ListNode reverseN (ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;

    }

    /**
     * 反转left到right之间的链表
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        // bad case;
        if (left == 1) {
            // 相当于反转前 n 个元素
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    /**
     * k个一组反转链表。先反转第一组k个链表，后面用递归。
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = head;
        b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null) {
                return head;
            };
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    public ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = a;
        while (cur != b) {
            nxt = cur.next;
            // 逐个结点反转
            cur.next = pre;
            // 更新指针位置
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }

}



