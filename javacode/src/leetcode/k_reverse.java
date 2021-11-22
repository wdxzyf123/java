package leetcode;

public class k_reverse {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
//        head.next.next.next.next.next = new ListNode(6);
        solution1 s1 = new solution1();
        ListNode pr = s1.reverseK(head, 2);
        while(pr != null) {
            System.out.print(pr.val + " ");
            pr = pr.next;
        }

    }
}
//迭代方法来反转链表
class solution1{
    ListNode reverseK(ListNode head, int k) {
        //考虑base case 是什么，是最后不足K个节点的时候不反转。同时也需要一个终止条件。空时
        if(head == null) return null;
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            if(b == null) return head;
            b = b.next;//此时b指向第K+1个节点
            //这里的意思就是不足K个节点就终止，够K个就继续
        }
        //反转[a,b)之间
        ListNode newHead = reverseab(a, b);
        a.next = reverseK(b, k);
        return newHead;
    }
    ListNode reverseab(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null; cur = a; nxt = null;
        while(cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

}