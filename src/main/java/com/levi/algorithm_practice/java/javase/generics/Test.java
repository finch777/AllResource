package com.levi.algorithm_practice.java.javase.generics;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        /*
        * 定义了两个ArrayList数组，不过一个是ArrayList<String>泛型类型的，只能存储字符串；一个是ArrayList<Integer>泛型类型的，只能存储整数，最后，
        * 通过list1对象和list2对象的getClass()方法获取他们的类的信息，最后发现结果为true。说明泛型类型String和Integer都被擦除掉了，只剩下原始类型。
        * */
        ArrayList<String> l1 = new ArrayList<>();
        l1.add("dzq");

        ArrayList<Integer> l2 = new ArrayList<>();
        l2.add(123);

        System.out.println(l1.getClass() == l2.getClass());


        /*
        * 通过反射添加其他类型的元素
        * */
//        ArrayList<Integer> list = new ArrayList<>();
//        list.add(123);
//
//        list.getClass().getMethod("add", Object.class).invoke(list, "abc");
//
//        for (int i = 0; i < list.size(); i ++) {
//            System.out.println(list.get(i));
//        }

    }
}
