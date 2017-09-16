package sword_offer;

/**
 * 两个链表的第一个公共结点
 * 输入两个链表，找出它们的第一个公共结点。
 */
public class Problem_37 {

    public static class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode findFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        int i = 1;
        int j = 1;
        ListNode curi = pHead1;
        ListNode curj = pHead2;
        while (curi.next != null) {
            curi = curi.next;
            i++;
        }
        while (curj.next != null) {
            curj = curj.next;
            j++;
        }
        if (curi != curj) {
            return null;
        }
        curi = pHead1;
        curj = pHead2;
        if (i > j) {
            for (int k = 1; k <= i-j; k++) {
                curi = curi.next;
            }
            while (curi != null && curj != null) {
                if (curi == curj) {
                    return curi;
                } else {
                    curi = curi.next;
                    curj = curj.next;
                }
            }
        } else {
            for (int k = 1; k <= j-i; k++) {
                curi = curj.next;
            }
            while (curi != null && curj != null) {
                if (curi == curj) {
                    return curi;
                } else {
                    curi = curi.next;
                    curj = curj.next;
                }
            }
        }
        return null;
    }
}
