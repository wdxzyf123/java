package leetcode;

import java.util.Arrays;

public class dp {
    public static void main(String[] args) {
        solution11 s = new solution11();
//        System.out.println(s.fibbest(3));
        int[] coins = {1,2,5};
        System.out.println(s.coinsdp(coins,11));
    }
}
class solution11{
    int fib(int N) {
        if(N == 1 || N == 2) return 1;
        return fib(N-1)+ fib(N-2);
    }
//    Arrays.fill(array, -1);
    //带备忘录的
    int fibplus(int n) {
        int [] arr = new int[n+1];
        arr[1] = arr[2] = 1;
        return help(arr,n);
    }
    int help(int [] arr, int n) {
        if(arr[n] == 0)
            arr[n] = help(arr, n-1) + help(arr, n-2);
        return arr[n];
    }
    /**
     * 至此，带备忘录的递归解法的效率已经和动态规划一样了。实际上，这种解法和动态规划的思想已经差不多了，
     * 只不过这种方法叫做「自顶向下」，动态规划叫做「自底向上」。
     * 啥叫「自顶向下」？注意我们刚才画的递归树（或者说图），是从上向下延伸，
     * 都是从一个规模较大的原问题比如说 f(20)，向下逐渐分解规模，直到 f(1) 和 f(2) 触底，
     * 然后逐层返回答案，这就叫「自顶向下」。
     * 啥叫「自底向上」？反过来，我们直接从最底下，最简单，问题规模最小的 f(1) 和 f(2) 开始往上推，
     * 直到推到我们想要的答案 f(20)，这就是动态规划的思路，这也是为什么动态规划一般都脱离了递归，
     * 而是由循环迭代完成计算
     */
    //动态规划
    int fibdp(int n) {
        int[] dp = new int[n+1];
        dp[1] = dp[2] = 1;
        for (int i = 3; i < n+1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
    //空间复杂度为O(1)的解法
    int fibbest(int n) {
        int pre = 1;
        int cur = 1;
        for (int i = 3; i < n+1; i++) {
            int sum = pre + cur;
            pre = cur;
            cur = sum;
        }
        return  cur;
    }
    //凑零钱问题
    int coinchange(int[] coins, int amount) {
        if(amount == 0) return 0;
        int ans = 1000;
        for(int i : coins) {
            if (amount - i < 0) {
                continue;
            }
            int subprob = coinchange(coins, amount - i);
            if (subprob == -1)
                continue;
            ans = Math.min(ans, subprob+1);
        }
        return ans == 1000 ? -1 : ans;
    }
    //凑零钱带备忘录
    int coinchangdaibeiwanglu(int[] coins, int amount) {
        int [] arr = new int[amount +1];
        Arrays.fill(arr, -2);
        return  help(coins, amount, arr);
    }
    int help(int[] count, int amount, int[] arr){
        if (amount == 0) return 0;
        if(arr[amount] != -2) return arr[amount];
        int ans = 1000;
        for(int i : count) {
            if((amount - i) < 0)  continue;
            int subprob = help(count, amount-i, arr);
            if (subprob == -1) continue;
            ans = Math.min(ans, subprob+1);
        }
        arr[amount] = (ans == 1000) ? -1 : ans;
        return arr[amount];
    }
    //凑零钱的动态规划写法
    int coinsdp(int [] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if(i - coin < 0) continue;
                dp[i] = Math.min(dp[i], 1 + dp[i-coin]);
            }
        }
        return  dp[amount] == amount+1 ? -1 : dp[amount];
    }

    int coins(int[] coins, int num) {
        int[] arr = new int[num+1];
        Arrays.fill(arr, -2);
        return hlep(coins, num, arr);
    }
    int hlep(int[] coins, int num, int[] arr) {
        if(num == 0) return 0;
        if(arr[num] != -2) return arr[num];
        int ans = 1000;
        for(int coin : coins) {
            if(num - coin < 0) continue;
            int subprob = help(coins, num - coin, arr);
            if(subprob == -1) continue;
            ans = Math.min(ans, subprob+1);
        }
        arr[num] = (ans == 1000) ? -1 : ans;
        return arr[num];
    }
    int coinsdp2(int[] coins, int num) {
        int dp[]= new int[num+1];
        dp[0] = 0;
        for (int i = 1; i < num+1; i++) {
            for (int coin : coins) {
                if(i - coin < 0)continue;
                dp[i] = Math.min(dp[i], 1+ dp[i-coin]);
            }
        }
        return dp[num] == num+1 ? -1: dp[num];
    }
}

