package easy;

import easy.model.TreeNode;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xninbt/"/>
 * 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * <p>
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 *  
 * <p>
 * 示例 1：
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 * <p>
 * 示例 2：
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * nums 按 严格递增 顺序排列
 */
public class Question31 {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5};
        TreeNode treeNode = sortedArrayToBST(nums);
        System.out.println(treeNode.toString());
    }

    /**
     * 递归逻辑理不清
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length <= 0) return null;
        return addTree(nums, 0, nums.length - 1);
    }

    public static TreeNode addTree(int[] nums, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = addTree(nums, start, mid - 1);
        root.right = addTree(nums, mid + 1, end);
        return root;
    }


}
