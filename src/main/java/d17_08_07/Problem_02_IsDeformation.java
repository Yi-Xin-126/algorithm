package d17_08_07;

/**
 * 变形词
 * 给定两个字符串str1和str2，如果str1和str2中出现的字符种类一样且每种字符出现的次数也一样，
 * 那么str1与str2互为变形词。请实现函数判断两个字符串是否互为变形词。
 * 【举例】
 * str1="123"，str2="231"，返回true。
 * str1="123"，str2="2331"，返回false。
 */
public class Problem_02_IsDeformation {


    /**
     *  使用一个辅助的hash表，遍历第一个字符串，记录在hash表中（字符没出现一次，对应位置加1）。
     *  然后遍历第二个字符串，对应位置减1，如果遍历过程中某个位置出现-1，那么结果直接返回false，
     *  如果遍历结束都没有出现-1，那么返回true
     */
    public static boolean isDeformation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int[] map = new int[256];

        for (int i = 0; i < chars1.length; i++) {
            map[chars1[i]]++;
        }
        for (int i = 0; i < chars2.length; i++) {
            if (map[chars2[i]]-- == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String A = "abcabcabc";
        String B = "bcacbaacb";
        System.out.println(isDeformation(A, B));

    }
}
