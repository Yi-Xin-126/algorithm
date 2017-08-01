package d17_07_20;

import java.util.Scanner;
import java.util.Stack;

/**
 * 山峰问题
 * 小B负责首都的防卫工作。
 * 首都处于一个四面环山的盆地中，周围的n个小山构成一个环，作为预警措施，
 * 小B计划在每个小山上设置一个观察哨，日夜不停的瞭望周围发生的情况。
 * 一旦发生外敌入侵事件，山顶上的岗哨将点燃烽烟。
 * 若两个岗哨所在的山峰之间的那些山峰，高度都不大于这两座山峰，且这两个山峰之间有相连通路，
 * 则岗哨可以观察到另一个山峰上的烽烟是否点燃。
 * 由于小山处于环上，任意两个小山之间存在两个不同的连接通路。
 * 满足上述不遮挡的条件下，一座山峰上岗哨点燃的烽烟至少可以通过一条通路被另一端观察到。
 * 对于任意相邻的岗哨，一端的岗哨一定可以发现一端点燃的烽烟。
 * 小B设计的这种保卫方案的一个重要特性是能够观测到对方烽烟的岗哨对的数量，
 * 她希望你能够帮她解决这个问题。
 * 输入
 * 输入中有多组测试数据。每组测试数据的第一行为一个整数n（3<=n <=10^6），为首都周围的小山数量，
 * 第二行为n个整数，依次表示小山的高度h，（1<=h<=10^9）。
 * 输出
 * 对每组测试数据，在单独的一行中输出能相互观察到的岗哨的对数。
 * 样例输入
 * 5
 * 1 2 4 5 3
 * 样例输出
 * 7
 */
public class Problem_01_MountainsAndFlame {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int size = in.nextInt();
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = in.nextInt();
            }
            System.out.println(communications(arr));
        }
    }

    /*
        获得环状数组的下一个位置
        如果没到数组末尾（size-1），则当前位置往后加1
        如果当前位置到了数组末尾，则下一个为更新到0
    */
    public static int nextIndex(int size, int i) {
        return i < (size - 1) ? (i + 1) : 0;
    }

    /*
        获得相同高度的山峰内部之间可以相互看到的对数
        若相同高度山峰数量为1，则对数为0
        若相同高度山峰数量为n，则对数为组合中的n取2  ==  n*(n-1)/2
    */
    public static long getInternalSum(int n) {
        return n == 1L ? 0L : (long) n * (long) (n - 1) / 2L;
    }

    /*  用到单调栈（由栈顶到栈底依次变大）的知识，
        能知道一个数组中的某个数i左边离它最近的比它大的数和右边离它最近的比它大的数。
    例子   5 2 1 4 3 7    压5，压2，压1，压4的时候，因为4>1，所以单调栈要进行更新操作
           左（在栈中它的底下是谁）    右（它因为谁而弹出）
        1     2                              4
        2     5                              4
        3     4                              7
        4     5                              7
        5     null                           7
        7     null                           null

        明确寻找策略：当前的山峰往左右两边去看比它高的山峰
        首先遍历一遍，找到某一个最高的山峰，高度记为value，位置下标记为maxIndex
        然后再从最高的山峰的下一个位置出发，维护单调栈，每次弹出元素时，把弹出元素能够贡献的对数加到结果中

    */
    public static long communications(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int size = arr.length;
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        int value = arr[maxIndex];
        int index = nextIndex(size, maxIndex);
        long res = 0L;
        Stack<Pair> stack = new Stack<Pair>();      //单调栈维护山峰的信息
        stack.push(new Pair(value));                //把最高峰压入
        while (index != maxIndex) {                 //从最高的山峰的下一个位置出发遍历一遍
            value = arr[index];
            while (!stack.isEmpty() && stack.peek().value < value) {     //当压入元素比栈顶元素大时就弹出
                int times = stack.pop().times;
                res += getInternalSum(times) + times;      //内部的对数加上它看向压入元素时产生的对数
                res += times;                              //它看向它底下的元素时产生的对数
            }
            if (!stack.isEmpty() && stack.peek().value == value) {
                stack.peek().times++;                      //相同高度的山峰压到一起，数量加1
            } else {
                stack.push(new Pair(value));
            }
            index = nextIndex(size, index);                //获得下一次流程的下标
        }
        while (!stack.isEmpty()) {           //栈中剩余的元素从栈顶到栈底 由小到大依次弹出
            int times = stack.pop().times;
            res += getInternalSum(times);    //内部的对数
            if (!stack.isEmpty()) {          //不是栈底元素
                res += times;                //弹出元素向右看时的对数
                if (stack.size() > 1) {      //弹出元素向左看时，如果它看到的不吃栈底，则直接加times
                    res += times;
                } else {                     //如果它向左看到的是栈底（即最高的山峰），那说明它前一步向右看到的一定也是栈底这个元素
                    res += stack.peek().times > 1 ? times : 0;  //这是需要判断栈底元素的个数不是1，则加times，否则不加
                }                                               //因为它从左右两边看到的是同一座山峰，只能加上一次
            }
        }
        return res;
    }

    /*
        这个数据维护的是
        value是山峰的高度，times是该高度下山峰的数量
    */
    public static class Pair {
        public int value;
        public int times;

        public Pair(int value) {
            this.value = value;
            this.times = 1;
        }
    }

}
