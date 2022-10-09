package com.levi.allresource.java.javase.reflect;

import java.lang.reflect.Constructor;

public class objectTest {
    public static void main(String[] args) {
        String className = "com.finch.test01.javase.reflect.Hero";
        try {
            //��ȡ�����ĵ�һ�ַ�ʽ
            Class pClass1 = Class.forName(className);
            //��ȡ�����ĵڶ��ַ�ʽ
            Class pClass2 = Hero.class;
            //��ȡ�����ĵ����ַ�ʽ
            Class pClass3 = new Hero().getClass();
            System.out.println(pClass1==pClass2);//���true
            System.out.println(pClass1==pClass3);//���true
            Constructor constructor = pClass1.getConstructor();
            constructor.setAccessible(true);

        } catch (ClassNotFoundException | NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
