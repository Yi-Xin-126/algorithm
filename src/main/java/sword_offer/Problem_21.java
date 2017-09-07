package sword_offer;

import java.util.Stack;

/**
 * 包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
 */
public class Problem_21 {

    public static Stack<Integer> dataStack = new Stack<Integer>();  //存储数据
    public static Stack<Integer> minStack = new Stack<Integer>();   //存储当前的最小值

    public static void push(int node) {
        if (minStack.isEmpty()) {
            minStack.push(node);
        } else {
            if (node < minStack.peek()) {
                minStack.push(node);
            } else {
                minStack.push(minStack.peek());
            }
        }
        dataStack.push(node);
    }

    public static void pop() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("err");
        }
        dataStack.pop();
        minStack.pop();
    }

    public static int top() {
        if (dataStack.isEmpty()) {
            throw new RuntimeException("err");
        }
        return dataStack.peek();
    }

    public static int min() {
        if (minStack.isEmpty()) {
            throw new RuntimeException("err");
        }
        return minStack.peek();
    }
}
