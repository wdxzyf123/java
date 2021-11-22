package com.java;

import java.io.*;

/**
 * @author skwang
 * 缓冲字符流
 * @create 2021-03-11-16:53
 */
public class BufferedZiFu {
    public static void main(String[] args) {
        BufferedZiFu.testBufferedReader();
        BufferedZiFu.testBufferedWriter();
        String inPath = "C:\\Users\\skwang\\Desktop\\java\\tt.txt";
        String outPath = "C:\\Users\\skwang\\Desktop\\java\\javacode\\tt.txt";
        BufferedZiFu.copyFile(inPath, outPath);
    }

    /**
     * 缓冲字符输入流
     */
    public static void testBufferedReader() {
        try {
            FileReader fr = new FileReader("C:\\Users\\skwang\\Desktop\\java\\cc.txt");
            BufferedReader br = new BufferedReader(fr);
            char[] c = new char[100];
            int len = 0;
            while ((len = br.read(c)) != -1) {
                System.out.println(new String(c, 0, len));
            }
            br.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 缓冲字符输出流
     */
    public static void testBufferedWriter() {
        try {
            FileWriter fw = new FileWriter("C:\\Users\\skwang\\Desktop\\java\\cc.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            String s = "Hello World!";
            bw.write(s);
            bw.flush();
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 缓冲字符流复制文件
     */
    public static void copyFile(String inPath, String outPath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(inPath));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outPath));
            char [] c = new char[100];
            int len = 0;
            while ((len = br.read(c)) != -1) {
                bw.write(c, 0, len);
            }
            bw.flush();
            bw.close();//先创建后关闭
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
