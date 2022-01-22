package normal;

import easy.model.ListNode;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-medium/xvdwtj/"/>
 * <p>
 * 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 * <p>
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 * <p>
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 * <p>
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 * <p>
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 */
public class Question08 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode node = oddEvenList(node1);
        System.out.println(node);

    }

    public static ListNode oddEvenList(ListNode head) {
        ListNode front = new ListNode();
        ListNode back = new ListNode();

        ListNode curFront = front;
        ListNode curBack = back;

        while (head != null && head.next != null) {
            curFront.next = head;
            curBack.next = head.next;
            curFront = curFront.next;
            curBack = curBack.next;

            head = head.next.next;
            curFront.next = null;
            curBack.next = null;
        }
        if (head != null) {
            curFront.next = head;
            curFront = curFront.next;
        }

        curFront.next = back.next;


        return front.next;
    }


    public static ListNode oddEvenList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode oddNode = head;  //奇数节点
        ListNode oddCur = oddNode;

        ListNode evenNode = head.next; //偶数节点
        ListNode evenCur = evenNode;

        while (evenCur != null && evenCur.next != null) {
            oddCur.next = oddCur.next.next;

            evenCur.next = evenCur.next.next;

            oddCur = oddCur.next;
            evenCur = evenCur.next;
        }
        oddCur.next = evenNode;
        return oddNode;
    }
}
