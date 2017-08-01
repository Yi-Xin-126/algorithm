package d17_07_24;

/**
 * 给定一个全是数字的字符串，返回可以转成合法IP的数量
 * 例如：101111
 * 可以转成：
 * 1.0.1.111, 1.0.11.11, 1.0.111.1
 * 10.1.1.11, 10.1.11.1, 10.11.1.1
 * 101.1.1.1
 * 所以返回7
 */
public class Problem_02_ConvertStringToIP {

    /*
        递归实现，主要过程在process函数
    */
    public static int convertNum1(String str) {
        if (str == null || str.length() < 4 || str.length() > 12) {
            return 0;
        }
        char[] chas = str.toCharArray();
        return process(chas, 0, 0);
    }

    /*
        important
        参数i表示i位置之前，parts表示已经有了个合法ip的段数，返回值为把i后面的字符串加上能转成的合法ip数量
    */
    public static int process(char[] chas, int i, int parts) {
        if (i > chas.length || parts > 4) {     //去除无效情况（越界和ip段数大于4）
            return 0;
        }
        if (i == chas.length) {
            return parts == 4 ? 1 : 0;    //当到了字符串最后，如果段数为4，则是一种合法情况；否则不合法
        }
        int res = process(chas, i + 1, parts + 1);   //把当前字符作为单独一段，去问process函数要结果
        if (chas[i] == '0') {                    //如果当前字符为0，那么它必须单独作为一段，所以直接返回
            return res;
        }
        res += process(chas, i + 2, parts + 1);  //把当前字符和下一个字符结合作为单独一段，去问process函数要结果
        if (i + 2 < chas.length) {
            int sum = (chas[i] - '0') * 100 + (chas[i + 1] - '0') * 10 + (chas[i + 2] - '0');
            if (sum < 256) {
                return res + process(chas, i + 3, parts + 1); //把当前字符和下两个字符结合作为单独一段，去问process函数要结果
            } else {
                return res;
            }
        } else {
            return res;
        }                                        //注意不可能会有四个字符联合在一起作为单独一段的情况
    }

    /*
        将上述convertNum1的递归改为动态规划实现
    */
    public static int convertNum2(String str) {
        if (str == null || str.length() < 4 || str.length() > 12) {
            return 0;
        }
        char[] chas = str.toCharArray();
        int size = chas.length;
        int[][] dp = new int[size + 3][5];
        dp[size][4] = 1;
        for (int parts = 3; parts >= 0; parts--) {
            for (int i = size - 1; i >= parts; i = Math.min(i - 1, parts * 3)) {
                dp[i][parts] = dp[i + 1][parts + 1];
                if (chas[i] != '0') {
                    dp[i][parts] += dp[i + 2][parts + 1];
                    if (i + 2 < chas.length) {
                        int sum = (chas[i] - '0') * 100 + (chas[i + 1] - '0') * 10 + (chas[i + 2] - '0');
                        if (sum < 256) {
                            dp[i][parts] += dp[i + 3][parts + 1];
                        }
                    }
                }
            }
        }
        return dp[0][0];
    }

    public static String getRandomNumberString() {
        char[] chas = new char[(int) (Math.random() * 10) + 3];
        for (int i = 0; i < chas.length; i++) {
            chas[i] = (char) (48 + (int) (Math.random() * 10));
        }
        return String.valueOf(chas);
    }

    public static void main(String[] args) {
        int testTime = 3000000;
        boolean hasErr = false;
        for (int i = 0; i < testTime; i++) {
            String test = getRandomNumberString();
            if (convertNum1(test) != convertNum2(test)) {
                hasErr = true;
            }
        }
        if (hasErr) {
            System.out.println("233333");
        } else {
            System.out.println("666666");
        }

    }

}
