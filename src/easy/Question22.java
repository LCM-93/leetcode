package easy;

import easy.model.ListNode;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn2925/"/>
 * <p>
 * 删除链表的倒数第N个节点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * 示例 2：
 * <p>
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *  
 * <p>
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *  
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
public class Question22 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node9 = new ListNode(9);
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4);
        node1.next = node9;
//        node9.next = node5;
//        node5.next = node4;

        System.out.println(node1);
        ListNode node = removeNthNode1(node1, 2);

        System.out.println(node);
    }


    public static ListNode removeNthNode(ListNode head, int n) {
        ListNode node = head;
        ListNode first = head;
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        if (first == null) return head.next;

        while (first != null) {
            first = first.next;
            node = node.next;
        }

        node.next = node.next.next;
        return head;
    }

    public static ListNode removeNthNode1(ListNode head ,int n){
        ListNode top = new ListNode(0,head);
        ListNode node = top;
        ListNode first = head;

        while (first != null){
            first = first.next;
            if(n-- >0) continue;
            node = node.next;
        }
        node.next = node.next.next;
        return top.next;
    }
}
