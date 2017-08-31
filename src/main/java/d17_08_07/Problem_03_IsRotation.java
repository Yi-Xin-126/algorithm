package d17_08_07;

/**
 * 如果一个字符串str，把字符串str前面任意的部分挪到后面形成的字符串叫作str的旋转词。
 * 比如str="12345"，str的旋转词有"12345"、"23451"、"34512"、"45123"和"51234"。
 * 给定两个字符串a和b，请判断a和b是否互为旋转词。
 */
public class Problem_03_IsRotation {


    //两个字符串 a 和 b，首先生成一个a+a的字符串，在a+a中看能否找到b（利用kmp算法），能找到就说明
    //互为旋转词；否则不互为旋转词
    public static boolean isRotation(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return false;
        }
        String temp = str1 + str1;
        return getIndexOf(temp, str2) != -1;
    }

    //kmp
    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int si = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (si < ss.length && mi < ms.length) {
            if (ss[si] == ms[mi]) {
                si++;
                mi++;
            } else if (next[mi] == -1) {
                si++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? si - mi : -1;
    }

    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "yunzuocheng";
        String str2 = "zuochengyun";
        System.out.println(isRotation(str1, str2));

    }
}
