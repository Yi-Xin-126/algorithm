package d17_08_14;

/**
 * 画匠问题
 * 给定一个整型数组arr，数组中的每个值都是正数，表示完成一幅画作需要的时间，再给定一个整数num表示画匠的数量，
 * 每个画匠只能画连在一起的画作。所有的画匠并行工作，请返回完成所有的画作需要的最少时间。
 */
public class Problem_05_ArtistProblem {

    /**
     * 得到数组所有数的累加和，利用二分法  开始范围为 0~累加和
     * 通过调整limit的大小，看看需要的画匠数量是大于画匠总数还是小于画匠总数，决定将答案向上或者向下调整。
     */
    public static int solution(int[] arr, int num) {
        if (arr == null || arr.length < 1 || num < 1) {
            throw new RuntimeException("err");
        }
        if (arr.length < num) {
            int res = Integer.MIN_VALUE;
            for (int i = 0; i < arr.length; i++) {
                res = Math.max(res, arr[i]);
            }
            return res;
        } else {
            int maxSum = 0;
            int minSum = 0;
            for (int i = 0; i < arr.length; i++) {
                maxSum += arr[i];                    //得到整个数组的累加和
            }
            while (minSum != maxSum - 1) {
                int mid = minSum + (maxSum - minSum) / 2;
                if (getNeedNum(arr, mid) > num) {//需要的画匠数量大于画匠总数，那么说明每个画匠还得多做点
                    minSum = mid;
                } else {         //需要的画匠数量小于画匠总数，那么说明每个画匠还可以再少做点
                    maxSum = mid;
                }
            }
            return maxSum;
        }
    }

    /**
     * 子问题：每个画匠的画画时间不超过limit，求出需要多少个画匠
     * 做法：从左到右遍历数组做累加，一旦超过limit，就该分配给下一个画匠来做，并使累加清零，从当前位置继续做
     * 累加。注意：一旦数组中的某个值大于limit，那么说明没有任何一个画匠可以完成，返回一个最大值（即无法完成）
     */
    public static int getNeedNum(int[] arr, int lim) {
        int res = 1;
        int stepSum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > lim) {
                return Integer.MAX_VALUE;
            }
            stepSum += arr[i];
            if (stepSum > lim) {
                res++;
                stepSum = arr[i];
            }
        }
        return res;
    }



}
