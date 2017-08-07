package sword_offer;

/**
 * 剑指offer中问题9_1：斐波那契数列
 * 现在要求输入一个整数n，请你输出斐波那契数列的第n项。n<=39
 */
public class Problem_09_1 {

    //递归实现，效率巨tm低
    public static int Fabonacci1(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return Fabonacci1(n - 1) + Fabonacci1(n - 2);
    }

    //O(N)
    public static int Fabonacci2(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int one = 0;
        int two = 1;
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = one + two;
            one = two;
            two = res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Fabonacci1(43));
    }
}
