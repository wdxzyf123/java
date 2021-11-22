package leetcode;

import java.util.Arrays;

public class cou_coins {
    public static void main(String[] args) {
        int[] coins = {1,2,5};
//        System.out.println(coinchange(coins, 2555));
        System.out.println(dp(coins, 100000));

    }
    public static int coinchange(int[] coins, int n) {
        int[] memo = new int [n+1];
        Arrays.fill(memo,-2);
        return help(coins, memo, n);
    }
    public static int help(int[] coins,int[] memo, int n) {
        if(n < 0) return -1;
        if(n == 0) return 0;
        if(memo[n] != -2) return memo[n];
        int res = 1000000;
        for (int coin : coins) {
            int sub = help(coins, memo, n - coin);
            if (sub == -1) continue;
            res = Math.min(res, 1 + sub);
            memo[n] = res;
        }
        return memo[n];
    }

    //在这个问题里dp[i]的定义是，目标金额为i最少需要多少个硬币来拼凑。自底向上地遍历
    static int dp(int[] coins, int n) {
        int[] dp = new int [n+1];
        Arrays.fill(dp, n+100);
        //base case
        dp[0] = 0;
        for (int i = 0; i < n+1; i++) {
            for (int coin: coins
                 ) {
                if(i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
            }
        }
        return dp[n];
    }


}
