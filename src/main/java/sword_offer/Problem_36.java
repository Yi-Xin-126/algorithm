package sword_offer;

/**
 * 数组中的逆序对
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
 */
public class Problem_36 {

    int res;
    public int inversePairs(int[] array) {
        res = 0;
        if (array == null || array.length < 2) {
            return res;
        }
        sort(array, 0, array.length - 1);
        return res;
    }

    public void sort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            sort(arr, left, mid);
            sort(arr, mid + 1, right);
            merge(arr,left,mid,right);
        }
    }

    public void merge(int[] arr, int left, int mid, int right) {
        int[] h = new int[right-left+1];
        int hi = 0;
        int i = left;
        int j = mid+1;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                h[hi++] = arr[i++];
            } else {
                h[hi++] = arr[j++];
                res += mid - i + 1;
                res %= 1000000007;
            }
        }
        for (; (i <= mid || j <= right); i++,j++) {
            h[hi++] = i > mid ? arr[j] : arr[i];
        }
        for (int k = 0; k < h.length; k++) {
            arr[k+left] = h[k];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,0};
        Problem_36 problem_37 = new Problem_36();
        System.out.println(problem_37.inversePairs(arr));
    }
}
