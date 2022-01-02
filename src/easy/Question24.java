package easy;

import easy.model.ListNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @see <a href="https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xnnbp2/"/>
 * <p>
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 * <p>
 * 提示：
 * <p>
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
public class Question24 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node5 = new ListNode(5);
        ListNode node7 = new ListNode(7);

        ListNode node2 = new ListNode(2);
        ListNode node4 = new ListNode(4);
        ListNode node6 = new ListNode(6);
        ListNode node8 = new ListNode(8);

        node1.next = node3;
        node3.next = node5;
        node5.next = node7;

        node2.next = node4;
        node4.next = node6;
        node6.next = node8;

        ListNode node = mergeTwoList2(node1, node2);

        System.out.println(node);
    }

    public static ListNode mergeTwoList(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        List<ListNode> list = new ArrayList<>();
        while (list1 != null) {
            list.add(list1);
            list1 = list1.next;
        }
        while (list2 != null) {
            list.add(list2);
            list2 = list2.next;
        }
        Collections.sort(list, Comparator.comparingInt(o -> o.val));

        ListNode node = list.get(0);
        ListNode newHead = node;
        for (int i = 1; i < list.size(); i++) {
            node.next = list.get(i);
            node = node.next;
        }
        node.next = null;
        return newHead;
    }

    /**
     * 因为链表有序 只要每次比较链表头就行
     * 类似归并排序
     *
     * @return
     */
    public static ListNode mergeTwoList1(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode tempHead = new ListNode(0);
        ListNode cur = tempHead;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2;
        return tempHead.next;
    }


    /**
     * 递归实现
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoList2(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if(list1.val < list2.val){
            list1.next = mergeTwoList2(list1.next,list2);
            return list1;
        }else{
            list2.next = mergeTwoList2(list1,list2.next);
            return list2;
        }
    }
}
