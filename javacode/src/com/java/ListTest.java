package com.java;

import java.util.ArrayList;
import java.util.List;

/**
 * @author skwang
 * @create 2021-03-08-13:07
 *有序可重复 有索引
 */
public class ListTest{
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("abc1");
        list.add("abc2");
        list.add("abc3");
        list.add("abc4");
        list.add("abc2");

        System.out.println(list);
        System.out.println(list.get(2));
        list.add(1, "ABC");
        System.out.println(list);
        ArrayList<String> l = new ArrayList<>();
        l.add("123");
        l.add("456");

        list.addAll(2, l);
        list.remove(0);
        System.out.println(list);

        System.out.println(list.indexOf("abc2"));
        System.out.println(list.lastIndexOf("abc2"));
        list.set(0,"A");
        System.out.println(list);
        List<String> sublist = list.subList(2, 4);
        System.out.println(sublist);
        System.out.println(list.size());

    }
}

