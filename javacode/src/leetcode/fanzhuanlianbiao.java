package leetcode;

public class fanzhuanlianbiao {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        solution s = new solution();
        ListNode last = s.reverseN(head,2);
        while (last != null) {
            System.out.println(
                    last.val + ","
            );
            last = last.next;
        }
        }

}
class solution {
    ListNode reverse(ListNode head) {
        if (head.next == null) return head;
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }
    ListNode successor = null;
    ListNode reverseN(ListNode head, int n) {
        if(n == 1) {
            successor = head.next;
            return head;
        }
        ListNode last = reverseN(head.next, n-1);
        head.next.next = head;
        head.next = successor;
        return last;
    }
    ListNode reverseBetween(ListNode head, int m, int n) {
        //base case
        if(m == 1)
        {
            return reverseN(head, n);
        }
        head.next = reverseBetween(head.next, m-1, n-1);
        return head;
    }
}
// 单链表节点的结构
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}
