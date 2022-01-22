package normal;

import normal.model.Node;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvijdh/"/>
 * <p>
 * 填充每个节点的下一个右侧节点指针
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * <p>
 *  
 * <p>
 * 进阶：
 * <p>
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：root = [1,2,3,4,5,6,7]
 * 输出：[1,#,2,3,#,4,5,6,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。序列化的输出按层序遍历排列，同一层节点由 next 指针连接，'#' 标志着每一层的结束。
 *  
 */
public class Question13 {

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        Node connect = connect(node1);

        System.out.println(connect);

    }

    public static Node connect(Node root) {
        if (root == null) return null;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int count = queue.size();
            Node pre = null;

            for (int i = 0; i < count; i++) {
                Node poll = queue.poll();

                if (pre != null) {
                    pre.next = poll;
                }
                pre = poll;

                if (poll.left != null)
                    queue.add(poll.left);
                if (poll.right != null)
                    queue.add(poll.right);
            }
        }

        return root;

    }

    /**
     * 直接按层遍历 链接链表
     * @param root
     * @return
     */
    public static Node connect1(Node root) {
        if (root == null) return null;

        Node cur = root;
        while (cur != null) {
            Node dummy = new Node(0);  //构建一个空节点
            Node pre = dummy;

            //因为是完美二叉树，所以必有左右节点或同时没有左右节点
            while (cur != null && cur.left != null) {
                pre.next = cur.left;
                pre = pre.next;

                pre.next = cur.right;
                pre = pre.next;

                cur = cur.next; //cur通过next指向同层级的下一个节点  继续遍历下一层的节点
            }

            cur = dummy.next;

        }

        return root;

    }

}
