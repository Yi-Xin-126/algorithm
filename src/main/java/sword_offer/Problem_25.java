package sword_offer;

import java.util.ArrayList;

/**
 * 二叉树中和为某一值的路径
 * 输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
 * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 */
public class Problem_25 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    public static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> paths = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return paths;
        }
        find(paths, new ArrayList<Integer>(), root, target);
        return paths;
    }

    public static void find(ArrayList<ArrayList<Integer>> paths, ArrayList<Integer> path,
                            TreeNode root, int target) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        ArrayList<Integer> path2 = new ArrayList<Integer>();
        path2.addAll(path);
        if (root.left == null && root.right == null) {
            if (target == root.val) {
                paths.add(path);
                return;
            }
        }
        find(paths, path, root.left, target - root.val);

        find(paths, path2, root.right, target - root.val);
    }
}
