package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

//字符串字母去重，不改变原来的相对位置，且保持字典序
public class chongfuzimu {
    public static void main(String[] args) {
        solutionzimu s = new solutionzimu();
        String str = "abbccdfehgh";
        System.out.println(s.quchong(str));
        System.out.println(s.zaici(str));
    }
}
class solutionzimu{
    String quchong(String s) {
        //需要一个栈来保证相对位置
        Stack<Character> stk = new Stack<>();
        //需要一个数组记录字符是否进栈
        boolean[] isinstack = new boolean[256];
        //需要一个数组来记录后面还有没有这个字符，保证字典序
        int [] count = new int[256];
        //记录一下每个字符的次数
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        for (char c : s.toCharArray()) {
            count[c]--;
            if(isinstack[c]) continue;
            while(!stk.isEmpty() && stk.peek() > c) {
                if(count[stk.peek()] == 0) break;
                //后面还有可以pop
                isinstack[stk.pop()] = false;
            }
            stk.push(c);
            isinstack[c] = true;
        }
        ArrayList<Character> list = new ArrayList<>();
        while (!stk.isEmpty()){
            list.add(stk.pop());
        }
        Collections.reverse(list);
        return list.toString();
    }

    String zaici(String s) {
        Stack<Character> stk = new Stack<>();
        int[] count = new int [256];
        boolean[] instack = new boolean[256];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
        }
        for(char c : s.toCharArray()) {
            count[c]--;
            if(instack[c]) continue;
            while(!stk.isEmpty() && stk.peek() > c) {
                if(count[stk.peek()]==0) break;
                instack[stk.pop()] = false;
            }
            stk.push(c);
            instack[c] = true;
        }
        ArrayList<Character> list = new ArrayList<>();
        while(!stk.isEmpty()){
            list.add(stk.pop());
        }
        Collections.reverse(list);
        return list.toString();
    }
}