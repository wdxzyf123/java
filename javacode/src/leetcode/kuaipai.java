package leetcode;

public class kuaipai {
    public static void main(String[] args) {
        int[] nums = new int[]{2,1,6,3,4,5};
        kuai(nums,0,5);
        for (int i : nums) {
            System.out.println(i);
        }
    }
    /**
     * 快排的思想：先在数组中挑选一个基准数，一般是数组第一个，做一个partition操作，
     * 将比它小的数放在它左边，比它大的数放在它右边这样就确定了一个数的位置。
     * 之后通过前序遍历递归地对他左右两边的数进行partition
     */
    public static void kuai(int[] nums, int low, int high) {
        if(low < high) {
            int p = partition(nums, low, high);
            kuai(nums, low, p-1);
            kuai(nums, p+1, high);
        }
    }
    public static int partition(int[] nums, int low, int high) {
//        int i = low, j =high;
        int temp = nums[low];
        //从右边j开始
        while(low<high) {
            while(low<high && nums[high] >= temp)
                high--;
            if(low<high)
                nums[low++] = nums[high];
            while(low<high && nums[low] < temp)
                low++;
            if(low<high)
                nums[high--] = nums[low];
        }
        nums[low] = temp;
        return low;
    }




}
