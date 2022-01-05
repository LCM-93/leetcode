package easy;

import easy.model.TreeNode;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnd69e/"/>
 * 二叉树的最大深度
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 * <p>
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回它的最大深度 3 。
 */
public class Question27 {

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);
        TreeNode node15 = new TreeNode(15);
        TreeNode node20 = new TreeNode(20, node15, node7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3, node9, node20);

        int maxDepth = maxDepth(node3);
        System.out.println("maxDepth:"+maxDepth);
    }


    public static int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    public static int maxDepth(TreeNode root, int curDepth) {
        if (root == null) return curDepth;
        return Math.max(maxDepth(root.left, curDepth + 1), maxDepth(root.right, curDepth + 1));
    }


}
