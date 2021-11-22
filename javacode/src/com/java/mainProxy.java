package com.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author skwang
 * @create 2021-03-13-14:59
 */
public class mainProxy {
    public static void main(String[] args) {
        testProxyInterface test = new testProxy();
        test.test1();
        test.test2();
        System.out.println("-----------------------------------");
        InvocationHandler handler = new ProxyDemo(test);
        /**
         * Proxy.newProxyInstance(Classloder, interfaces,h)
         * 参数1是代理对象的类加载器
         * 参数2是代理对象的接口
         * 参数3是代理对象
         * 返回值是成功被处理后的对象
         */
       testProxyInterface t = (testProxyInterface) Proxy.newProxyInstance(handler.getClass().getClassLoader(),  test.getClass().getInterfaces(),handler);
        t.test1();
        System.out.println("---------------------------");
        t.test2();
    }
}
