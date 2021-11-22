package com.java;

import java.io.*;

/**
 * @author skwang
 * @create 2021-03-11-17:10
 *ISO8859-1村英文编码，不适合汉字
 * GBK和UTF-8适用于中文和英文，一般使用UTF-8
 */
public class ZhuanHuanLiu {
    public static void main(String[] args) {
        ZhuanHuanLiu.testInputStreamReader();
        ZhuanHuanLiu.testOutputStreamWriter();
    }

    /**
     * 转换字节输入流为字符输入流
     * 转换输入的时候编码格式要对应
     */
    public static void testInputStreamReader() {
        try {
            FileInputStream fi =new FileInputStream("C:\\Users\\skwang\\Desktop\\java\\tt.txt");
            //把字节流转换成字符流
            InputStreamReader in = new InputStreamReader(fi, "utf-8" );
            char[] c = new char[100];
            int len = 0;
            while ((len = in.read(c)) != -1) {
                System.out.println(new String(c, 0, len));
            }
            in.close();
            fi.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 转换字节输出流为字符输出流
     */
    public static void testOutputStreamWriter() {
        FileOutputStream fo = null;
        try {
            fo = new FileOutputStream("C:\\Users\\skwang\\Desktop\\java\\tt.txt");
            OutputStreamWriter ow = new OutputStreamWriter(fo, "utf-8");
            ow.write("我是中国人NIHAO!");
            ow.flush();
            ow.close();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
