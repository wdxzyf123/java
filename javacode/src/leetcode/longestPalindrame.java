package leetcode;

//最长回文子串//双指针
//主要地重点思想就是，使用两个参数，同时兼顾奇偶两种情况
public class longestPalindrame {
    public static void main(String[] args) {
        String text = "abbaabba";
        solution2 s = new solution2();
        String pr1 = s.palindrome(text,1,2);
        String pr2 = s.longestPalindrome(text);
        System.out.println(pr1);
        System.out.println(pr2);
//        int[] arr = new int[20];
////        Arrays.fill(arr,2);
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println(arr[i]);
//        }
//        System.out.println(arr);
    }
}
class solution2 {
    String palindrome(String s, int l, int r) {
        while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r))
        {
            l--;
            r++;
        }
        return s.substring(l+1, r);
    }
    String longestPalindrome(String s) {
        String res ="";
        for (int i = 0; i < s.length(); i++) {
            String s1 = palindrome(s, i, i);
            String s2 = palindrome(s,i,i+1);
            res = res.length() > s1.length() ? res : s1;
            res = res.length() > s2.length() ? res : s2;
         }
        return res;
    }

}
