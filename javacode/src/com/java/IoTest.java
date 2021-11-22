package com.java;

import java.io.File;
import java.io.IOException;

/**
 * @author skwang
 * @create 2021-03-09-14:39
 */
public class IoTest {
    public static void main(String[] args) {
        File f = new File("C:\\Users\\skwang\\Desktop\\java\\javacode\\filetest\\abs\\ww.txt");
        //这是对象f就是tt.txt
        File f1 = new File("C:/Users/skwang\\Desktop\\“，”java\\javacode\\filetest\\abs\\ww.txt");
        File f2 = new File("C:“+ File.separator +”Users/skwang\\Desktop\\“，”java\\javacode\\filetest\\abs\\ww.txt");
        System.out.println(f.getName());
        File f3 = new File("src\\com\\java\\kongge.java");
        System.out.println(f3.getPath());//相对路径
        System.out.println(f3.getAbsoluteFile());
        System.out.println(f3);

        System.out.println(f3.getParent());//父级路径

//        改名
        f.renameTo(new File("C:\\Users\\skwang\\Desktop\\java\\javacode\\filetest\\abs\\tt.txt"));
        System.out.println(f.exists());//重点 rename之后原来的对象失效
        System.out.println(f3.exists());

        System.out.println(f3.canWrite());
        System.out.println(f3.canRead());
        System.out.println(f3.isFile());
        System.out.println(f3.isDirectory());

        System.out.println(f3.lastModified());//毫秒
        System.out.println(f3.length());//字节数

        File f4 = new File("C:\\Users\\skwang\\Desktop\\java\\javacode\\filetest\\abs\\ww.txt");
        System.out.println(f4.exists());
        if(!f4.exists()) {
            try {
                f4.createNewFile();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        f4.delete();
        System.out.println(f4.exists());
        /**
         * 目录
         */
        File f5 = new File("C:\\Users\\skwang\\Desktop\\java\\javacode\\filetest\\abs\\cc\\dd\\ee\\ff\\hh");
        System.out.println(f5.exists());
        f5.mkdirs();//mkdir（）创建单层目录  mkdirs（）创建多层目录
        File f6 = new File("C:\\Users\\skwang\\Desktop\\java");
        String[] str = f6.list();//返回String文件名和曾
        for(String s : str) {
            System.out.println(s);
        }
        File[] fl = f6.listFiles();//返回对象
        for(File i : fl) {
            System.out.println(i);
        }


        /**
         * 递归遍历某个文件夹下的所有目录和文件
         */
        File f7 = new File("C:\\Users\\skwang\\Desktop\\java");
        new IoTest().test(f7);

    }
    public void test(File f) {
        if(f.isFile()) {
            System.out.println(f.getAbsolutePath() + "是一个文件");
        }else {
            System.out.println(f.getAbsolutePath() + "是一个文件夹");
            File[] fl = f.listFiles();
            if(fl != null && fl.length >0) {
                for(File fs : fl) {
                    test(fs);
                }
            }
        }
    }
}
