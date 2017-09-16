package sword_offer;

/**
 * 平衡二叉树
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class Problem_39_2 {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }


    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean[] res = new boolean[1];
        res[0] = true;
        getHeight(root, 1, res);
        return res[0];
    }

    public static int getHeight(TreeNode root, int level, boolean[] res) {
        if (root == null) {
            return level;
        }
        int lH = getHeight(root.left, level + 1, res);
        if (!res[0]) {
            return level;
        }
        int rH = getHeight(root.right, level + 1, res);
        if (!res[0]) {
            return level;
        }
        if (Math.abs(lH - rH) > 1) {
            res[0] = false;
        }
        return Math.max(lH, rH);
    }
}
