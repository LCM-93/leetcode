package easy;

import easy.model.ListNode;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnarn7/"/>
 * 删除链表中的节点
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点。传入函数的唯一参数为 要被删除的节点 。
 *  
 * <p>
 * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：head = [4,5,1,9], node = 5
 * 输出：[4,1,9]
 * 解释：给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
 * 示例 2：
 * <p>
 * 输入：head = [4,5,1,9], node = 1
 * 输出：[4,5,9]
 * 解释：给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
 */
public class Question21 {

    public static void main(String[] args) {
        ListNode node9 = new ListNode(9);
        ListNode node1 = new ListNode(1);
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4);
        node1.next = node9;
        node5.next = node1;
        node4.next = node5;

        System.out.println(node4);
        deleteNode(node9);
        System.out.println(node4);

    }

    public static void deleteNode(ListNode node) {
        if(node.next == null){
            return;
        }
        node.val = node.next.val;
        node.next = node.next.next;
    }


}
