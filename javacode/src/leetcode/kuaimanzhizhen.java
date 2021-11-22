package leetcode;

public class kuaimanzhizhen {
    public static void main(String[] args) {

    }
    //判断链表是否有环
    public boolean hasCycle(ListNode head) {
        /**
         * 定义两个指针一个快一个慢，快指针比慢指针走快一步，如果没环则必会遇到空指针，
         * 如果有环那就会在满指针走完第一圈快指针走完第二圈的时候相遇。
         */
        ListNode fast, slow;
        fast = slow = head;
        while(fast != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next.next;
            if (fast == slow)
                return true;
        }
        return false;
    }
    //已知这个链表有环，要求返回链表环的起点。
    public ListNode cycleFirst(ListNode head) {
        /**
         *当快慢指针第一次相遇的时候，快指针比满指针多走了一倍，设快慢指针分别走了2k和k;设环起点到第一次相遇点为m，
         * 起点到环起点为k-m，巧了，从相遇点到环起点也是k-m;
         * 所以让两个指针分别从起点和第一次相遇点同时以想同的步速走，再次相遇的点就是环起点。
         */
        ListNode fast, slow;
        fast = slow = head;
        while(fast != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast)
                break;
        }
        slow = head;
        while(!(slow == fast)) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return fast;
    }

    //二分查找
    int binarySearch(int[] nums, int target) {
        /**
         * 多看几次背下来
         */
        int left = 0, right = nums.length-1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target)
                return mid;
            else if(nums[mid] < target)
                left = mid+1;
            else if(nums[mid] > target)
                right = mid-1;
        }
        return -1;
    }
    //有序数组的两数之和等于一个target返回这两个数的索引，不存在则返回-1；
    int[] twoSum(int[] nums, int target) {
        /**
         * 和二分查找同理，大就往左挪一挪，小就往右边挪一挪。
         */
        int left = 0, right = nums.length-1;
        while(left < right) {
            int sum = nums[left] + nums[right];
            if(sum == target) {
                return new int[] {left+1, right+1};
            }else if(sum < target) {
                left++;
            }else if(sum > target) {
                right--;
            }
        }
        return new int[] {-1, -1};
    }
    //反转数组
    void reverse(int[] nums) {
        int left = 0, right = nums.length-1;
        while(left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            right--;
            left++;
        }
    }
    //寻找左侧边界的二分查找
    /**
     * 其实就是target在数组中存在多个，要求找出最左边或者最右边的target索引
     */
    //常见写法，搜索区间[left,right)，不会越界的写法
    public int leftbound1(int[] nums, int target) {
        int left = 0;
        int right = nums.length; //搜索区间为[left,right)
        //终止条件为left == right 既是不包括right
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                right = mid; // 向左压缩
            }else if(nums[mid] > target) {
                right = mid;//不包括mid 不要-1了
            }else if(nums[mid] < target) {
                left = mid+1;
            }
        }
        return left;
    }
    //第二种写法，搜索区间为[left, right],需要处理越界可能
    public int leftbound2(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;//搜索区间为[left,right]
        //终止条件为left == right + 1 说明搜索范围包括right
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                right = mid - 1; //收缩右侧区域，逼近左边界。
            }else if(nums[mid] > target) {
                right = mid - 1;
            }else if(nums[mid] < target) {
                left = mid + 1;
            }
        }
        //可能存在越界。left可能越过上界
        if(left >= nums.length || nums[left] != target)
            return -1;
        return left;
    }
    //寻找右侧边界的二分查找
    //第一种写法，搜索区间为[left, right)
    int rightbound1(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        //终止条件left == right
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                //向右压缩，逼近右边界
                left = mid+1;
            }else if(nums[mid] > target) {
                right = mid;//因为是开区间，不用-1
            }else if(nums[mid] < target) {
                left = mid +1;
            }
        }
        return right-1; //为甚要-1，因为刚才在相等的是时候已经给他+1了
    }
    //第二种写法，闭区间写法[left, right]
    int rightbound2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        //终止条件left = right + 1 需要处理越界可能
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                left = mid + 1;
            }else if(nums[mid] > target) {
                right = mid - 1;
            }else if(nums[mid] < target) {
                left = mid + 1;
            }
        }
        //可能越下界
        if(right < 0 || nums[right] != target) {
            return -1;
        }
        return right-1;
    }
}
