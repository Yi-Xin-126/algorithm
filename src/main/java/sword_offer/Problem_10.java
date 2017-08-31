package sword_offer;

/**
 * 二进制中1的个数
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 * 举一反三：
 * 把一个整数减去1后再和原来的整数做位与运算，得到的结果相当于是把整数的二进制中最右边的一个1变成0。
 */
public class Problem_10 {

    //思想：用1（1自身左移运算，其实后来就不是1了）和n的每位进行位与，来判断1的个数
    public static int NumberOf1(int n) {
        int count = 0;
        int flag = 1;
        while (flag != 0) {
            if ((n & flag) != 0) {
                count++;
            }
            flag = flag << 1;  //注意：触发的效率比位运算低得多，应尽量使用位运算代替除法
        }
        return count;
    }

    //最优解
    public static int NumberOf2(int n) {
        if (n == 0) {
            return 0;
        }
        int count = 0;
        while (n != 0) {
            count++;
            n = (n - 1) & n;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1(-10));
        System.out.println(NumberOf2(-10));
    }
}
