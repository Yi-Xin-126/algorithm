package sword_offer;

/**
 * 把字符串转换成整数
 * 将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 */
public class Problem_48 {

    public static int strToInt(String str) {
        if (str == null || str.equals("") || str.length() < 1) {
            return 0;
        }
        char[] cs = str.toCharArray();
        int sign = 0;
        if (cs[0] == '-') {
            sign = 1;
        }
        int res = 0;
        for (int i = sign; i < cs.length; i++) {
            if (cs[i] == '+') {
                continue;
            }
            if (cs[i] < '0' || cs[i] > '9') {
                return 0;
            }
            res = res * 10 + cs[i] - '0';
        }
        return sign == 0 ? res : res*(-1);
    }
}
