package sword_offer;

/**
 * 合并两个排序的链表
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 */
public class Problem_17 {

    public static class ListNode {
        public int val;
        public ListNode next = null;

        public ListNode (int val) {
            this.val = val;
        }
    }

    //递归版本
    public static ListNode mergeList(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode mergeHead = null;

        if (list1.val <= list2.val) {
            mergeHead = list1;
            mergeHead.next = mergeList(list1.next, list2);
        } else {
            mergeHead = list2;
            mergeHead.next = mergeList(list1, list2.next);
        }
        return mergeHead;
    }

    //非递归版本
    public static ListNode mergeList2(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode mergeHead = null;
        ListNode cur = null;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                if (mergeHead == null) {
                    mergeHead = list1;
                    cur = mergeHead;
                } else {
                    cur.next = list1;
                    cur = cur.next;
                }
                list1 = list1.next;
            } else {
                if (mergeHead == null) {
                    mergeHead = list2;
                    cur = mergeHead;
                } else {
                    cur.next = list2;
                    cur = cur.next;
                }
                list2 = list2.next;
            }
        }

        if (list1 != null) {
            cur.next = list1;
        } else {
            cur.next = list2;
        }
        return mergeHead;
    }
}
