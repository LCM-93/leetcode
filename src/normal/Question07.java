package normal;

import easy.model.ListNode;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvw73v/"/>
 * 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 *  
 * <p>
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * <p>
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * <p>
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *  
 * <p>
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */
public class Question07 {

    public static void main(String[] args) {
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node2 = new ListNode(2);

        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);
        ListNode node44 = new ListNode(4);

        node2.next = node4;
        node4.next = node3;

        node5.next = node6;
        node6.next = node44;

        ListNode node = addTwoNumbers(node2, node5);
        System.out.println(node);

    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        boolean needAdd = false;
        ListNode resNode = new ListNode();
        ListNode curNode = resNode;
        while (l1 != null || l2 != null || needAdd) {
            int temp = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val);
            if (needAdd) {
                temp++;
                needAdd = false;
            }

            ListNode node;
            if (temp < 10) {
                node = new ListNode(temp);
            } else {
                needAdd = true;
                node = new ListNode(temp % 10);
            }
            curNode.next = node;
            curNode = node;

            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        return resNode.next;
    }


}
