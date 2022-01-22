package normal;

import easy.model.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvle7s/"/>
 * <p>
 * 二叉树的锯齿形层次遍历
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 * 示例 2：
 * <p>
 * 输入：root = [1]
 * 输出：[[1]]
 * 示例 3：
 * <p>
 * 输入：root = []
 * 输出：[]
 *  
 * <p>
 * 提示：
 * <p>
 * 树中节点数目在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 */
public class Question11 {

    public static void main(String[] args) {
        TreeNode node3 = new TreeNode(3);
        TreeNode node9 = new TreeNode(9);
        TreeNode node20 = new TreeNode(20);
        TreeNode node15 = new TreeNode(15);
        TreeNode node7 = new TreeNode(7);
        node3.left = node9;
        node3.right = node20;
        node20.left = node15;
        node20.right = node7;

        List<List<Integer>> lists = zigzagLevelOrder(node3);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }


    /**
     * DFS  深度优先算法
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        zigzagHelper(root, result, 0);


        return result;
    }

    public static void zigzagHelper(TreeNode treeNode, List<List<Integer>> lists, int height) {
        if (treeNode == null) return;
        if (lists.size() <= height) lists.add(new ArrayList<>());


        zigzagHelper(treeNode.left, lists, height + 1);

        if (height % 2 == 0) {
            lists.get(height).add(treeNode.val);
        } else {
            lists.get(height).add(0, treeNode.val);
        }

        zigzagHelper(treeNode.right, lists, height + 1);

    }


    /**
     * BFS  广度优先算法
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> zigzagLevelOrder1(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root); //相当于把数据加入到队列尾部

        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int count = queue.size();

            for (int i = 0; i < count; i++) {
                TreeNode poll = queue.poll(); //poll方法相当于移除队列头部的元素
                if (leftToRight){
                    level.add(poll.val);
                }else{
                    level.add(0,poll.val);
                }

                //把数据加入到队列尾部
                if(poll.left != null)
                    queue.add(poll.left);
                if (poll.right != null)
                    queue.add(poll.right);

            }

            result.add(level);
            leftToRight =!leftToRight;
        }
        return result;

    }
}
