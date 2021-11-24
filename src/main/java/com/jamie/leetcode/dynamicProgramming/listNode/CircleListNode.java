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


}
