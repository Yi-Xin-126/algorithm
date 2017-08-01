package d17_07_19;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组，值可以为正、负和0，请返回累加和小于等于k的最长子数组长度。
 */
public class Problem_02_LongestSubarrayLessSumAwesomeSolution {

    //important method1
    /*
        维护min_sum(以某个位置开头加得的最小和) 和 end_index(以某个位置开头能加得最小和的结尾的下标)
    例子：  4 3 -2 6 7 -3 -1
    index   0 1 2 3  4  5 6
   min_sum        6  2 -4 -1
   end_index      3  6  6  6
        更新策略：从结尾开始（-1）倒着走，每个位置到往后加，求最小和
        在求min_sum[i]时，如果min_sum[i+1]<=0，则min_sum[i]=arr[i] + min_sum[i+1],end_index[i]=end_index[i+1]
                         否则min_sum[i]=arr[i],end_index[i]=i。
        遍历过程，永远不后退，一次往后加
    */
    public static int maxLengthAwesome(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Map<Integer, Integer> ends = new HashMap<Integer, Integer>();   //用map维护end_index,查询为O(1)
        int[] sums = new int[arr.length];                               //用数组维护min_index
        sums[arr.length - 1] = arr[arr.length - 1];
        ends.put(arr.length - 1, arr.length - 1);
        for (int i = arr.length - 2; i >= 0; i--) {
            if (sums[i + 1] < 0) {
                sums[i] = arr[i] + sums[i + 1];
                ends.put(i, ends.get(i + 1));
            } else {
                sums[i] = arr[i];
                ends.put(i, i);
            }
        }
        int end = 0;
        int sum = 0;
        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            while (end < arr.length && sum + sums[end] <= k) {
                sum += sums[end];              //sum加上以end开头得到的最小和的那一坨
                end = ends.get(end) + 1;       //把end更新为上面加上的那一坨后面的下一个位置
            }
            sum -= end > i ? arr[i] : 0;       //如果end在i后面，sum则减去此时数组中i位置的值，为遍历下一次准备
            res = Math.max(res, end - i);      //更新res
            end = Math.max(i + 1, end);          //更新end
        }
        return res;
    }

    //method2
    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("oops!");
            }
        }

    }

}
