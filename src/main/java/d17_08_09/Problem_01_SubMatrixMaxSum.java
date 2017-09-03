package d17_08_09;

/**
 * 子矩阵的最大累加和
 */
public class Problem_01_SubMatrixMaxSum {

    /**
     * 首先先搞定一个子问题：子数组的最大累加和（O(N)）
     * 整个求解过程如下：
     * 假设有一个3行的矩阵，a.求只包含第一行的数的最大累加和
     *                    b.求包含一二行的数的最大累加和
     *  c.一二三行  d.二行  e.二三行  f.三行
     *  对于第一行来说，就是求子数组的最大累加和，一二行来说，1和2行先合并（列对应相加），然后就相当于一行了
     *  依次类推，记录整个过程的最大值，就是所求解
     */
    public static int maxSum(int[][] m) {
        if (m == null || m.length == 0 || m[0].length == 0) {
            return 0;
        }
        int[] s = null;  //累加数组
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i = 0; i < m.length; i++) {
            s = new int[m[0].length];
            for (int j = i; j < m.length; j++) {
                cur = 0;
                for (int k = 0; k < m[0].length; k++) {
                    s[k] += m[j][k];
                    cur += s[k];
                    max = Math.max(cur, max);
                    cur = cur < 0 ? 0 : cur;
                }
            }
        }
        return max;
    }


    public static void main(String[] args) {
        int[][] matrix = { { -90, 48, 78 }, { 64, -40, 64 }, { -81, -7, 66 } };
        System.out.println(maxSum(matrix));

    }

}
