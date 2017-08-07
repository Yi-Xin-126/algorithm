package sword_offer;

/**
 * 剑指offer中问题8：旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 */
public class Problem_08 {

    //O(N)
    public static int minNumberInRotateArray(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }

    //利用二分的思想去找
    public static int minNumberInRotateArray2(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        int i = 0;
        int j = array.length-1;
        while (i < j) {
            if (j - i == 1) {
                return array[j];
            }
            int mid = i + (j-i)/2;
            if (array[i] == array[mid] && array[j] == array[mid]) {    //注意{1,0,1,1,1,1} {1,1,1,1,0,1}
                int min = Integer.MAX_VALUE;                      //这种情况下，二分无法完成，只能顺序查找，记录最小值
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, array[k]);
                }
                return min;
            }
            if (array[i] <= array[mid]) {
                i = mid;
            }
            if (array[j] >= array[mid]) {
                j = mid;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1,1,1,0,1,1};
        System.out.println(minNumberInRotateArray2(arr));
    }
}
