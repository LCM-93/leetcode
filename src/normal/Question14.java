package normal;

import easy.model.TreeNode;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvuyv3/"/>
 * <p>
 * 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,1,4,null,2], k = 1
 * 输出：1
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,null,1], k = 3
 * 输出：3
 *  
 *  
 * <p>
 * 提示：
 * <p>
 * 树中的节点数为 n 。
 * 1 <= k <= n <= 104
 * 0 <= Node.val <= 104
 *  
 * <p>
 * 进阶：如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化算法？
 */
public class Question14 {

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node4 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);

        node3.left = node1;
        node3.right = node4;
        node1.right = node2;

        int i = kthSmallest(node3, 1);
        System.out.println(i);

    }

    static int count = 0;
    static int target = -1;

    public static int kthSmallest(TreeNode root, int k) {
        count = k;
        helper(root);
        return target;
    }

    public static void helper(TreeNode root) {
        if (root == null) return;

        helper(root.left);

        if (--count == 0) {
            target = root.val;
            return;
        }

        helper(root.right);
    }


    /**
     * 通过节点个数来查找  类似于二分查找
     * <p>
     * 如果左子节点的个数大于等于k，说明要找的元素就在左子节点中
     * 否则如果左子节点的个数加上当前节点个数（1）等于k，说明当前节点就是要找的元素，直接返回即可。
     * 否则我们要找的元素在右子节点中，直接到右子节点中查找。
     *
     * @return
     */
    public static int kthSmallest1(TreeNode root, int k) {
        int countLeft = countNode(root.left);

        if (countLeft >= k) {
            return kthSmallest1(root.left, k);
        } else if (countLeft + 1 == k) {
            return root.val;
        } else {
            return kthSmallest1(root.right, k - 1 - countLeft);
        }
    }

    public static int countNode(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNode(root.left) + countNode(root.right);
    }
}
