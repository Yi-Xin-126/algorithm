package sword_offer;

import java.util.Stack;

/**
 * 反转链表
 * 输入一个链表，反转链表后，输出链表的所有元素。
 */
public class Problem_16 {

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode res = head;
        ListNode pre = null;              //保存当前节点的前一个节点
        ListNode last = null;             //保存当前节点的后一个节点

        while (res != null) {

            last = res.next;

            if (last == null) {           //遍历到最后一个节点结束，返回最后一个节点当头部
                res.next = pre;
                break;
            }

            res.next = pre;
            pre = res;
            res = last;
        }
        return res;

    }
}
