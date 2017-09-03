package d17_08_09;

/**
 * 一个数组分成两个不相交的子数组，求出不相交子数组的最大和
 * 预处理数组的应用：先设计大流程，然后找优化的点，可以用预处理数组记下来
 */
public class Problem_03_TwoSubArrayMaxSum {

    /**
     * 思路：依次去找以每个位置为分界线时，左右两边的子数组最大累加和
     * 技巧：利用先遍历一遍的方式来用预处理数组记录右边的子数组最大累加和
     */
    public static int twoSubArrayMaxSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        //important
        int[] rArray = new int[arr.length];//记录以每个位置为分界线（包含当前位置）的右边的子数组最大累加和
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = arr.length - 1; i > 0; i--) {
            cur += arr[i];
            max = Math.max(max, cur);
            rArray[i] = max;
            cur = cur < 0 ? 0 : cur;
        }
        int res = Integer.MIN_VALUE;
        max = Integer.MIN_VALUE;
        cur = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            res = Math.max(rArray[i+1] + max, res);
            cur = cur < 0 ? 0 : cur;
        }
        return res;
    }

    // for test
    public static int rightAnswer(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int res = Integer.MIN_VALUE;
        for (int p = 0; p < arr.length - 1; p++) {
            res = Math.max(res, maxSum(arr, 0, p) + maxSum(arr, p + 1, arr.length - 1));
        }
        return res;
    }

    // for test
    public static int maxSum(int[] arr, int l, int r) {
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = l; i <= r; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;
        }
        return max;
    }

    // for test
    public static int[] generateRandomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 20) - 10;
        }
        return res;
    }

    // for test
    public static void main(String[] args) {
        int testTime = 5000000;
        boolean hasErr = false;
        for (int i = 0; i < testTime; i++) {
            int[] test = generateRandomArray();
            if (twoSubArrayMaxSum(test) != rightAnswer(test)) {
                hasErr = true;
            }
        }
        if (hasErr) {
            System.out.println("23333333");
        } else {
            System.out.println("66666666");
        }

    }
}
