package leetcode;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * 全排列的做法，回溯法
 */
public class quan_pai_lie {
    public static void main(String[] args) {
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("请输入一个不含重复数字的数组，数字之间用逗号隔开，程序将输出这个数组的全排列！输入：");
            String str = input.next();
            String [] arr = str.split(",");
            int[] nums = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                nums[i] = Integer.parseInt(arr[i]);
            }
            LinkedList<Integer> track = new LinkedList<>();
            backtrack(nums, track);
            System.out.println(res);
        }
//        int n=input.nextInt();



    }
    static LinkedList<LinkedList<Integer>> res = new LinkedList<>();
    public static void backtrack(int[] nums, LinkedList<Integer> track) {
        //结束条件 当track临时路径大小等于全部数目的时候就可以存入res列表了
        if(track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }
        //回溯的核心，for循环遍历是所有选择，根据选择列表确定可以选择的选项，这里用了点技巧，判断是不是用过了，用过了就continue不用了。
        for (int i = 0; i < nums.length; i++) {
            if(track.contains(nums[i]))
                continue;
            //在递归之前进行选择
            track.add(nums[i]);
            //递归
            backtrack(nums, track);
            //递归之后撤销选择
            track.removeLast();
        }
    }
}
