package sword_offer;

import java.util.Stack;

/**
 * 剑指offer中问题7：用两个栈实现队列
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 */
public class Problem_07 {

    public static Stack<Integer> stack1 = new Stack<Integer>();
    public static Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        if (stack2.isEmpty()) {
            stack2.push(node);
        } else {
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            stack1.push(node);
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

    }

    public int pop() {
        return stack2.pop();
    }
}
