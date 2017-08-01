package d17_07_26;

/**
 * 给定一个数组arr，返回子数组的最大累加和。
 * 例如，arr=[1,-2,3,5,-2,6,-1]，所有的子数组中，[3,5,-2,6]可以累加出最大的和12，所以返回12。
 */
public class Problem_03_SubArrayMaxSum {

    /*
        遍历一遍即可完成，维护两个变量cur（以当前位置结尾的子数组的和）和max（记录子数组最大和）
        cur更新逻辑，加出负数就归零（这么做的原因：要想子数组的和最大，那么它必然不能有负数的前缀和后缀）
    */
    public static int maxSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int cur = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            cur += arr[i];
            max = Math.max(max, cur);
            cur = cur < 0 ? 0 : cur;         //important
        }
        return max;
    }


    public static void main(String[] args) {
        int[] arr1 = {-2, -3, -5, 40, -10, -10, 100, 1};
        System.out.println(maxSum(arr1));

        int[] arr2 = {-2, -3, -5, 0, 1, 2, -1};
        System.out.println(maxSum(arr2));

        int[] arr3 = {-2, -3, -5, -1};
        System.out.println(maxSum(arr3));

    }
}
