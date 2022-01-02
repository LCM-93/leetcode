package easy;

import easy.model.ListNode;

import java.util.Stack;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnv1oc/"/>
 *
 * 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *  
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 * 示例 2：
 *
 *
 * 输入：head = [1,2]
 * 输出：false
 *
 * 提示：
 *
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 *  
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 */
public class Question25 {

    public static void main(String[] args){
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;

        System.out.println(node1);
        System.out.println(isPalindrome1(node1));

    }


    /**
     * 利用栈辅助
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head){
        ListNode temp = head;
        Stack<Integer> stack = new Stack<>();
        while (temp != null){
            stack.push(temp.val);
            temp = temp.next;
        }

        while (head !=null){
            if(stack.pop() != head.val){
                return false;
            }
            head = head.next;
        }
        return true;

    }

    /**
     * 反转链表后半部分再进行比较
     * @param head
     * @return
     */
    public static boolean isPalindrome1(ListNode head){
        ListNode fast = head;ListNode slow = head;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //如果fast 不为空  说明链表是奇数个
        if(fast != null){
            slow = slow.next;
        }
        slow = reverse(slow);  //反转后半部分链表

        fast = head;
        while (slow != null){
            if(slow.val != fast.val){
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public static ListNode reverse(ListNode head){
        ListNode node = null;
        while (head != null){
            ListNode temp = head.next;
            head.next = node;
            node = head;
            head = temp;
        }
        return node;
    }
}
