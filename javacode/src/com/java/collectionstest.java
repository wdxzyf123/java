package com.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author skwang
 * @create 2021-03-08-15:37
 */
public class collectionstest {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("b");
        list.add("a");
        list.add("cd");
        list.add("1");
        list.add("2");
        list.add("a");
        System.out.println(list);
        Collections.reverse(list);//反转排列
        System.out.println(list);
        Collections.shuffle(list);//随机排列
        System.out.println(list);
        Collections.sort(list);//升序排列
        System.out.println(list);

        Student student1 = new Student(12, "zhangsan1");
        Student student2 = new Student(17, "zhangsan2");
        Student student3 = new Student(19, "zhangsan3");
        Student student4 = new Student(9, "zhangsan4");

        ArrayList<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);

        for(Student stu : students) {
            System.out.println(stu.name+ "," + stu.age);
        }
        System.out.println("-------------------------------------");
        Collections.sort(students, new Student());
        for(Student stu : students) {
            System.out.println(stu.name+ "," + stu.age);
        }
        System.out.println("-------------------------------------");
        Collections.swap(list,2,1);
        System.out.println(list);
        System.out.println(Collections.max(list));
        System.out.println(Collections.min(list));
        //自定义的大小求最大最小
        Student s = Collections.max(students, new Student());
        System.out.println(s.name+" "+s.age);


        System.out.println(Collections.frequency(list, "x"));
        //替换值
        Collections.replaceAll(list,"a","aa");//所有的a
    }
}

class Student implements Comparator<Student>{
    int age;
    String name;
    public Student() {

    }
    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }
    @Override
    public int compare(Student o1, Student o2) {
        if(o1.age > o2.age) {
            return 1;
        }else if(o1.age < o2.age) {
            return -1;
        }else{
            return 0;
        }
    }
}