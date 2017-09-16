package sword_offer;

/**
 * 数组中只出现一次的数字
 * 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class Problem_40 {

    //num1,num2分别为长度为1的数组。传出参数
    //将num1[0],num2[0]设置为返回结果
    public static void findNumsAppearOnce(int[] array, int[] num1, int[] num2) {
        int eO = 0, eO2 = 0;
        for (int cur : array) {
            eO = eO^cur;
        }
        int low = eO & (~eO+1);
        for (int cur : array) {
            if ((cur & low) != 0) {
                eO2 = eO2^cur;
            }
        }
        num1[0] = eO2;
        num2[0] = eO^eO2;
    }
}
