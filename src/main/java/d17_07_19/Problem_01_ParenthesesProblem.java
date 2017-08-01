package d17_07_19;

/**
 * 已知一个字符串都是由左括号(和右括号)组成，判断该字符串是否是有效的括号组合。
 * 例子：
 * 有效的括号组合:()(),(()),(()())
 * 无效的括号组合:(,()),((),()(()
 * 题目进阶：
 * 已知一个字符串都是由左括号(和右括号)组成，返回最长有效括号子串的长度。
 */
public class Problem_01_ParenthesesProblem {

    /*
        判断字符串是否为有效的括号组合
        定义一个变量count，初始为0，遍历字符串，碰到（就加1，碰到）就减1。
        如果在遍历途中出现count<0的情况，则说明不合法，返回false。
        遍历结束后，如果count==0，则说明合法，否则不合法。
    */
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

    /*
        求最长有效括号子串的长度。
        dp数组记录以该位置结尾的最长有效子串的长度
  举例  ()()()
        012345
     dp[020406]   那么已知dp[0....i]的情况下，如何求得dp[i+1]?
        dp[5] = dp[4]+2 = 6
        dp[i+1] = dp[i] + 2 + #  特别注意#这一部分 代表情况为 ()|(()())
    */
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
            if (chars[i] == ')') {       //当前字符为）时才考虑有可能以该位置字符结尾的最长有效子串，（时不考虑
                pre = i - dp[i - 1] - 1;    //pre代表与当前）对应的前面的字符
                if (pre >= 0 && chars[pre] == '(') {   //pre为（，则可以匹配上
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);    //得到dp[i]
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
