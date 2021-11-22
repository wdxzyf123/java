package leetcode;

public class guibing {
    public static void main(String[] args) {
        int [] nums = {9,6,5,2,8,7,1,3};
        sort(nums, 0, nums.length-1);
        for (int i : nums
             ) {
            System.out.println(i);
        }

    }
    public static void sort(int[] nums, int low, int high) {
        if(low < high) {
            int mid = low + (high - low) / 2;
            sort(nums, low, mid);
            sort(nums, mid+1, high);
            merge(nums, low, mid, high);
        }
    }
    //归并算法，分治思想，先递归地分解，在合并有序地相邻序列。
    public static void merge(int[] nums, int low, int mid, int high) {
        int i = low, j = mid+1;
        //归并排序需要借助一个额外的数组来操作。
        int t = 0;
        int[] temp = new int[nums.length];
        while(i <= mid && j <= high) {
            if(nums[i] <= nums[j]) {
                temp[t++] = nums[i++];
            }
            else
                temp[t++] = nums[j++];
        }
        while(i <= mid)
            temp[t++] = nums[i++];
        while(j <= high)
            temp[t++] = nums[j++];
        t = 0;
        while(low <= high) {
            nums[low++] = temp[t++];
        }
    }

}
