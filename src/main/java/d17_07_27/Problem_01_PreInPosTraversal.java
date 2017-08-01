package d17_07_27;

import java.util.Stack;

/**
 * 分别用递归和非递归方式实现二叉树先序、中序和后序遍历
 */
public class Problem_01_PreInPosTraversal {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    //递归实现先序遍历
    public static void preOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    //递归实现中序遍历
    public static void inOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    //递归实现后序遍历
    public static void postOrderRecur(Node head) {
        if (head == null) {
            return;
        }
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    /*
        非递归实现先序遍历
        用一个栈stack，首先把头节点压入，然后开始一次循环，弹出栈顶元素并打印，先判断栈顶元素
        是否有右孩子，如果有右孩子，先压入右孩子；再判断栈顶元素是否有左孩子，如果有左孩子，
        再压入左孩子，这样一次循环结束。整个循环直到栈为开结束。
    */
    public static void preOrderUnRecur(Node head) {
        System.out.print("pre-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.add(head);
            while (!stack.isEmpty()) {
                head = stack.pop();
                System.out.print(head.value + " ");
                if (head.right != null) {
                    stack.push(head.right);
                }
                if (head.left != null) {
                    stack.push(head.left);
                }
            }
        }
    }

    /*
        非递归实现中序遍历
        用一个栈stack，首先把头节点压入，然后压入左孩子，再压左孩子的左孩子，.....，把左边界先全部
        压入，直到左孩子为空；这是从栈中弹出栈顶元素并打印，然后令当前头节点等于栈顶元素的右孩子，
        在次进行压左边界的操作，直到为空，.......
        依次进行下去，直到栈空
    */
    public static void inOrderUnRecur(Node head) {
        System.out.print("in-order: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            while (!stack.isEmpty() || head != null) {
                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.print(head.value + " ");
                    head = head.right;
                }
            }
        }

    }

    /*
        非递归实现后序遍历左右根，逆过来是根右左，先序遍历是根左右，那么只需要调整一下先序遍历即可
        用两个栈s1，s2，首先把头节点压入s1，然后开始一次循环，弹出栈顶元素并压入s2，先判断栈顶元素
        是否有左孩子，如果有左孩子，先往s1压入左孩子；再判断栈顶元素是否有右孩子，如果有右孩子，
        再往s1压入右孩子，这样一次循环结束。整个循环直到栈为开结束。
    */
    public static void postOrderUnRecur1(Node head) {
        System.out.print("post-order1: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<Node>();
            Stack<Node> s2 = new Stack<Node>();
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }

    }

    public static void postOrderUnRecur2(Node head) {
        System.out.print("post-order2: ");
        if (head != null) {
            Stack<Node> stack = new Stack<Node>();
            stack.push(head);
            Node c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && head != c.left && head != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && head != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().value + " ");
                    head = c;
                }
            }
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        // recursive
        System.out.println("==============recursive==============");
        System.out.print("pre-order: ");
        preOrderRecur(head);
        System.out.println();
        System.out.print("in-order: ");
        inOrderRecur(head);
        System.out.println();
        System.out.print("pos-order: ");
        postOrderRecur(head);
        System.out.println();

        // unrecursive
        System.out.println("============unrecursive=============");
        preOrderUnRecur(head);
        System.out.println();
        inOrderUnRecur(head);
        System.out.println();
        postOrderUnRecur1(head);
        System.out.println();
        postOrderUnRecur2(head);

    }
}
