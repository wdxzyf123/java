package jianzhioffer;

public class chou_shu {
    public static void main(String[] args) {
        Solution3 s = new Solution3();
        int pr = s.GetUglyNumber_Solution(11);
        System.out.println(pr);
    }
}

class Solution3 {
    public int GetUglyNumber_Solution(int index) {
        if(index == 1)
            return 1;
        int num = 2;
        while(index > 1){
            if((num % 2) == 0){
                num++;
                index--;
                continue;
            }else if((num % 3) == 0) {
                num++;
                index--;
                continue;
            }else if((num % 5) == 0) {
                num++;
                index--;
                continue;
            }else
                num++;
            continue;
        }
        return num-1;
    }
}