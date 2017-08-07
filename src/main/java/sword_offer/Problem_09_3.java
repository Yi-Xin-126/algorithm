package sword_offer;

/**
 * 剑指offer中问题9_3：变态跳台阶
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级.....也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 * 思路：每个台阶都有跳与不跳两种情况（除了最后一个台阶），最后一个台阶必须跳。所以共用2^(n-1)中情况
 */
public class Problem_09_3 {

    public static int JumpFloor(int target) {
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        return (int) Math.pow(2, target - 1);
    }

    public static void main(String[] args) {
        System.out.println(JumpFloor(3));
    }
}
