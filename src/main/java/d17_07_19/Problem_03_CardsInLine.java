package d17_07_19;

/**
 * 由暴力递归转到动态规划的题目
 * <p>
 * <p>
 * 有一排正数，玩家A和玩家B都可以看到。
 * 每位玩家在拿走数字的时候，都只能从最左和最右的数中选择一个。
 * 玩家A先拿，玩家B再拿，两人交替拿走所有的数字，
 * 两人都力争自己拿到的数的总和比对方多。请返回最后获胜者的分数
 * 例如：5,2,3,4
 * 玩家A先拿，当前他只能拿走5或者4。
 * 如果玩家A拿走5，那么剩下2，3，4。轮到玩家B，此时玩家B可以选择2或4中的一个，…
 * 如果玩家A拿走4，那么剩下5，2，3。轮到玩家B，此时玩家B可以选择5或3中的一个，…
 */
public class Problem_03_CardsInLine {


    //暴力递归
    public static int win1(int[] arr) {
        int len = arr.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return arr[0];
        }
        return Math.max(f(arr, 0, len - 1), s(arr, 0, len - 1));
    }

    //先拿数的人能得到的最好分数
    /*
        f函数的变量i，j代表拿数的左右范围
        两种情况：1.先拿左加上作为后拿者的身份在左+1到右范围上的分数
                 2.先拿右加上作为后拿者的身份在左到右-1范围上的分数
                 二者取较大值
    */
    public static int f(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];    //base case
        }
        if (i == j - 1) {
            return Math.max(arr[i], arr[j]);   //base case
        }
        return Math.max((arr[i] + s(arr, i + 1, j)), (arr[j] + s(arr, i, j - 1)));
    }

    //后拿数的人能得到的最好分数
    /*
        s函数的变量i，j代表拿数的左右范围
        两种情况：1.作为先拿者的身份在左+1到右范围上的分数
                 2.作为先拿者的身份在左到右-1范围上的分数
                 二者取较小值 （原因；第一个数由先拿者拿走，由于俩人绝对聪明，说以绝对留下较差的情况给后拿者，所以
                                后拿者只能得到较小值）
    */
    public static int s(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        if (i == j - 1) {
            return Math.min(arr[i], arr[j]);
        }
        return Math.min(f(arr, i + 1, j), f(arr, i, j - 1));
    }

    //通过dp表来对win1进行优化
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max((arr[i] + s[i + 1][j]), (arr[j] + s[i][j - 1]));
                s[i][j] = Math.min(f[i][j - 1], f[i + 1][j]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    //只关心先拿数的人的最好分数
    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        return Math.max(p(arr, 0, arr.length - 1), sum - p(arr, 0, arr.length - 1));
    }

    /*
        结合上面f和s函数的分析
    */
    public static int p(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }
        if (i + 1 == j) {
            return Math.max(arr[i], arr[j]);
        }
        return Math.max(arr[i] + Math.min(p(arr, i + 2, j), p(arr, i + 1, j - 1)),
                arr[j] + Math.min(p(arr, i, j - 2), p(arr, i + 1, j - 1)));
    }

    //通过dp表对win3进行的优化
    public static int win4(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
            return arr[0];
        }
        if (arr.length == 2) {
            return Math.max(arr[0], arr[1]);
        }
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        int[][] dp = new int[arr.length][arr.length];
        for (int i = 0; i < arr.length - 1; i++) {
            dp[i][i] = arr[i];
            dp[i][i + 1] = Math.max(arr[i], arr[i + 1]);
        }
        dp[arr.length - 1][arr.length - 1] = arr[arr.length - 1];
        for (int k = 2; k < arr.length; k++) {
            for (int j = k; j < arr.length; j++) {
                int i = j - k;
                dp[i][j] = Math.max(arr[i] + Math.min(dp[i + 1][j - 1], dp[i + 2][j]),
                        arr[j] + Math.min(dp[i + 1][j - 1], dp[i][j - 2]));
            }
        }
        return Math.max(dp[0][arr.length - 1], sum - dp[0][arr.length - 1]);
    }


    public static int[] generateRandomArray() {
        int[] res = new int[(int) (Math.random() * 20) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = (int) (Math.random() * 20) + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int testTime = 50000;
        boolean err = false;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray();
            int r1 = win1(arr);
            int r2 = win2(arr);
            int r3 = win3(arr);
            int r4 = win4(arr);
            if (r1 != r2 || r1 != r3 || r1 != r4) {
                err = true;
            }
        }
        if (err) {
            System.out.println("2333333333");
        } else {
            System.out.println("6666666666");
        }
    }
}
