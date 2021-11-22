package jianzhioffer;

import java.util.*;

public class strpailie {
    public static void main(String[] args) {
        Solution s = new Solution();
        ArrayList<String> pr = new ArrayList<String>();
        pr = s.Permutation("abc");
        for (int i = 0; i < pr.size(); i++) {
            System.out.println(pr.get(i));
        }
    }

}
class Solution {
    ArrayList<String> res = new ArrayList<String>();
    public ArrayList<String> Permutation(String str) {
        if(str == null || str.length() == 0) {
            return res;
        }
        Permutation(str.toCharArray(), 0);
        Collections.sort(res);
        return res;
    }
    public void Permutation(char[] strArr, int begin) {
        if(begin == strArr.length - 1) {
            String s = String.valueOf(strArr);
            if(! res.contains(s)) {
                res.add(s);
            }
        }else {
            for(int i = begin; i < strArr.length; i++) {
                swap(strArr, begin,i);
                Permutation(strArr,begin+1);
                swap(strArr,begin,i);//恢复回来，给下一个位置
            }
        }
    }
    public void swap(char[] strArr,int a, int b) {
        char temp = strArr[a];
        strArr[a] = strArr[b];
        strArr[b] = temp;
    }
}