package normal;

import easy.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvix0d/"/>
 * <p>
 * 从前序与中序遍历序列构造二叉树
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 * 示例 2:
 * <p>
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均无重复元素
 * inorder 均出现在 preorder
 * preorder 保证为二叉树的前序遍历序列
 * inorder 保证为二叉树的中序遍历序列
 */
public class Question12 {

    public static void main(String[] args) {
        int[] preOrder = new int[]{3, 9, 20, 15, 7};
        int[] inOrder = new int[]{9, 3, 15, 20, 7};

        TreeNode node = buildTree(preOrder, inOrder);
        System.out.println(node);


    }


    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder.length <= 0) return null;
        List<Integer> preList = new ArrayList<>();
        List<Integer> inList = new ArrayList<>();
        for (int i = preorder.length - 1; i >= 0; i--) {
            preList.add(preorder[i]);
            inList.add(inorder[i]);
        }
        return helper(preList, inList);
    }

    public static TreeNode helper(List<Integer> preList, List<Integer> inList) {
        if (inList.size() <= 0) return null;

        Integer first = preList.remove(0);
        TreeNode root = new TreeNode(first);

        int midIndex = inList.indexOf(first);

        root.left = helper(preList, inList.subList(0, midIndex));

        root.right = helper(preList, inList.subList(midIndex + 1, inList.size()));

        return root;
    }


    public static TreeNode buildTree1(int[] preorder, int[] inorder) {
        if (preorder.length <= 0) return null;

        return helper1(0, 0, inorder.length - 1, preorder, inorder);
    }

    public static TreeNode helper1(int preStart, int inStart, int inEnd, int[] preOrder, int[] inOrder) {
        if (preStart > preOrder.length - 1 || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preOrder[preStart]);
        int index = 0;
        for (int i = inOrder.length - 1; i >= 0; i--) {
            if (inOrder[i] == preOrder[preStart]) {
                index = i;
                break;
            }
        }

        root.left = helper1(preStart + 1, inStart, index - 1, preOrder, inOrder);
        root.right = helper1(preStart + (index - inStart + 1), index + 1, inEnd, preOrder, inOrder);

        return root;
    }


}
