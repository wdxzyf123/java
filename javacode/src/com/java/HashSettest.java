package com.java;

import java.util.*;

/**
 * @author skwang
 * @create 2021-03-08-12:01
 */
public class HashSettest {
    /**
     * 无序不重复
     */
    public static void main(String[] args) {
        Set set = new HashSet();//泛型object
        set.add(1);
        set.add('a');
        set.add("acs");
        set.add(3);
        set.remove(1);
        System.out.println(set.contains('a'));
        set.clear();
        System.out.println(set);
        set.add(1);
        set.add('a');
        set.add("acs");
        set.add(3);
        set.add(1);
        set.add(null);

        Iterator it = set.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());

        }

        for (Object obj : set) {
            System.out.println(obj);

        }
        System.out.println("---------------------------------");
        System.out.println(set.size());
        System.out.println("----------------------------------");

        /**
         * TreeSet
         *有排,自动调用compareTo()方法
         *
         * */
        Set<Integer> treeset = new TreeSet<>();//自然排序
        treeset.add(5);
        treeset.add(6);
        treeset.add(3);
        treeset.add(2);
        treeset.add(1);
        System.out.println(treeset);
        treeset.remove(5);
        treeset.remove(3);
        System.out.println(treeset.contains(1));
        System.out.println(treeset);
        Iterator<Integer> it1 = treeset.iterator();
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }

        for (Integer i : treeset) {
            System.out.println(i);
        }
        /**
         * 别的类型的排序 按年龄排序
         */
        System.out.println("-----------------------------");
        Person person1 = new Person("zhangsan1", 18);
        Person person2 = new Person("zhangsan2", 28);
        Person person3 = new Person("zhangsan3", 25);
        Person person4 = new Person("zhangsan4", 21);
        Person person5 = new Person("zhangsan5", 22);
        TreeSet<Person> people = new TreeSet<>(new Person());
        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);
        people.add(person5);
        for(Person p : people) {
            System.out.println(p.name+" "+p.age);
        }
    }
}


class Person implements Comparator<Person> {
    int age;
    String name;
    public Person() {

    }
    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }



    @Override
    public int compare(Person o1, Person o2) {
        if (o1.age < o2.age) {
            return 1;
        }
        if (o1.age > o2.age) {
            return -1;
        } else {
            return 0;
        }

    }
}
