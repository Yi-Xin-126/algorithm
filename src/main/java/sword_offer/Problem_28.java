package sword_offer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 字符串的排列
 * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。
 * 例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
 */
public class Problem_28 {

    public ArrayList<String> permutation(String str) {
        ArrayList<String> res = new ArrayList<String>();
        if (str == null || str.length() < 1) {
            return res;
        }
        char[] chars = str.toCharArray();
        process(res, chars, 0);
        Collections.sort(res);
        return res;
    }

    public static void process(ArrayList<String> res, char[] chars, int start) {
        if (start == chars.length - 1) {
            res.add(String.valueOf(chars));
        } else {
            for (int i = start; i < chars.length; i++) {
                if (start == i || chars[start] != chars[i]) {
                    swap(chars, start, i);
                    process(res, chars, start+1);
                    swap(chars, start, i);
                }
            }
        }

    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
}
