package sword_offer;

/**
 * 丑数   把只包含因子2、3和5的数称作丑数（Ugly Number）。
 * 例如6、8都是丑数，但14不是，因为它包含因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 */
public class Problem_34 {

    public static int getUglyNumber(int index) {
        if (index <= 0) {
            return 0;
        }
        int count = 0;
        int[] help = new int[index];
        help[count] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;

        while (count < index-1) {
            int temp = Math.min(help[i2]*2, Math.min(help[i3]*3, help[i5]*5));
            if (temp == help[i2]*2) {
                i2++;
            }
            if (temp == help[i3]*3) {
                i3++;
            }
            if (temp == help[i5]*5) {
                i5++;
            }
            help[++count] = temp;
        }
        return help[index - 1];
    }
}
