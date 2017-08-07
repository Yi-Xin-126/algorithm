package sword_offer;

/**
 * 剑指offer中问题9_2：跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 */
public class Problem_09_2 {

    public static int JumpFloor(int target) {
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int one = 1;
        int two = 2;
        int res = 0;
        for (int i = 3; i <= target; i++) {
            res = one + two;
            one = two;
            two = res;
        }
        return res;
    }
}
