package d17_08_14;

/**
 * 蓄水池算法
 * 【题目】 有一个机器按自然数序列的方式吐出球（1号球，2号球，3号球，……），你有一个袋子，
 * 袋子最多只能装下K个球，并且除袋子以外，你没有更多的空间。设计一种选择方式，
 * 使得当机器吐出第N号球的时候（N>K），你袋子中的球数是K个，同时可以保证从1号球到N号球中的每一个，
 * 被选进袋子的概率都是K/N。举一个更具体的例子，有一个只能装下10个球的袋子，当吐出100个球时，
 * 袋子里有10个球，并且1~100号中的每一个球被选中的概率都是10/100。然后继续吐球，当
 * 吐出1000个球时，袋子里有10个球，并且1~1000号中的每一个球被选中的概率都是10/1000。
 * 继续吐球，当吐出i个球时，袋子里有10个球，并且1~i号中的每一个球被选中的概率都是10/i，
 * 即吐球的同时，已经吐出的球被选中的概率也动态地变化。
 */
public class Problem_03_ReservoirSampling {

    // 一个简单的随机函数，决定一个事情做还是不做
    public static int rand(int max) {
        // 等概率返回1~max中的一个   -> 0~max-1 +1 -> 1~max
        return (int) (Math.random() * max) + 1;
    }

    public static int[] getKNumsRand(int k, int max) {
        if (max < 1 || k < 1) {
            return null;
        }
        int[] res = new int[Math.min(k, max)];
        for (int i = 0; i != res.length; i++) {
            res[i] = i + 1; // 前k个数直接进袋子
        }
        for (int i = k + 1; i < max + 1; i++) {
            if (rand(i) <= k) { // 决定i进不进袋子
                res[rand(k) - 1] = i; // i随机替掉袋子中的一个
            }
        }
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] res = getKNumsRand(10, 10000);
        printArray(res);
    }
}
