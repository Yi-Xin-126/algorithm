package sort;

/**
 * 桶排序的思想,不基于比较
 * 计数排序 f(N)=O(N)  s(N)=O(M) (M为桶的数量)  稳定
 */
public class Sort_08_CountingSort {

    public static int[] countingSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        //获得数组中的最大和最小值
        for (int i = 0; i < arr.length; i++) {
            min = Math.min(min, arr[i]);
            max = Math.max(max, arr[i]);
        }
        //建立桶，并把数据倒入桶中
        int[] bucket = new int[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i] - min]++;
        }
        //从桶中倒出
        int index = 0;
        for (int i = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i]; j++) {
                arr[index++] = i + min;
            }
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {21, 5, 4, 8, 2, 3, 0, 9, 7, 4, 5, 10};
        printArray(countingSort(arr));
    }
}
