package sword_offer;

import java.util.ArrayList;

/**
 * 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下矩阵：
 * 1 2 3 4
 * 5 6 7 8
 * 9 10 11 12
 * 13 14 15 16
 * 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 */
public class Problem_20 {

    public static ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return null;
        }
        ArrayList<Integer> res = new ArrayList<Integer>();
        int rows = matrix.length;
        int cols = matrix[0].length;

        int layer = (Math.min(rows, cols)-1) / 2 + 1;   //这个是打印的层数
        for (int i = 0; i < layer; i++) {
            for (int j = i; j <= cols - i - 1; j++) { //从左到右
                res.add(matrix[i][j]);
            }
            for (int j = i + 1; j <= rows - i - 1 ; j++) { //从右上到右下
                res.add(matrix[j][cols - i - 1]);
            }
            for (int j = cols - i - 2; (j >= i)&&(rows - i - 1 != i); j--) {   //从右下到左下
                res.add(matrix[rows - i - 1][j]);
            }
            for (int j = rows - i - 2; (j >= i + 1)&&(cols - i - 1 != i) ; j--) { //从左下到左上
                res.add(matrix[j][i]);
            }
        }
        return res;
    }
}
