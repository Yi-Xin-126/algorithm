package d17_08_07;

/**
 * 给定两个有序的长度相等的数组arr1和arr2，返回上中位数
 */
public class Problem_01_FindUpMedian {

    public static int getUpMedian(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            throw new RuntimeException("Your arr is invalid!");
        }
        int start1 = 0;
        int end1 = arr1.length - 1;
        int start2 = 0;
        int end2 = arr2.length - 1;
        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;

        while (start1 < end1) {
            mid1 = start1 + (end1 - start1) / 2;
            mid2 = start2 + (end2 - start2) / 2;
            // 元素个数为奇数，offset为0，元素个数为偶数，offset为1。
            offset = (arr1.length & 1) ^ 1;

            //以中间值为划分，中间值小的那个数组就用后半部分，中间值大的那个数组就用前半部分，继续找
            if (arr1[mid1] > arr2[mid2]) {
                end1 = mid1;
                start2 = mid2 + offset;
            } else if (arr1[mid1] < arr2[mid2]) {
                start1 = mid1 + offset;
                end2 = mid2;
            } else {
                return arr1[mid1];
            }
        }
        return Math.min(arr1[start1], arr2[start2]);
    }
}
