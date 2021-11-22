package com.java;

import java.io.*;

/**
 * @author skwang
 * @create 2021-03-11-20:49
 */
public class SerializationTest {


    /**
     * 先实现可序列化接口
     */
    //alt+entry 快捷生成
    public static class Person implements Serializable{

        @Serial
        private static final long serialVersionUID = 1L;
        String name;
        int age;
    }
}
