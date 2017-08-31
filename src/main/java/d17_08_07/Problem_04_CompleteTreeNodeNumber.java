package d17_08_07;

/**
 * 统计完全二叉树的节点数
 * 【题目】 给定一棵完全二叉树的头节点head，返回这棵树的节点个数。
 * 【要求】 如果完全二叉树的节点数为N，请实现时间复杂度低于O(N)的解法。
 */
public class Problem_04_CompleteTreeNodeNumber {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int val) {
            this.value = val;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, maxLeftLevel(head, 1));
    }

    public static int bs(Node node, int l, int h) {
        if (l == h) {
            return 1;
        }
        if (maxLeftLevel(node.right, l + 1) == h) {  //满二叉树的高度为h，则结点个数为(2^h)-1。、
            //左子树为满二叉树的节点数加上头结点加bs（右子树）
            return (1 << h - l) + bs(node.right, l + 1, h);
        } else {
            //右子树为高度小1的满二叉树的节点数加上头结点加上bs（左子树）
            return (1 << h - l - 1) + bs(node.left, l + 1, h);
        }
    }

    public static int maxLeftLevel(Node node, int level) {
        while (node != null) {
            level++;
            node = node.left;
        }
        return level - 1;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }
}
