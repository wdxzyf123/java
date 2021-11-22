package com.java;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author skwang
 * @create 2021-03-11-16:24
 * BufferedInputStream  BufferedOutStream  字节流
 * BufferedReader BufferedWriter  字符流
 */
public class BufferedZiJie {
    public static void main(String[] args) {
        BufferedZiJie.testBufferedStream();
        BufferedZiJie.testBufferedOutputStream();
        String inPath = "C:\\Users\\skwang\\Desktop\\java\\img.jpg";
        String outPath = "C:\\Users\\skwang\\Desktop\\java\\javacode\\img.jpg";
        try {
            BufferedZiJie.copy(inPath, outPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void testBufferedStream() {
        //先建一个字节输入流对象
        try {
            FileInputStream in = new FileInputStream("C:\\Users\\skwang\\Desktop\\java\\bb.txt ");
            BufferedInputStream br = new BufferedInputStream(in);
            byte[] b = new byte[11];
            int len = 0;
            while((len = br.read(b)) != -1) {
                System.out.println(new String(b, 0, len));
            }
            br.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testBufferedOutputStream() {
        try {
            FileOutputStream out = new FileOutputStream("C:\\Users\\skwang\\Desktop\\java\\cc.txt");
            BufferedOutputStream bo =new BufferedOutputStream(out);
             String s = "ssdsdasdasdsdasd";
             bo.write(s.getBytes(StandardCharsets.UTF_8));
             bo.flush();
             bo.close();
             out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy(String inPath, String outPath) throws Exception {
        BufferedInputStream bi = new BufferedInputStream(new FileInputStream(inPath));
        BufferedOutputStream bo = new BufferedOutputStream(new FileOutputStream(outPath));
        byte[] b = new byte[100];
        int len = 0;
        while ((len = bi.read(b)) != -1) {
            bo.write(b, 0, len);
        }
        bo.flush();
        bo.close();
        bi.close();

    }
}
