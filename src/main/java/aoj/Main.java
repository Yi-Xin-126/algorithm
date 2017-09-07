package aoj;

import java.util.Scanner;

/**
 * oj中java语言的Main函数
 */
public class Main {
    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int n = cin.nextInt();
        long[] help = {1L, 2L};
        long res = 0L;
        for (int i = 2; i < n; i++) {
            res = (long)help[0] + (long)help[1] + 1;
            help[0] = help[1];
            help[1] = res;
        }
        System.out.println(res%1000000007);
    }
}

