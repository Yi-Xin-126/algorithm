package sword_offer;

/**
 * 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。
 * 假设输入的数组的任意两个数字都互不相同。
 */
public class Problem_24 {

    public static boolean verifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return process(sequence, 0, sequence.length - 1);
    }

    public static boolean process(int[] arr, int start, int end) {
        if (end <= start) {
            return true;
        }
        int root = arr[end];
        int pos = end;
        for (int i = start; i < end; i++) {
            if (arr[i] > root) {
                pos = i;
                break;
            }
        }
        for (int i = pos + 1; i < end; i++) {
            if (arr[i] < root) {
                return false;
            }
        }
        return process(arr, start, pos-1) && process(arr, pos, end-1);
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(verifySequenceOfBST(arr));
    }

}
