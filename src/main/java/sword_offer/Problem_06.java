package sword_offer;

/**
 * 剑指offer中问题6：重建二叉树
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 */
public class Problem_06 {

    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || pre.length == 0 || in == null || in.length == 0) {
            return null;
        }
        TreeNode head = new TreeNode(pre[0]);
        for (int i = 0; i < in.length; i++) {
            if (pre[0] == in[i]) {
                head.left = reConstructBinaryTree(copyArr(pre, 1, i), copyArr(in, 0, i - 1));
                head.right = reConstructBinaryTree(copyArr(pre, i + 1, pre.length - 1), copyArr(in, i + 1, in.length - 1));
            }

        }
        return head;
    }

    public static int[] copyArr(int[] arr, int i, int j) {
        int[] res = new int[j - i + 1];
        for (int k = 0; k < res.length; k++) {
            res[k] = arr[i++];
        }
        return res;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }


}
