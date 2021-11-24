package com.jamie.leetcode.dynamicProgramming.listNode;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @PackageName: com.jamie.leetcode.dynamicProgramming.listNode
 * @ClassName: CircleListNode
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2021/11/24 8:46 上午
 */
public class CircleListNode {
    @AllArgsConstructor
    @NoArgsConstructor
    class ListNode {
        int val;
        ListNode next;
    }

    public boolean hasCycle(ListNode head) {
        if (Objects.isNull(head)){
            return false;
        }
        ListNode fast = head, slow = head;

        while (Objects.nonNull(fast.next) && Objects.nonNull(slow.next) && Objects.nonNull(slow.next.next)) {
            fast = fast.next;
            slow = slow.next.next;
            if (slow.equals(fast)) {
                return true;
            }
        }
        return false;
    }


    public ListNode detectCycle(ListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        ListNode fast = head, slow = head;
        while (Objects.nonNull(slow.next) && Objects.nonNull(fast.next) && Objects.nonNull(fast.next.next)) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow.equals(fast)) {
                fast = head;
                while (!fast.equals(slow)) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    /**
     * 反转从a节点到b节点的链表.[a,b),半开半闭区间
     * @param left
     * @param right
     * @return
     */
    public ListNode reverse(ListNode left, ListNode right) {
        ListNode pre, cur, next;
        pre = null;
        cur = left;
        next = left;

        while (cur != right) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /**
     * k个一组反转链表。先反转第一组的k个链表，然后递归反转后面的。
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode left = head, right= head;

        for (int i = 0 ; i < k ; i ++) {
            if (Objects.isNull(right)) {
                return head;
            }
            right = right.next;
        }
        //反转前k个元素
        ListNode reverse = reverse(left, right);
        // 递归反转后续的链表并连接起来
        left.next = reverseKGroup(right, k);
        return reverse;
    }

}
