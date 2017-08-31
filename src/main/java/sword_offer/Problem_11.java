package sword_offer;

/**
 * 数值的整数次方
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 */
public class Problem_11 {

    //传统公式求解时间复杂度O(n)
    public static double Power1(double base, int exponent) {
        double result = 1;
        if (base == 0) {
            return 0;
        }
        for (int i = 0; i < Math.abs(exponent); i++) {
            result *= base;
        }
        if (exponent < 0) {
            result = 1/result;
        }
        return result;
    }

    //递归实现，时间复杂度O(logN)
    public static double Power2(double base, int exponent) {
        int n = Math.abs(exponent);
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return base;
        }
        double result = Power2(base, n >> 1);    //用位运算右移代替除法
        result *= result;
        if ((n & 1) == 1) {                      //用位运算与代替取余
            result *= base;
        }
        if (exponent < 0) {
            result = 1/result;
        }
        return result;
    }
}
