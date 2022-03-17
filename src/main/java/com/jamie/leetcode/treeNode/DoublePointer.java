package com.jamie.leetcode.treeNode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @PackageName: com.jamie.leetcode.treeNode
 * @ClassName: DoublePointer
 * @Description:
 * @Author: jamiezhangming
 * @Date: 2022/3/17 2:31 下午
 */
public class DoublePointer {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ListNode {
        int val;
        ListNode next;
    }
    /**
     *
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode newHead1 = list1;
        ListNode newHead2 = list2;
        ListNode dummy = new ListNode(-1, null);
        ListNode p = dummy;

        while (newHead1 != null && newHead2 != null) {
            if (newHead1.val > newHead2.val) {
                p.next = newHead2;
                newHead2 = newHead2.next;
            } else {
                p.next = newHead1;
                newHead1 = newHead1.next;
            }
            p = p.next;
        }

        if (newHead1 == null && newHead2 != null) {
            p.next = newHead2;
        }

        if (newHead2 == null && newHead1 != null) {
            p.next = newHead1;
        }
        return dummy.next;
    }

    /**
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     * https://leetcode-cn.com/problems/merge-k-sorted-lists/
     * @param lists
     * @return
     */
    // 笨拙的方法，两两合并
    // 机智的方法，巧妙利用优先级队列，维护一个小顶堆。
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode dummy = new ListNode(-1, null);
        ListNode p = dummy;
        //优先级队列，最小堆
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length ,(a, b)->(a.val - b.val));

        for (ListNode head : lists) {
            if (head != null) {
                priorityQueue.add(head);
            }
        }
        while (!priorityQueue.isEmpty()) {
            ListNode node = priorityQueue.poll();
            p.next = node;
            if (node.next != null) {
                priorityQueue.add(node.next);
            }
            p = p.next;
        }
        return dummy.next;
    }

    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        if (head == null) {
            return false;
        }

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
     * 不允许修改 链表。
     * 链接：https://leetcode-cn.com/problems/linked-list-cycle-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        if (head == null) {
            return null;
        }
        int distance = 0;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            distance ++;
            if (fast == slow) {
                break;
            }
        }
        if (fast != slow) {
            return null;
        }

        ListNode fastFlag = head;
        ListNode slowFlag = head;

        while (distance > 0) {
            fastFlag = fastFlag.next;
            distance --;
        }

        while (fastFlag != null && fast.next != null) {
            if (fastFlag == slowFlag) {
                return fastFlag;
            }
            fastFlag = fastFlag.next;
            slowFlag = slowFlag.next;
        }
        return null;
    }

    /**
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     * 如果有两个中间结点，则返回第二个中间结点。
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while ( fast != null && fast.next != null ) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    /**
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = slow;

        while (fast != null) {
            fast = fast.next;
            n --;
            if (n == 0) {
                break;
            }
        }
        if ( n > 0) {
            return null;
        }

        while (fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        if (fast == null && slow != null) {
            pre = slow.next;
        }
        if (slow != null && slow.next != null) {
            slow.next = slow.next.next;
        } else {
            pre = null;
        }
        return pre;
    }

    /**
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     * https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        ListNode node2 = headB;
        Boolean flag1 = false;
        Boolean flag2 = false;
        if (node1 == null || node2 == null) {
            return null;
        }

        if (node1 == node2) {
            return node1;
        }

        while (node1 != null && node2 != null) {
            node1 = node1.next;
            node2 = node2.next;

            if (node1 == null && !flag1) {
                node1 = headB;
                flag1 = true;
            }
            if (node2 == null && !flag2) {
                node2 = headA;
                flag2 = true;
            }

            if (node1 == node2) {
                return node1;
            }
        }
        return null;
    }

    /**
     * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(0, head);

        // 初始化指针
        ListNode slow = dummyHead;
        ListNode fast = dummyHead.next;

        // 将指针移到相应的位置
        for(int step = 0; step < left - 1; step++) {
            slow = slow.next; fast = fast.next;
        }

        // 头插法插入节点
        for (int i = 0; i < right - left; i++) {
            ListNode removed = fast.next;
            fast.next = fast.next.next;

            removed.next = slow.next;
            slow.next = removed;
        }

        return dummyHead.next;

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
        ListNode node3 = new ListNode(3, null);
        ListNode node4 = new ListNode(4, null);
        ListNode node5 = new ListNode(5, null);
        ListNode node6 = new ListNode(6, null);
        ListNode node7 = new ListNode(7, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        ListNode listNode = reverseBetween(node1, 2, 3);

        //ListNode listNode = reverseListNode(node1, node3);

        System.out.println(getNodeList(listNode));
    }



}
