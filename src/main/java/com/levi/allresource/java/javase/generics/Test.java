package com.levi.allresource.java.javase.generics;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        /*
        * ����������ArrayList���飬����һ����ArrayList<String>�������͵ģ�ֻ�ܴ洢�ַ�����һ����ArrayList<Integer>�������͵ģ�ֻ�ܴ洢���������
        * ͨ��list1�����list2�����getClass()������ȡ���ǵ������Ϣ������ֽ��Ϊtrue��˵����������String��Integer�����������ˣ�ֻʣ��ԭʼ���͡�
        * */
        ArrayList<String> l1 = new ArrayList<>();
        l1.add("dzq");

        ArrayList<Integer> l2 = new ArrayList<>();
        l2.add(123);

        System.out.println(l1.getClass() == l2.getClass());


        /*
        * ͨ����������������͵�Ԫ��
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
