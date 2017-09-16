package sword_offer;

/**
 * 左旋转字符串
 * 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
 * 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。
 * 例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
 */
public class Problem_42_2 {

    public static String leftRotateString(String str, int n) {
        if (str == null) {
            return null;
        }
        if (str.trim().equals("")) {
            return str;
        }
        if (n > str.length()) {
            return str;
        }
        char[] cs = str.toCharArray();
        reverse(cs, 0, n-1);
        reverse(cs, n, cs.length-1);
        reverse(cs, 0, cs.length-1);
        return String.valueOf(cs);

    }

    public static void reverse(char[] cs, int left, int right) {
        if (left == right) {
            return;
        }
        while (left < right) {
            swap(cs, left++, right--);
        }
    }

    public static void swap(char[] cs, int index1, int index2) {
        char temp = cs[index1];
        cs[index1] = cs[index2];
        cs[index2] = temp;
    }
}
