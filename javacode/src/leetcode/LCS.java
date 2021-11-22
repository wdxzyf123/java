package leetcode;

public class LCS {
    public static void main(String[] args) {
        String str1 = "abcdefghijk";
        String str2 = "abcdelknfgh";
        System.out.println(longestCommenSubsequence(str1, str2));
    }
    public static int longestCommenSubsequence(String str1, String str2) {
        /**
         * 求解两个字符串的最长公共子序列的长度
         * 子序列 最值 自然想到动态规划
         * 定义dp数组dp[i][j]表示字符串S1[0...i-1]和S2[0...j-1]的最长公共子序列长度
         * base case dp[0][...]和dp[...][0]都应该为0;
         */
        int n = str1.length();
        int m = str2.length();
        int dp[][] = new int[n+1][m+1];//默认是0
        //其实是固定套路记下来为好，两个for循环遍历dp数组然后if else
        for(int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }
}
