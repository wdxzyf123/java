package leetcode;

public class digui_fanzhuan_lianbiao {

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode temp = head;
        for (int i = 1; i < 5; i++) {
            head.next=new ListNode(i);
            head = head.next;
        }
        temp = reverse(temp);
        while(temp != null) {
            System.out.println(temp.val);
            temp = temp.next;
        }
    }
    public static ListNode reverse(ListNode head) {
        //递归写法
        //base case
        if(head.next == null) return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
    //递归反转前n个节点
    static ListNode succes = null;
    public static ListNode reverse(ListNode head, int n) {
        //先找到并记录第n+1个节点
        if(n == 1) {
            succes = head.next;
            return head;
        }
        ListNode last = reverse(head.next, n-1);
        head.next.next = head;
        head.next = succes;
        return last;
    }
    //反转一个索引区间内的节点
    public static ListNode reverse(ListNode head, int m, int n) {
        if(m == 1) {
            return reverse(head, n);
        }
        else {
            head.next = reverse(head.next, m-1, n-1);
            return head;
        }
    }
}
