package sword_offer;

import java.util.HashMap;

/**
 * 第一个只出现一次的字符
 * 在一个字符串(1<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置
 */
public class Problem_35 {

    public static int firstNotRepeatingChar(String str) {
        if (str == null || str.length() < 1) {
            return -1;
        }
        char[] cs = str.toCharArray();
        HashMap<Character, Integer> help = new HashMap<Character, Integer>();
        for (int i = 0; i < cs.length; i++) {
            if (help.containsKey(cs[i])) {
                help.put(cs[i], help.get(cs[i])+1);
            } else {
                help.put(cs[i], 1);
            }
        }
        for (int i = 0; i < cs.length; i++) {
            if (help.get(cs[i]) == 1) {
                return i;
            }
        }
        return -1;
    }
}
