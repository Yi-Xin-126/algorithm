package sword_offer;

import java.util.ArrayList;

/**
 * 和为S的两个数字
 * 输入一个递增排序的数组和一个数字S，在数组中查找两个数，是的他们的和正好是S，
 * 如果有多对数字的和等于S，输出两个数的乘积最小的。
 */
public class Problem_41_2 {

    public static ArrayList<Integer> findNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (array == null || array.length < 2) {
            return res;
        }
        int i = 0;
        int j = array.length-1;
        while (i < j) {
            if (array[i] + array[j] == sum) {
                res.add(array[i]);
                res.add(array[j]);
                return res;
            } else if (array[i] + array[j] < sum){
                i++;
            } else {
                j--;
            }
        }
        return res;
    }
}
