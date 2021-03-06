package sword_offer;

/**
 * 树的子结构
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class Problem_18 {

    public static class TreeNode {
        public int val = 0;
        public TreeNode left = null;
        public TreeNode right = null;

        public TreeNode (int val) {
            this.val = val;
        }
    }

    public static boolean hasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        }
        boolean result = false;
        if (root1.val == root2.val) {
            result = isTree1HaveTree2(root1, root2);
        }
        if (!result) {
            result = hasSubtree(root1.left, root2);
        }
        if (!result) {
            result = hasSubtree(root1.right, root2);
        }
        return result;
    }

    public static boolean isTree1HaveTree2(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }
        if (root1.val != root2.val) {
            return false;
        }
        return isTree1HaveTree2(root1.left, root2.left) && isTree1HaveTree2(root1.right, root2.right);
    }
}
