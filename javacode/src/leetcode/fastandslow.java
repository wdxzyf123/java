package leetcode;

import java.util.Arrays;

public class fastandslow {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        ListNode cy = head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = cy;
        solution111 s = new solution111();
        System.out.println(s.hascycle(head));
        System.out.println(s.qidian(head).val);
        ListNode head1 = new ListNode(1);
        head1.next = new ListNode(2);
        head1.next.next = new ListNode(3);
        head1.next.next.next = new ListNode(4);
        head1.next.next.next.next = new ListNode(5);
        ListNode prhead = s.removeNthFromEnd(head1, 2);
        while(prhead != null) {
            System.out.println(prhead.val);
            prhead = prhead.next;
        }
        int[] arr = {1,2,3,9,8,7,5,4,6};
        Arrays.sort(arr);
        System.out.println(s.binarySearch(arr,1));
        int [] prsum = s.twoSum(arr, 10);
        for (int i : prsum)
            System.out.println(i);



    }
}
class solution111 {
    //判断是否有环
    boolean hascycle(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
    //返回环的起点
    ListNode qidian(ListNode head) {
        ListNode slow, fast;
        slow = fast = head;
        while(fast !=null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if(slow == fast) {
                break;
            }
        }
        slow = head;
        while(fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    //删除链表的倒数第N个节点
    ListNode removeNthFromEnd(ListNode head, int n ) {
        ListNode fast, slow;
        fast = slow = head;
        while(n-- > 0){
            fast = fast.next;
        }
        if(fast == null)
            return  head;
        while( fast != null && fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
    //二分查找
    int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) {
            int mid =left + (right - left) / 2;
            if(nums[mid] == target)
                return nums[mid];
            else if(nums[mid] < target) {
                left = mid + 1;
            }
            else
                right = mid -1;
        }
        return -1;
    }
    //两数相加
    int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            int sum = nums[left] + nums[right];
            if(sum == target) {
                return new int[]{left+1, right+1};
            }
            else if(sum < target) {
                left ++;
            }
            else
                right--;

        }
        return new int[]{-1,-1};
    }
    //反转数组
    void reverseString(char[] arr) {
        int left = 0;
        int right = arr.length  - 1;
        while(left < right) {
            char temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
            right--;
            left++;
        }
    }

}