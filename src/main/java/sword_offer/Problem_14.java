package sword_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
 */
public class Problem_14 {

    public static void reOrderArray(int[] array) {
        List<Integer> odd = new ArrayList<Integer>();
        List<Integer> even = new ArrayList<Integer>();

        for (int i = 0; i < array.length; i++) {
            if (isEven(array[i])) {
                even.add(array[i]);
            } else {
                odd.add(array[i]);
            }
        }

        int k = 0;
        for (int i = 0; i < odd.size(); i++) {
            array[k++] = odd.get(i);
        }
        for (int j = 0; j < even.size(); j++) {
            array[k++] = even.get(j);
        }
    }

    public static boolean isEven(int n) {
        return (n & 1) == 0 ? true : false;
    }
}
