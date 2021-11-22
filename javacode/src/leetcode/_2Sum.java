package leetcode;

import java.util.HashMap;

//无序2sum
public class _2Sum {
    public static void main(String[] args) {
        int[] arr = {3,56,1,4,8,9,5,2};
        solutionx s = new solutionx();
        int[] prarr = s.twosumhash(arr, 10);
        for(int i : prarr)
            System.out.println(i);
    }
}
class solutionx{
    //暴力穷举
    int[] twosum(int[] nums, int target) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(nums[j] == target  - nums[i]) {
                    return new int[]{j,i};
                }
            }
        }
        return new int[]{-1,-1};
    }
    //借助hashmap
    int[] twosumhash(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer,Integer> hashmap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            hashmap.put(nums[i], i);
        }
        for (int i = 0; i < n; i++) {
            int other = target - nums[i];
            if(hashmap.containsKey(other) && hashmap.get(other) != i)
                return new int[]{i,hashmap.get(other)};
        }
        return new int[]{-1,-1} ;}
    //排序后双指针
}