package d17_08_14;

/**
 * 随机函数问题
 * 给定一个等概率随机产生1~5的随机函数rand1To5，除此之外，不能使用任何额外的随机机制，
 * 请用rand1To5实现等概率随机产生1~7的随机函数rand1To7。
 */
public class Problem_01_Rand5ToRand7 {

    public static int rand1To5() {
        return (int) (Math.random() * 5) + 1;
    }

    //转化为二进制，用rand1To5得到rand01  1-2返回0 3就retry 4-5返回1
    public static int rand01() {
        int mid = (1 + 5) / 2;
        int temp = rand1To5();
        if (temp < mid) {
            return 0;
        } else if (temp == mid) {
            return rand01();
        } else {
            return 1;
        }
    }

    //1-7需要三位，碰到7就retry，得到0-6，加1即是1-7
    public static int rand1To7() {
        int res = 0;
        for (int i = 0; i < 3; i++) {
            res += rand01() * (1 << i);
        }
        if (res == 7) {
            return rand1To7();
        }
        return res + 1;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(rand1To7());
        }

    }


}
