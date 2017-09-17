package sword_offer;

/**
 * 扑克牌顺子
 * LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
 * 他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,
 * 嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,
 * 他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
 * 上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
 * 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何。为了方便起见,你可以认为大小王是0。
 */
public class Problem_44 {

    public static boolean isContinuous(int[] numbers) {
        if (numbers == null || numbers.length != 5) {
            return false;
        }
        numbers = heapSort(numbers);
        int count0 = 0;
        for (int i = 0; i < 5; i++) {
            if (numbers[i] == 0) {
                count0++;
            } else if ((i-1) > 1 && numbers[i] == numbers[i-1]) {
                return false;
            }
        }
        for (int i = 1; i < 5; i++) {
            if (numbers[i-1] == 0) {
                continue;
            }
            int temp = numbers[i] - numbers[i-1] - 1;
            count0 -= temp;
            if (count0 < 0) {
                return false;
            }
        }
        return true;
    }

    public static int[] heapSort(int[] arr) {
        //1,建立大根堆
        arr = buildHeap(arr);
        //2,调整排序
        for (int i = arr.length - 1; i > 0; i--) {
            //堆顶和堆底元素交换
            swap(arr, 0, i);
            heapAdjust(arr, 0, i);//将剩余的元素整理成堆
        }
        return arr;
    }

    public static int[] buildHeap(int[] arr) {
        for (int i = arr.length / 2; i >= 0; i--) {
            heapAdjust(arr, i, arr.length);
        }
        return arr;
    }

    public static void heapAdjust(int[] arr, int parent, int length) {
        int temp = arr[parent];
        int child = 2*parent+1;
        while (child < length) {
            if (child + 1 < length && arr[child] < arr[child+1]) {
                child++;
            }
            if (arr[child] <= temp) {
                break;
            }
            arr[parent] = arr[child];
            parent = child;
            child = 2*child + 1;
        }
        arr[parent] = temp;    //直到找到合适的位置，才将这个值交换过去
    }

    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {0,3,2,6,4};
        printArray(heapSort(arr));
        System.out.println(isContinuous(arr));
    }

}
