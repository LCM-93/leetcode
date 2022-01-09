package easy;

import easy.model.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Question30 {


    public static void main(String[] args) {
        TreeNode node7 = new TreeNode(7);
        TreeNode node15 = new TreeNode(15);
        TreeNode node20 = new TreeNode(20, node15, node7);
        TreeNode node9 = new TreeNode(9);
        TreeNode node3 = new TreeNode(3, node9, node20);

        List<List<Integer>> lists = levelOrder1(node3);
        System.out.println(lists.toString());

    }

    /**
     * 广度优先算法 BFS
     *
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (node != null) {
                    list.add(node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            result.add(list);
        }
        return result;
    }


    /**
     * 深度优先算法 DFS
     *
     * @return
     */
    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        levelOrder1Helper(result, root, 0);
        return result;
    }

    public static void levelOrder1Helper(List<List<Integer>> result, TreeNode root, int level) {
        if (root == null) return;
        if (level >= result.size()) { //如果层数等于集合长度，需要先初始化对应层数的List集合
            result.add(new ArrayList<>());
        }

        result.get(level).add(root.val);
        levelOrder1Helper(result, root.left, level + 1);
        levelOrder1Helper(result, root.right, level + 1);
    }
}
