package jianzhioffer;

public class _1chu_xian_de_ci_shu {
    public static void main(String[] args) {
        int a = 534;
        Solution2 s = new Solution2();
        System.out.print(s.NumberOf1Between1AndN_Solution(a));
    }
}
class Solution2{
    public int NumberOf1Between1AndN_Solution(int n) {
        int round = n;
        int weight = 0;
        int former = 0;
        int count = 0;
        int base = 1;
        while(round != 0) {
            weight = round % 10;
            round = round / 10;
            former = n % base;
            if(weight > 1)
                count += round * base + base;
            else if(weight == 1)
                count += round * base + former + 1;
            else
                count += round * base;
        base *= 10;
        }
        return count;
    }
}