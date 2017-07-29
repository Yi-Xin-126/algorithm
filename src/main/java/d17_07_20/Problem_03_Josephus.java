package d17_07_20;

/**
     环形单链表的约瑟夫问题
     据说著名犹太历史学家Josephus有过以下故事：在罗马人占领乔塔帕特后，
     39个犹太人与Josephus及他的朋友躲到一个洞中，39个犹太人决定宁愿死也不要被敌人抓到，
     于是决定了一个自杀方式，41个人排成一个圆圈，由第1个人开始报数，报数到3的人就自杀，
     然后再由下一个人重新报1，报数到3的人再自杀，这样依次下去，直到剩下最后一个人时，
     那个人可以自由选择自己的命运。这就是著名的约瑟夫问题。
     现在请用单向环形链表描述该结构并呈现整个自杀过程。
     输入：一个环形单向链表的头节点head和报数的值m。
     返回：最后生存下来的节点，且这个节点自己组成环形单向链表，其他节点都删掉。
     进阶：
     如果链表节点数为N，想在时间复杂度为O(N)时完成原问题的要求，该怎么实现？
     n个节点的环，数到m就杀人
 */
public class Problem_03_Josephus {

    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    //普通解法 O（m*n）
    /*
        环形链表中不停的转圈，转m个就删除节点，知道剩下最后一个节点
    */
    public static Node JosephusKill1(Node head, int m) {
        if (head == null || head.next == head || m < 0) {
            return head;
        }
        Node last = head;
        while (last.next != head) {
            last = last.next;
        }
        int count = 0;
        while (head != last) {
            if (++count == m) {
                last.next = head.next;
                count = 0;
            } else {
                last = last.next;
            }
            head = last.next;
        }
        return head;
    }

    //进阶解法 O(n)
    public static Node JosephusKill2(Node head, int m) {
        if (head == null || head.next == head || m < 0) {
            return head;
        }
        Node cur = head.next;
        int count = 1;
        while (cur != head) {
            count++;
            cur = cur.next;
        }
        int res = getLive(count, m);
        while (--res != 0) {
            head = head.next;
        }
        return head;
    }

    /*
        递归
        从最底下往上推，推出最原始的一个人都没杀的情况，找个活下来的那个人位置即可
    例子   每个人报的数： 1 2 3 4 5 6 7 .....
                  编号： 1 2 3 1 2 3 1 .....
        首先描述一个函数（剃刀函数）：Y=X%i
                                编号=（报数-1）%i+1
                                编号老=（编号新+s-1）%i+1
                                s即每次被杀掉的节点
                                                         即报数到m的节点
                                合并下来为s=（m-1）%i+1
                                最终的公式：老=（新+（m-1）%i+1-1）%i+1 = （新+m-1）%i+1
        老 1 2 3 4 5 6 7   老链表中结点数为i  假设被干掉的是s， s=3
        新 5 6 x 1 2 3 4   当新编号为1-4（1<=新<=i-s）时，老=新+s
                           当新编号为5-6（i-s+1<=新<=i-1）时，老=新-s-1
    */
    public static int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i-1, m) + m - 1) % i + 1;
    }
}
