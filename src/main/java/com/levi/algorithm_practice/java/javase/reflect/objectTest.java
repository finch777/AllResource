package com.levi.algorithm_practice.java.javase.reflect;

import java.lang.reflect.Constructor;

public class objectTest {
    public static void main(String[] args) {
        String className = "com.finch.test01.javase.reflect.Hero";
        try {
            //获取类对象的第一种方式
            Class pClass1 = Class.forName(className);
            //获取类对象的第二种方式
            Class pClass2 = Hero.class;
            //获取类对象的第三种方式
            Class pClass3 = new Hero().getClass();
            System.out.println(pClass1==pClass2);//输出true
            System.out.println(pClass1==pClass3);//输出true
            Constructor constructor = pClass1.getConstructor();
            constructor.setAccessible(true);

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
