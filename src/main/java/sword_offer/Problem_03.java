package sword_offer;

/**
 * 剑指offer中问题3：二维数组中的查找
 * 在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 */
public class Problem_03 {

    public static boolean find(int[][] array, int target) {
        if (array == null) {
            return false;
        }
        int i = 0;
        int j = array[0].length - 1;  //从右上角出发
        while (i < array.length && j >= 0) {
            if (array[i][j] == target) {         //找到目标值就返回
                return true;
            } else if (array[i][j] < target) {   //如果当前值比目标值小，那么就把行加1
                i++;
            } else if (array[i][j] > target) {   //如果当前值比目标值大，那么就把列减1
                j--;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        // 测试用的例子
        int A[][] = {{1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}};
        System.out.println(find(A, 100));
    }
}
