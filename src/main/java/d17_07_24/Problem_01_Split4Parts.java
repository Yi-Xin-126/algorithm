package d17_07_24;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 能否把一个正数数组分成4部分问题，每部分的累加和相等，分割点的值不算
 */
public class Problem_01_Split4Parts {

    /*  方法一
        两个指针l和r，l从1开始往右滑，r从n-2开始往左滑，哪边小哪边指针就滑，滑过之后把指针指的数加上，
        记录lsum(不包括arr[l])和rsum(不包括arr[r]),直到lsum==rsum。此时左右两头相等，再找l和r之间的
        某个分割点p。
    例子：      （7）分1 （7） 分2 （7） 分3 （7）
                     l        p          r
             如上图所示，如果存在满足条件的p（分3），那么p左边的和一定为7*2+分1(arr[l]),右边的和一定
             为7*2+分3(arr[l])。所以当前问题就在于如何去寻找这个p(分3)。
        实现一个黑盒来找这个p，黑盒的实现方法：
    例子:     3 1 2 1 4 2
              0 1 2 3 4 5
       首先求出整个数组的和，遍历一遍，sum=13
       然后从下标1开始遍历到下标4，
            下标1  左边和(不包括1)  3    右边和(不包括1) 9    此时往set中加一个3_9
            下标2  左边和(不包括2)  4    右边和(不包括2) 7    此时往set中加一个4_7
            .......
       遍历结束后，黑盒就完成了。
    */
    public static boolean canSplits1(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        Set<String> set = new HashSet<String>();
        int allSum = 0;   //所有数的和
        for (int i = 0; i < arr.length; i++) {
            allSum += arr[i];
        }
        int leftSum = arr[0];   //不包含left的左边部分的累加值
        for (int i = 1; i < arr.length - 1; i++) {
            set.add(String.valueOf(leftSum) + "_" + String.valueOf(allSum - arr[i] - leftSum));
            leftSum += arr[i];
        }
        int l = 1;    //左指针
        int lsum = arr[0];   //不包含l指针 的左边部分的累加和
        int r = arr.length - 2;   //右指针
        int rsum = arr[arr.length - 1];    //不包含r指针 的左边部分的累加和
        while (l < r - 3) {
            if (lsum == rsum) {
                String lkey = String.valueOf(lsum * 2 + arr[l]);
                String rkey = String.valueOf(rsum * 2 + arr[r]);
                if (set.contains(lkey + "_" + rkey)) {
                    return true;
                }
                lsum += arr[l++];
            } else if (lsum < rsum) {
                lsum += arr[l++];
            } else {
                rsum += arr[r--];
            }
        }
        return false;
    }

    /*
        维护一个map<int, int>     key -- value
        key表示0~当前位置的前一个位置的累加和  value表示该累加和最早出现的位置
    例子：    1 2 1 3 1     （1,1） （3,2） （4,3） （7,4）
        然后一个指针p， 从1滑到n-5
        数组： 3 4 1 ........
        下标： 0 1 2 ........
                p
      当p指向下标1时，假设p可以作为分1，那么分2必然满足下面的关系： （3） 分1 （3） 分2 ，
      即分2前面的累加和为3*2+分1，如果可以在map中找到分2，那么分3必然满足下面的关系：
      （3） 分1 （3） 分2 （3） 分3 ， 即分3前面的累加和为3*3+分1+分2。
      如果也可以在map中找到分3，那么如果分3后面的数的和为3，即可返回true；如果不为3，则p向下走一步。
    */
    public static boolean canSplits2(int[] arr) {
        if (arr == null || arr.length < 7) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int sum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            map.put(sum, i);
            sum += arr[i];
        }
        int lsum = arr[0];
        for (int s1 = 1; s1 < arr.length - 5; s1++) {
            int checkSum = lsum * 2 + arr[s1];
            if (map.containsKey(checkSum)) {      //如果找不到分2，就进行下一次循环
                int s2 = map.get(checkSum);
                checkSum += lsum + arr[s2];
                if (map.containsKey(checkSum)) {  //如果找不到分3，就进行下一次循环
                    int s3 = map.get(checkSum);
                    if (checkSum + arr[s3] + lsum == sum) {
                        return true;
                    }
                }
            }
            lsum += arr[s1];
        }
        return false;
    }


    public static int[] generateRandomArray() {
        int[] res = new int[(int) (Math.random() * 10) + 7];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 10) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 3000000;
        boolean hasErr = false;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray();
            if (canSplits1(arr) ^ canSplits2(arr)) {
                hasErr = true;
                break;
            }
        }
        if (hasErr) {
            System.out.println("233333");
        } else {
            System.out.println("666666");
        }

    }
}
