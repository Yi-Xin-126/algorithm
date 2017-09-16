package sword_offer;

import java.util.ArrayList;

/**
 * 最小的K个数 (BFPRT)
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
 */
public class Problem_30 {

    public static ArrayList<Integer> getLeastNumbers(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if (input == null || k > input.length || input.length < 1) {
            return res;
        }
        int minKth = getMinKthByBFPRT(input, k);
        int[] resArr = new int[k];
        int index = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] < minKth) {
                resArr[index++] = input[i];
            }

        }
        for (; index < k; index++) {
            resArr[index] = minKth;
        }
        for (int i = 0; i < resArr.length; i++) {
            res.add(resArr[i]);
        }
        return res;

    }


    public static int getMinKthByBFPRT(int[] arr, int k) {
        int[] nArr = copyArr(arr);
        return select(arr, 0, arr.length-1, k-1);
    }

    public static int[] copyArr(int[] arr) {
        int[] newArr = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        return newArr;
    }

    public static int select(int[] arr, int begin, int end, int k) {
        if (begin == end) {
            return arr[begin];
        }
        int pivot = getMedianOfMedians(arr, begin, end);
        int[] pivotRange = patition(arr, begin, end, pivot);
        if (k >= pivotRange[0] && k <= pivotRange[1]) {
            return arr[k];
        } else if (k < pivotRange[0]) {
            return select(arr, begin, pivotRange[0]-1, k);
        } else {
            return select(arr, pivotRange[1]+1, end, k);
        }

    }

    public static int getMedianOfMedians(int[] arr, int begin, int end) {
        int length = end - begin + 1;
        int offset = length % 5 == 0 ? 0 : 1;
        int[] mArr = new int[length/5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            int beginI = begin + i*5;
            int endI = beginI + 4;
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        return select(mArr, 0, mArr.length-1, mArr.length/2);
    }

    public static int[] patition(int[] arr, int begin, int end, int pivot) {
        int small = begin - 1;
        int cur = begin;
        int big = end + 1;
        while (cur != big) {
            if (arr[cur] < pivot) {
                swap(arr, ++small, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, --end, cur);
            } else {
                cur++;
            }
        }
        int[] range = new int[2];
        range[0] = small + 1;
        range[1] = end - 1;
        return range;
    }

    public static int getMedian(int[] arr, int begin, int end) {
        int sum = begin + end;
        int mid = (sum / 2) + (sum % 2);
        insertSort(arr, begin, end);
        return arr[mid];
    }

    public static void insertSort(int[] arr, int begin, int end) {
        for (int i = begin + 1; i <= end; i++) {
            for (int j = i; j > begin; j--) {
                if (arr[j] < arr[j-1]) {
                    swap(arr, j, j-1);
                } else {
                    break;
                }
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2) {
        if (index1 != index2) {
            int temp = arr[index1];
            arr[index1] = arr[index2];
            arr[index2] = temp;
        }
    }
}
