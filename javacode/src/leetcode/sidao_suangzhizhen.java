package leetcode;

public class sidao_suangzhizhen {
    public static void main(String[] args) {
        //1
        int[] arr = new int[]{0,0,2,2,3,3,3,4,5,5,5,6,7,8,8,9};
        solutionxx s = new solutionxx();
//        System.out.println(s.removeRepet(arr));
//        for(int i : arr)
//            System.out.println(i);
        //2
//        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(3);
//        head.next.next.next.next = new ListNode(4);
//        ListNode prhead = s.removeRepetlink(head);
//        while(prhead != null) {
//            System.out.println(prhead.val);
//            prhead = prhead.next;
//        }
        //3

    }
}
class solutionxx{
    //有序数组原地去重
    int removeRepet(int[] nums) {
        if(nums.length == 0)
            return 0;
        int slow, fast;
        slow = fast = 0;
        while(fast < nums.length) {
            if(nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow+1;
    }
    //有序链表去重
    ListNode removeRepetlink(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while (fast != null) {
            if(fast.val != slow.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }
        return head;
    }
    //原地删除指定元素
    int removeElement(int[] nums, int target) {
        int fast = 0, slow = 0;
        while(fast < nums.length) {
            if(nums[fast] != target) {
                nums[slow] = nums[fast];
                slow++;
            }
            else
                fast++;
        }
        return slow;
    }
    //移动零
    void removezero(int[] nums) {
        int n = nums.length;
        int fast = 0, slow = 0;
        while(fast < nums.length) {
            if(nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            else
                fast++;
        }
        for (int i = slow; i < n; i++) {
            nums[i] = 0;
        }
    }
}