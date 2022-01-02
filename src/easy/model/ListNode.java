package easy.model;


public class ListNode {

    public int val;
    public ListNode next;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        ListNode node = this;
        while (node != null){
            builder.append("ListNode( val = "+node.val+" )").append(" -> ");
            node = node.next;
        }
        builder.delete(builder.lastIndexOf("-> "),builder.length());
        return builder.toString();
    }

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
