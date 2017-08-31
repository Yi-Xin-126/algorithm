package sword_offer;

/**
 * 链表中倒数第k个结点
 * 输入一个链表，输出该链表中倒数第k个结点。
 * 举一反三：
 * 当我们用一个指针遍历链表不能解决问题时，可以尝试用两个指针来遍历链表。可以让其中一个指针遍历速度快一些，
 * 比如一次在链表上走两步，或者让它现在链表上走若干步
 */
public class Problem_15 {

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode findKthToTail(ListNode head, int k) {
        if (head == null || k < 1) {
            return null;
        }
        ListNode ahead = head;
        ListNode behind = head;

        for (int i = 1; i < k; i++) {
            if (ahead.next == null) {
                return null;
            }
            ahead = ahead.next;
        }

        while (ahead.next != null) {
            behind = behind.next;
            ahead = ahead.next;
        }
        return behind;
    }
}
