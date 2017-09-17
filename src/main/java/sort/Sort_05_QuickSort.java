package sort;

/**
 * 快速排序  f(N)=O(N*logN) s(N)=O(logN)~O(N)  不稳定
 * 快速排序是最快的通用排序算法，在大多数实际情况中，快速排序是最佳选择。
 * 在java的默认排序中是使用的是三向切分排序。
 */
public class Sort_05_QuickSort {


    public static int[] quickSort(int[] arr) {
        quick(arr, 0, arr.length - 1);
        return arr;
    }

    public static void quick(int[] arr, int left, int right) {
        if (left < right) {
            int mid = partition(arr, left, right);
            quick(arr, left, mid);
            quick(arr, mid + 1, right);
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int mid = left + (right - left) / 2;   //选中元素的下标
        swap(arr, mid, right);           //把选中元素放到数组末尾
        int less = left;          //比选中元素小的区域的指针
        for (int i = left; i <= right; i++) {
            if (arr[i] < arr[right]) {
                swap(arr, i, less++);   //比选中元素小的数中位置0开始依次往后放 0 1 2 ....
            }
        }
        swap(arr, less, right);     //把选中元素放到小区域的后面。  这样就完成了小于选中元素的数放到它的左边，大于它的数放到它的右边
        return less;              //返回选中元素的下标
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 5, 4, 8, 2, 3, 0, 9, 7, 4, 5};
        printArray(quickSort(arr));
    }
}
