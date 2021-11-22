package com.java;

/**
 * @author skwang
 * @create 2021-03-05-14:54
 */
public class kongge {

    public static void main(String[] args) {
        String str = "sdsada192";
        int len = StrToInt(str);


        System.out.println(len);


    }
    public static int StrToInt(String str) {
        char[] arr = str.toCharArray();
        if(isNumeric(arr) == true)
        {
            int len = arr.length;
            double res = 0;
            if(arr[0] == '+') {
                for (int i = 1; i < len; i++ ) {
                    int n = len -1-i;
            res = res + ((int)arr[i] - (int)('0')) * Math.pow(10,n);
                }
            }else if (arr[0] == '-') {
                for (int i = 1; i < len; i++ ) {
                    int n = len -1-i;
                    res = res + ((int)arr[i] - (int)('0')) * Math.pow(10,n);}
                return 0-(int)res;
            }
            else
                for(int i = 0; i < len; i++) {
                    int n = len-i-1;
                    res = res + ((int)arr[i] - (int)('0')) * Math.pow(10,n);
                }
            return (int)res;
        }
        else
            return 0;
    }
    public static boolean isNumeric(char[] str) {
        String string = String.valueOf(str);
        return string.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
         /*
         为什么是两个反斜杠
         首先字符串中的\\被编译器解释为\ -------》  第一步，编译器将字符串转变为“正则表达式”
		然后作为正则表达式，\.又被正则表达式引擎解释为.   ----------------> 第二步，才开始把第一步的结果当做是正则表达式，开始进行匹配！
		如果在字符串里只写\.的话，第一步就被直接解释为.，之后作为正则表达式被解释时就变成匹配任意字符了
         */
    }
}