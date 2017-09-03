package d17_08_09;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个数组，每个位置的值代表一个高度，那么整个数组可以看做是一个直方图，如果把这个直方图看做是
 * 一个容器的话，可以用它来装多少格水。
 * 例如3,1,2,4，返回3.
 */
public class Problem_02_WaterProblem {

    /**
     * 暴力解法O(N*N)
     * 依次得到每个位置上可以存住多少水，把每个位置上的值相加即可
     * 方法：从当前位置出发，分别向左向右寻找左边最大的数（max左）和右边最大的数（max右）
     * max左 max右 中较小的数减去当前位置在数组中的值，即为当前位置上可以存住的水量
     */
    public static int getWater1(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int value = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            int leftMax = 0;
            int rightMax = 0;
            for (int l = 0; l < i; l++) {
                leftMax = Math.max(arr[l], leftMax);
            }
            for (int r = i + 1; r < arr.length; r++) {
                rightMax = Math.max(arr[r], rightMax);
            }
            value += Math.max(0, Math.min(leftMax, rightMax) - arr[i]);
        }
        return value;
    }

    /**
     * 优化 ： 提前将每个位置的max左 max右 求出来   优化为时间O(N) 空间O(N)
     * 遍历两边   1 左->右  生成L[i] 即max左
     *           2 右->左  生成 R[i] 即max右
     */
    public static int getWater2(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int n = arr.length - 2;
        int[] leftMaxs = new int[n];
        leftMaxs[0] = arr[0];
        for (int i = 1; i < n; i++) {
            leftMaxs[i] = Math.max(leftMaxs[i - 1], arr[i]);
        }
        int[] rightMaxs = new int[n];
        rightMaxs[n - 1] = arr[n + 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i + 2]);
        }
        int value = 0;
        for (int i = 1; i <= n; i++) {
            value += Math.max(0, Math.min(leftMaxs[i - 1], rightMaxs[i - 1]) - arr[i]);
        }
        return value;
    }

    /**
     * 与方法二类似
     */
    public static int getWater3(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int n = arr.length - 2;
        int[] rightMaxs = new int[n];
        rightMaxs[n - 1] = arr[n + 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMaxs[i] = Math.max(rightMaxs[i + 1], arr[i + 2]);
        }
        int leftMax = arr[0];
        int value = 0;
        for (int i = 1; i <= n; i++) {
            value += Math.max(0, Math.min(leftMax, rightMaxs[i - 1]) - arr[i]);
            leftMax = Math.max(leftMax, arr[i]);
        }
        return value;
    }

    /**
     *  时间O(N)  空间O(1)
     *  用两个指针 max左  max右  代表滑过去的最大值
     *  max左 max右  哪个小就滑哪个  滑过去的就结算
     */
    public static int getWater4(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int value = 0;
        int leftMax = arr[0];
        int rightMax = arr[arr.length - 1];
        int l = 1;
        int r = arr.length - 2;
        while (l <= r) {
            if (leftMax <= rightMax) {
                value += Math.max(0, leftMax - arr[l]);
                leftMax = Math.max(leftMax, arr[l++]);
            } else {
                value += Math.max(0, rightMax - arr[r]);
                rightMax = Math.max(rightMax, arr[r--]);
            }
        }
        return value;
    }

    public static int[] generateRandomArray() {
        int[] arr = new int[(int) (Math.random() * 98) + 2];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 200) + 2;
        }
        return arr;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int[] arr = generateRandomArray();
            int r1 = getWater1(arr);
            int r2 = getWater2(arr);
            int r3 = getWater3(arr);
            int r4 = getWater4(arr);
            if (r1 != r2 || r3 != r4 || r1 != r3) {
                System.out.println("What a fucking day! fuck that! man!");
            }
        }

        HashMap<String,String> map = new HashMap<String,String>();

        for(Map.Entry<String,String> entry : map.entrySet()){
            System.out.println(entry.getKey()+" , "+ entry.getValue());
        }

    }

}
