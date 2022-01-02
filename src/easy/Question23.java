package easy;

import easy.model.ListNode;

import java.util.Stack;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnnhm6/"/>
 *
 * 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *  
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：[2,1]
 * 示例 3：
 *
 * 输入：head = []
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *  
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 *
 */
public class Question23 {

    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node9 = new ListNode(9);
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4);
        node1.next = node9;
        node9.next = node5;
        node5.next = node4;

        System.out.println(node1);
        ListNode node = reverseList2(node1);
        System.out.println(node);

    }

    /**
     * 双链表翻转  每次分解出的node变为新链表的头
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head){
      ListNode newNode = null;
        while (head != null){
            ListNode temp = head;
            head = head.next;
            if(newNode == null) {
                temp.next = null;
                newNode = temp;
            }else{
                temp.next = newNode;
                newNode = temp;
            }
        }
        return newNode;
    }


    /**
     * 用栈解决  先入后出
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head){
        Stack<ListNode> stack = new Stack<>();

        while (head != null){
            ListNode temp = head;
            head = head.next;
            temp.next = null;
            stack.push(temp);
        }
        if(stack.isEmpty()) return null;

        ListNode node = stack.pop(); //node总是指向最后一个节点
        ListNode newHead = node;
        while (!stack.empty()){
            ListNode temp = stack.pop();
            node.next = temp;
            node = node.next;
        }
        return newHead;
    }

    /**
     * 递归调用
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        //保存链表的下一个节点
        ListNode next = head.next;

        //next反转后 肯定是链表的尾结点 所以只需要把当前节点放在next节点的后面就行
        ListNode reverse = reverseList2(next);

        next.next = head;

        head.next = null;

        return reverse;
    }


    /**
     * 尾递归调用
     * @param head
     * @return
     */
    public static ListNode reverseList3(ListNode head){
        return reverseList31(head,null);
    }

    public static ListNode reverseList31(ListNode head, ListNode newHead){
        if(head == null) return newHead;
        ListNode next = head.next;
        head.next = newHead;
        return reverseList31(next,head);
    }
}
