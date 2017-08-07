package sword_offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 剑指offer中问题5：从尾到头打印链表
 * 输入一个链表，从尾到头打印链表每个节点的值。
 */
public class Problem_05 {

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (listNode == null) {
            return res;
        }
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(listNode.val);
        while (listNode.next != null) {
            listNode = listNode.next;
            stack.push(listNode.val);
        }
        while (!stack.isEmpty()) {
            res.add(stack.pop());
        }

        return res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(67);
        head.next = new ListNode(0);
        head.next.next = new ListNode(24);
        head.next.next.next = new ListNode(58);
        System.out.println(printListFromTailToHead(head).toString());
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }
}
