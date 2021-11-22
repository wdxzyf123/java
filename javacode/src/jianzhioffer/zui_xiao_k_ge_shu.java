package jianzhioffer;

import java.util.ArrayList;

public class zui_xiao_k_ge_shu {
    public static void main(String[] args) {
     Solution1 s = new Solution1();
     ArrayList<Integer> pr = new ArrayList<>();
     int [] input = {9,8,2,4,1,7,3,6};
     pr = s.GetK(input, 3);
        for (int i = 0; i < pr.size(); i++) {
            System.out.println(pr.get(i));
        }
    }
}
class Solution1{
    ArrayList<Integer> res = new ArrayList<>();
    public ArrayList<Integer> GetK(int[] input, int k) {
        if(input == null || input.length <= 0 || k > input.length) {
            return res;
        }
        int len = input.length;
        int low = 0, high = len-1;
        int index = -1;
        while(index != k && low < high) {
            index = partition(input, low, high);
            if(index > k)
                high = index-1;
            else
                low = index+1;
        }
        for (int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }

    public int partition(int[] array, int low, int high) {
        int i = low;
        int j = high;
        int temp = array[i];
        while(i < j) {
            while(i < j && array[j] >= temp){
                j--;
            }
            if(i < j) {
                array[i] = array[j];
                i++;
            }
            while(i<j && array[i] < temp) {
                i++;
            }
            if(i < j) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = temp;
        return i;
    }
}