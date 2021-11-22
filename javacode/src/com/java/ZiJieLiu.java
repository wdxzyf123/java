package com.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author skwang
 * @create 2021-03-09-16:59
 */
public class ZiJieLiu {
    public static void main(String[] args) {
        try {
            FileInputStream in = new FileInputStream("C:\\Users\\skwang\\Desktop\\java\\tt.txt");
            byte[] b = new byte[10];//接收的数组
//            in.read(b);//in.read(b)返回一个读取的数据的长度，如果是-1那就是读完了
            int len = 0;
            while((len = in.read(b))  != -1 ) {
                System.out.println(new String(b, 0, len));
            }
            System.out.println(new String(b));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String InPath = "C:\\Users\\skwang\\Desktop\\java\\img.jpg";
        String OutPath = "C:\\Users\\skwang\\Desktop\\java\\javacode\\img.jpg";
        ZiJieLiu.copy(OutPath, InPath);
        ZiJieLiu.testFileOutputStream();

    }


    public static  void testFileOutputStream() {
        try {
            FileOutputStream out = new FileOutputStream("C:\\Users\\skwang\\Desktop\\java\\tt.txt");
            String str = "kangshukang";
            out.write(str.getBytes(StandardCharsets.UTF_8));//数据写入内存
            out.flush();//内存数据写入硬盘
            out.close();//关闭流


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void copy(String OutPath, String InPath) {
        try {
            FileInputStream in = new FileInputStream(InPath);
            FileOutputStream out = new FileOutputStream(OutPath);
            byte [] b = new byte[100];
            int len = 0;
            while ((len = in.read(b)) != -1){
                out.write(b, 0, len);
            }
            out.flush();
            out.close();//先关out
            in.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
