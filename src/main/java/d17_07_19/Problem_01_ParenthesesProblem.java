package d17_07_19;

/**
 * Created by YiXin on 2017/7/29.
 */
public class Problem_01_ParenthesesProblem {

    public static boolean isValid(String str) {
        char[] chars = str.toCharArray();
        int len = chars.length;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (count < 0) {
                return false;
            }
            if (chars[i] == '(') {
                count++;
            }
            if (chars[i] == ')') {
                count--;
            }
        }
        return count == 0;
    }

    public static int maxLength(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int len = chars.length;
        int pre = 0;
        int res = 0;
        int[] dp = new int[len];
        for (int i = 1; i < len; i++) {
            if (chars[i] == ')') {
                pre = i - dp[i-1] -1;
                if (pre >= 0 && chars[pre] == '(') {
                    dp[i] = dp[i-1] + 2 + (pre > 0 ? dp[pre-1] : 0);
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }


    public static void main(String[] args) {
        String str1 = "((())())";
        System.out.println(str1.length());
        System.out.println(isValid(str1));
        System.out.println(maxLength(str1));

        String str2 = "(())(()(()))";
        System.out.println(str2.length());
        System.out.println(isValid(str2));
        System.out.println(maxLength(str2));

        String str3 = "()(()()(";
        System.out.println(str3.length());
        System.out.println(isValid(str3));
        System.out.println(maxLength(str3));

    }
}
