package sword_offer;

/**
 * 剑指offer中问题9_4：矩阵覆盖
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 */
public class Problem_09_4 {

    public static int RectCover(int target) {
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int one = 1;
        int two = 2;
        int res = 0;
        for (int i = 3; i <= target; i++) {
            res = one + two;
            one = two;
            two = res;
        }
        return res;
    }

}
