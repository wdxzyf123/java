package com.java;

import java.io.*;

/**
 * @author skwang
 * @create 2021-03-11-19:10
 */
public class SystemOutIn {
    public static void main(String[] args) throws Exception {
//        SystemOutIn.testSystemIn();
        SystemOutIn.write2TXT();
        System.out.println();
    }


    /**
     * 标准输入流
     * @throws IOException
     */
    public static void testSystemIn() throws IOException {
        //创建一个接收键盘输入的输入流
        InputStreamReader ir = new InputStreamReader(System.in);

        //把输入流放到缓冲流中
        BufferedReader br = new BufferedReader(ir);
//        String str = "";
//        while ((str = br.readLine()) != null) {
//            System.out.println(str);
//        }
        char[] c = new char[100];
        int len = 0;
        while ((len = br.read(c)) != -1) {
            System.out.println(new String(c, 0, len));
        }
        br.close();
        ir.close();
    }
    /**
     * 标准输出流
     * 把控制台上输入的内容写到指定的文件中，收到字符串over结束程序运行
     */
    public static void write2TXT() throws Exception {
        //创建一个接收键盘输入的输入流
        InputStreamReader ir = new InputStreamReader(System.in);

        //把输入流放到缓冲流中
        BufferedReader br = new BufferedReader(ir);
        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\skwang\\Desktop\\java\\javacode\\pp.txt"));

        String line = "";
        while ((line = br.readLine()) != null) {
            //读取每一行都写到txt
            if(line.equals("over")) {
                break;
            }
            bw.write(line);
        }
        bw.flush();;
        bw.close();
        br.close();
    }
}
