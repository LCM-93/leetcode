package easy;

import easy.model.TreeNode;

import java.util.Stack;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn08xg/"/>
 * 验证二叉搜索树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *  
 * <p>
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数目范围在[1, 104] 内
 * -231 <= Node.val <= 231 - 1
 */
public class Question28 {

    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);
        TreeNode node15 = new TreeNode(15);
        TreeNode node20 = new TreeNode(20, node15, node7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3, node9, node20);


        boolean validBST = isValidBST(node3);
        System.out.println("isValidBST:" + validBST);
    }


    /**
     * 最开始想到这种方法   但是这种只考虑了最近两层的比较  不能比较所有左子树比root小或所有右子树比root大
     *
     * @deprecated
     */
    public static boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        if ((root.right != null && root.right.val <= root.val) || (root.left != null && root.left.val >= root.val)) {
            return false;
        }
        return isValidBST(root.left) && isValidBST(root.right);
    }

    /**
     * 上面递归方法的修正，添加比较的范围,子节点的范围一定比父节点小
     *
     * @param root
     * @return
     */
    public static boolean isValidBST1(TreeNode root) {
        return isValidBST1(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean isValidBST1(TreeNode root, int minVal, int maxVal) {
        if (root == null) return true;
        if (root.val <= minVal || root.val >= maxVal) return false;
        return isValidBST1(root.left, minVal, root.val) && isValidBST1(root.right, root.val, maxVal);
    }

    /**
     * 笨办法
     *
     * @param root
     * @return
     */
    public static boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        int rootValue = root.val;
        Stack<TreeNode> left = new Stack<>();
        Stack<TreeNode> right = new Stack<>();
        if (root.left != null) left.push(root.left);
        if (root.right != null) right.push(root.right);

        while (!left.isEmpty()) {
            TreeNode node = left.pop();
            if (node.val >= rootValue) return false;
            if (node.right != null) {
                left.push(node.right);
            }
            if (node.left != null) {
                left.push(node.left);
            }
        }

        while (!right.isEmpty()) {
            TreeNode node = right.pop();
            if (node.val <= rootValue) return false;
            if (node.right != null) {
                right.push(node.right);
            }
            if (node.left != null) {
                right.push(node.left);
            }
        }
        return isValidBST2(root.left) && isValidBST2(root.right);
    }


    static TreeNode preNode;

    /**
     * 利用中序遍历 当前节点大小一定大于前一个节点
     *
     * @param root
     * @return
     */
    public static boolean isValidBST3(TreeNode root) {
        if (root == null) return true;

        //访问左子树
        if (!isValidBST3(root.left)) return false;

        //比较当前节点
        if (preNode != null && preNode.val >= root.val) return false;

        preNode = root;
        //访问右子树
        if (!isValidBST3(root.right)) return false;

        return true;
    }


    /**
     * 用非递归的方式进行中序遍历
     * @param root
     * @return
     */
    public static boolean isValidBST4(TreeNode root){
        if(root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();

            if(pre != null && pre.val >= root.val) return false;
            pre = root;

            
            root = root.right;
        }
        return true;
    }
}
