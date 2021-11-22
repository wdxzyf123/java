package leetcode;

public class fei_bo_na_qi {
    public static void main(String[] args) {
        int n = 45;
//        int[] arr = new int[n+1];
//        Arrays.fill(arr, 0);
        System.out.println(fib_dp(n));
    }
    public static int fib(int n) {
        if(n==0)return 0;
        if(n==1 || n==2)
            return 1;
        return fib(n-2)+fib(n-1);
    }
    //自顶向下
    //动态规划是自底向上
    public static int fib2(int n, int[] arr) {
        arr[1] = arr[2] = 1;
        if(arr[n] != 0) return arr[n];
        arr[n] = fib2(n-1,arr) + fib2(n-2, arr);
        return arr[n];
    }
    public static int fib_dp(int n) {
        if(n == 1 || n == 2) return 1;
        int[] arr = new int[n+1];
        arr[1] = arr[2] = 1;
        for (int i = 3; i < n+1; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }
}

