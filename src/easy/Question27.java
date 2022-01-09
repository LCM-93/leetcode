package easy;

import easy.model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

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
        System.out.println("maxDepth:" + maxDepth);
    }


    public static int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    public static int maxDepth(TreeNode root, int curDepth) {
        if (root == null) return curDepth;
        return Math.max(maxDepth(root.left, curDepth + 1), maxDepth(root.right, curDepth + 1));
    }


    /**
     * BFS 广度优先搜索
     *
     * @param root
     * @return
     */
    public static int maxDepth1(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        int count = 0;

        while (!deque.isEmpty()) {
            int size = deque.size(); //每一层拥有的节点个数
            while (size-- > 0) {
                TreeNode cur = deque.pop();
                if (cur.left != null) {
                    deque.push(cur.left);
                }
                if (cur.right != null) {
                    deque.push(cur.right);
                }
            }
            count++;
        }
        return count;
    }


    /**
     * FDS 深度优先搜索
     * 用两个栈同时记录节点以及节点所在的层数
     *
     * @param root
     * @return
     */
    public static int maxDepth2(TreeNode root) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> level = new Stack<>();
        int max = 0;
        stack.push(root);
        level.push(1);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            Integer curLevel = level.pop();
            max = Math.max(curLevel, max);
            if (node.right != null) {
                stack.push(node.right);
                level.push(curLevel + 1);
            }
            if (node.left != null) {
                stack.push(node.left);
                level.push(curLevel + 1);
            }
        }
        return max;
    }


}
