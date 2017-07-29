package d17_07_19;

import java.util.HashMap;
import java.util.Map;

/**
    给定一个数组，值可以为正、负和0，请返回累加和为给定值k的最长子数组长度。
 */
public class Problem_02_LongestSumSubArrayLength {

    /*
        申请一个map k-v  sum-index(出现的最早位置)
     例子: 4 1 -1 0 3 6    sum   index
           0 1 2 3 4 5     4       0
                           5       1
                        又出现sum为4，因为记录的是最早位置，所以map不更新
                        又出现sum为4，因为记录的是最早位置，所以map不更新
                           7       4
                           13      5
         注意：map初始化时要put(0,-1),在记录长度时sum-k为0，index-(-1)得到的即为长度
         维护好这样一个map，在遍历过程中，当前位置和为sum，想要求得最长子数组长度，就要找到sum-k最早出现的位置
         当前位置下标与最早出现位置的下标的差值即为当前位置下的最长子数组长度
         遍历结束时len即为所有情况下最长的子数组长度
    */
    public static int maxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = 0;
        int len = 0;
        map.put(0, -1);   //important
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            if (map.containsKey(sum-k)) {
                len = Math.max(len, i - map.get(sum-k));    //important
            }
            if (!map.containsKey(sum)) {
                map.put(sum, i);
            }
        }
        return len;
    }

    public static int[] generateArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = (int) (Math.random() * 11) - 5;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = generateArray(20);
        printArray(arr);
        System.out.println(maxLength(arr, 10));

    }
}
