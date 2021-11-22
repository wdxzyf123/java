package com.java;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author skwang
 * @create 2021-03-13-14:46
 * 动态代理类
 */
public class ProxyDemo implements InvocationHandler {

    Object obj;//被代理的对象
    public ProxyDemo(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName() + "方法开始执行：");
        Object res = method.invoke(this.obj, args);//代理对象的指定方法
        System.out.println(method.getName() + "方法执行完毕！");
        return res;
    }
}
