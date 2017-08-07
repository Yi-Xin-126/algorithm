package sword_offer;

/**
 * 剑指offer中问题4：替换空格
 * 请实现一个函数，将一个字符串中的空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 */
public class Problem_04 {

    public static String replaceSpace(StringBuffer str) {
        if (str == null || str.length() == 0) {
            return str.toString();
        }
        StringBuffer sb = new StringBuffer();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
}
