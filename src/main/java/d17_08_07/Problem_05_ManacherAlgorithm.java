package d17_08_07;

/**
 * manacher算法，给定一个字符串str，返回str中最长的回文子串的长度
 * 【进阶题目】
 * 给定一个字符串str，想通过添加字符的方式使得str整体都变成回文字符串，但要求只能在str的末尾添加字符，请返回
 * 在str后面添加的最短字符串。
 */
public class Problem_05_ManacherAlgorithm {

    /*
        对字符串进行处理，解决奇回文和偶回文的问题
        处理方式 变原字符串 121 为 #1#2#1#
    */
    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];  //(i&1)
    }                                                 //偶数与(&)上1，结果为0；奇数与(&)上1，结果不为0
        return res;
    }

    /*
        如果 pR>i，说明当前位置被pR包住，那么就初始化为 i关于index的对称点的最大回文半径 和 i到pR-1的距离
        两者之间的最大值
        如果不满足 pR>i，说明当前位置没有被pR包住，那么就初始化为1，没有优化，简单地依次往外扩
    */
    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];  //记录每个位置能扩出去的最大回文半径
        int pR = -1;                // important   记录之前所有的回文半径中，最右即将到达的位置
        int index = -1;                        //记录最近一次更新pR时，那个回文中心的位置
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i - pArr[i] > -1 && i + pArr[i] < charArr.length) { //判断是否超出左边界和是否超出右边界
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])   //判断能否继续往外扩
                pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {     //判断当前情况是否能更新pR
                pR = i + pArr[i];
                index = i;
            }
            max = Math.max(max, pArr[i]);  //记录过程中的回文半径的最大值
        }
        return max - 1;     //因为对原字符串进行过处理，所以返回值为回文半径的最大值减去一(可以举例验证)
    }

    public static String shortestEnd(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int maxContainsEnd = -1;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            if (pR == charArr.length) {
                maxContainsEnd = pArr[i] - 1;
                break;
            }
        }
        char[] res = new char[str.length() - maxContainsEnd];
        for (int i = 0; i < res.length; i++) {
            res[res.length - 1 - i] = charArr[i * 2 + 1];
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));

        String str2 = "abcd123321";
        System.out.println(shortestEnd(str2));

    }
}
