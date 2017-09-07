package sword_offer;

import java.util.Stack;

/**
 *栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
 * 序列4，5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
 * （注意：这两个序列的长度是相等的）
 */
public class Problem_22 {

    public static boolean isPopOrder(int[] pushA, int[] popA) {
        if (pushA == null || popA == null || pushA.length == 0 || popA.length == 0) {
            return false;
        }
        boolean res = false;
        Stack<Integer> help = new Stack<Integer>();
        int i = 0, j = 0;
        while (j < popA.length) {
            int temp = popA[j];
            if (!help.isEmpty() && help.peek() == temp) {
                j++;
                help.pop();
            } else {
                if (i == pushA.length) {
                    break;
                }
                help.push(pushA[i]);
                i++;
            }
        }
        res = help.isEmpty() ? true : false;
        return res;
    }
}
