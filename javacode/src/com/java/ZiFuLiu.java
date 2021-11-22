package com.java;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author skwang
 * @create 2021-03-10-21:15
 * 写入时有同名文件会被覆盖，读取时文件要存在，不然会报错。
 * 文件字符流，缓冲数组是字符
 */
public class ZiFuLiu {
    public static void main(String[] args) {
        ZiFuLiu.testFileRead("C:\\Users\\skwang\\Desktop\\java\\tt.txt");
        ZiFuLiu.testFileWriter("WoShiNiBaBa", "C:\\Users\\skwang\\Desktop\\java\\bb.txt");
        ZiFuLiu.CopyFile("C:\\Users\\skwang\\Desktop\\java\\bb.txt", "C:\\Users\\skwang\\Desktop\\java\\cc.txt");
    }

    public static void testFileRead(String InPath) {
        try {
            FileReader fr = new FileReader(InPath);
            char[] c = new char[100];
            int len = 0;
            while ((len = fr.read(c)) != -1) {
                System.out.println(new String(c, 0, len));
            }
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件字符输出流FileWriter
     * @param OutPath
     */
    public static void testFileWriter(String text, String OutPath) {
        try {
            FileWriter fw = new FileWriter(OutPath);
            fw.write(text);
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 字符流只能拷贝字符文件 文档
     */
    public static void CopyFile(String inPath, String outPath) {
        try {
            FileReader fr = new FileReader(inPath);
            FileWriter fw = new FileWriter(outPath);
            char[] c = new char[100];
            int len = 0;
            while ((len = fr.read(c)) != -1) {
                fw.write(c, 0, len);
            }

            fw.flush();
            fw.close();
            fr.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
