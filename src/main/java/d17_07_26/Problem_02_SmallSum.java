package d17_07_26;

/**
     数组小和的定义如下：
     例如，数组s=[1,3,5,2,4,6]，在s[0]的左边小于或等于s[0]的数的和为0，在s[1]的左边小于或等于s[1]的数的和为1，
     在s[2]的左边小于或等于s[2]的数的和为1+3=4，在s[3]的左边小于或等于s[3]的数的和为1，
     在s[4]的左边小于或等于s[4]的数的和为1+3+2=6，在s[5]的左边小于或等于s[5]的数的和为1+3+5+2+4=15，
     所以s的小和为0+1+4+1+6+15=27。
     给定一个数组s，实现函数返回s的小和。
 */
public class Problem_02_SmallSum {

    public static void main(String[] args) {
        int[] arr = { 1, 3, 5, 2, 4, 6 };
        System.out.println(getSmallSum(arr));

    }


    public static int getSmallSum(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return func(arr, 0, arr.length-1);
    }


    public static int func(int[] arr, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left+right)/2;
        return func(arr, left, mid) + func(arr, mid+1, right) + merge(arr, left, mid, right);
    }

    /*
        在mergeSort中增加一行维护smallSum的代码
        smallSum更新逻辑：每次在某个小区域内两个数比较时，较小的那个数的数值乘上较大数后面的数的数量（包含较大数在内）
    例子             1 3 5 | 2 4 6
               1 3 | 5        2 4 | 6
           1 | 3            2 | 4
      看左边最后一层 1和5比较  smallSum加上 1*1 ； 同理，右边最后一层， 2 和 4 比较，smallSum加上 2*1
      看左边倒数第二层 1和5比较  smallSum加上 1*1 ； 3 和 5 比较，smallSum加上 1*1
      .........
      看左边第一层 1和2比较 smallSum加上 1*3
      按这种方式，mergeSort走完，也就得到了smallSort

    */
    public static int merge(int[] arr, int left, int mid, int right) {
        int[] h = new int[right-left+1];
        int hi = 0;
        int li = left;
        int ri = mid + 1;
        int smallSum = 0;
        while (li <= mid && ri <= right) {
            if (arr[li] <= arr[ri]) {
                smallSum += arr[li]*(right-ri+1);     //important  smallSum更新逻辑
                h[hi++] = arr[li++];
            } else {
                h[hi++] = arr[ri++];
            }
        }
        for (; (li < mid+1) || (ri < right+1); li++, ri++) {
            h[hi++] = li > mid ? arr[ri] : arr[li];
        }
        for (int k = 0; k < h.length; k++) {
            arr[left++] = h[k];
        }
        return smallSum;
    }



}
