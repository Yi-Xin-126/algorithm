package sword_offer;

/**
 * 数字在排序数组中出现的次数
 * 统计一个数字在排序数组中出现的次数。
 */
public class Problem_38 {

    public static int getNumberOfK(int[] array, int k) {
        if (array == null || array.length < 1 || k < array[0] || k > array[array.length-1]) {
            return 0;
        }
        int count = 1;
        int index = getIndex(array, 0, array.length-1, k);
        if (index == -1) {
            return 0;
        }
        int i = index-1;
        int j = index+1;
        while (i >= 0 || j <= array.length-1) {
            if (i >= 0 && array[i--] == k) {
                count++;
            } else {
                i = -1;
            }
            if (j <= array.length-1 && array[j++] == k) {
                count++;
            } else {
                j = array.length;
            }
        }
        return count;
    }

    public static int getIndex(int[] arr, int left, int right, int k) {
        if (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == k) {
                return mid;
            } else if (arr[mid] < k) {
                return getIndex(arr, left, mid-1, k);
            } else {
                return getIndex(arr, mid+1, right, k);
            }
        }
        return -1;
    }
}
