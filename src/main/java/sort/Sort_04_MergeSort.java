package sort;

/**
 * 归并排序  f(N)=O(N*logN) s(N)=O(N)  稳定   n大时好
 * 如果需要稳定，空间不是很重要，请选择归并
 */
public class Sort_04_MergeSort {

    public static int[] mergeSort(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return arr;
        }
        sort(arr, 0, arr.length - 1);
        return arr;
    }

    public static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);     //递归去排左边的部分
            sort(arr, mid + 1, right);  //递归去排右边的部分
            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] h = new int[right - left + 1];   //辅助数组
        int hi = 0;                        //辅助数组下标
        int i = left;  //左指针
        int j = mid + 1; //右指针
        while (i <= mid && j <= right) {   //左右两边先比较，将某一部分排序整合完成
            if (arr[i] <= arr[j]) {
                h[hi++] = arr[i++];
            } else {
                h[hi++] = arr[j++];
            }
        }
        for (; (i < mid + 1 || j < right + 1); i++, j++) {   //将剩余的部分一次放到辅助数组中
            h[hi++] = i > mid ? arr[j] : arr[i];
        }                                               //完成后此时的h数组已经是排好序的了
        for (int k = 0; k < h.length; k++) {
            arr[left++] = h[k];               //将原数组arr的left~right部分用排好序的h数组替换掉
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 8, 2, 3, 0, 9, 7, 4, 5};
        printArray(mergeSort(arr));
    }
}
