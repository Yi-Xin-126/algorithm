package d17_07_19;

/**
 * 给定一个数组，值全是正数，请返回累加和为给定值k的最长子数组长度。
 */
public class Problem_02_LongestSumSubArrayLengthInPositiveArray {

    /*
        滑动窗口 左l右r俩个指针  变量len记录最大长度
        sum<k时，r右移，sum加上刚移动过去的；sum>k，l右移，sum就减去刚才l所指的值；
        sum==k，比较之前的len和当前情况下的len，记录下较大值，l右移，sum就减去刚才l所指的值
        遍历结束时，返回len
    */
    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum < k) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }


    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
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
        int len = 20;
        int k = 15;
        int[] arr = generatePositiveArray(len);
        printArray(arr);
        System.out.println(getMaxLength(arr, k));

    }
}
