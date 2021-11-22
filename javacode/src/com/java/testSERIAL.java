package com.java;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
 * @author skwang
 * @create 2021-03-11-21:13
 */
public class testSERIAL {
    public static void main(String[] args) throws Exception {
testSERIAL.testSerialize();
    }

    public static void testSerialize() throws Exception {
        //指定文件放序列化后的流
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("C:\\Users\\skwang\\Desktop\\java\\javacode\\xuliehua.txt"));
        SerializationTest.Person p = new SerializationTest.Person();

        p.name = "shukang";
        p.age = 25;
        out.writeObject(p);
        out.flush();
        out.close();
    }

}
