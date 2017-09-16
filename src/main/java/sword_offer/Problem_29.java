package sword_offer;

/**
 * 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，
 * 因此输出2。如果不存在则输出0。
 */
public class Problem_29 {

    public static int moreThanHalfNum(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int[] help = new int[256];
        int half = array.length/2 + 1;
        int res = 0;
        for (int i = 0; i < array.length; i++) {
            if (++help[array[i]] >= half) {
                res = array[i];
                break;
            }
        }
        return res;
    }

    public static int moreThanHalfNum2(int[] array) {
        if (array == null || array.length < 1) {
            return 0;
        }
        int res = array[0];
        int count = 1;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == res) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                res = array[i];
                count = 1;
            }
        }
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (res == array[i]) {
                count++;
            }
        }
        if (count * 2 > array.length) {
            return res;
        } else {
            return 0;
        }
    }
}
