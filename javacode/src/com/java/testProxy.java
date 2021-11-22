package com.java;

/**
 * @author skwang
 * @create 2021-03-13-14:56
 */
public class testProxy implements testProxyInterface{

    @Override
    public void test1() {
        System.out.println("执行test1");
    }

    @Override
    public void test2() {
        System.out.println("执行test2");
    }
}
