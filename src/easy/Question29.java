package easy;

import easy.model.TreeNode;

import java.util.*;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn7ihv/"/>
 * <p>
 * 对称二叉树
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 * <p>
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *  
 * <p>
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class Question29 {


    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);
        TreeNode node15 = new TreeNode(15);
        TreeNode node20 = new TreeNode(20, node15, node7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3, node9, node20);

        System.out.println(isSymmetric(node3));
    }


    /**
     * @deprecated 第一时间想到中序遍历结果放list里进行比较  但是这样没有考虑树的结构
     */
    public static boolean isSymmetric(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        printTree(root, list);
//        System.out.println(list.toString());
        int left = 0;
        int right = list.size() - 1;
        while (left < right) {
            if (!list.get(left++).equals(list.get(right--))) return false;
        }
        return true;
    }

    public static void printTree(TreeNode root, List<Integer> list) {
        if (root == null) return;
        printTree(root.left, list);
        list.add(root.val);
        printTree(root.right, list);
    }


    public static boolean isSymmetric1(TreeNode root) {
        if (root == null) return true;
        return isSymmetric1(root.left, root.right);
    }

    public static boolean isSymmetric1(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null || left.val != right.val) return false;
        //左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
        return isSymmetric1(right.right, left.left) && isSymmetric1(right.left, left.right);
    }


    public static boolean isSymmetric2(TreeNode root){
        if(root == null) return true;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root.right);
        queue.add(root.left);

        while (!queue.isEmpty()){
            TreeNode right = queue.poll();
            TreeNode left = queue.poll();

            if(right ==null && left == null) continue;
            if (left == null || right == null || left.val != right.val) return false;

            queue.add(right.right);
            queue.add(left.left);

            queue.add(left.right);
            queue.add(right.left);
        }

        return true;

    }
}
