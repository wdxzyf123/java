package com.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author skwang
 * @create 2021-03-09-19:02
 */
public class CopyFile {
    public static void main(String[] args) {
        String InPath = "C:\\Users\\skwang\\Desktop\\java\\img.jpg";
        String OutPath = "C:\\Users\\skwang\\Desktop\\java\\javacode\\img.jpg";
        CopyFile.copy(OutPath, InPath);
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
