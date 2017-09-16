package sword_offer;

import java.util.ArrayList;

/**
 * 和为S的连续正数序列
 * 小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,他马上就写出了正确答案是100。
 * 但是他并不满足于此,他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。
 * 没多久,他就得到另一组连续正数和为100的序列:18,19,20,21,22。
 * 现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!
 */
public class Problem_41_1 {

    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (sum < 3) {
            return res;
        }
        int small = 1;
        int big = 2;
        int cur = small+big;
        while (2*big-1 <= sum && small < big) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            if (cur == sum) {
                for (int i = small; i <= big; i++) {
                    temp.add(i);
                }
                res.add(temp);
                cur -= small;
                small++;
            } else if (cur < sum) {
                big++;
                cur += big;
            } else {
                cur -= small;
                small++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        findContinuousSequence(9);
    }
}
