package com.levi.algorithm_practice.java.javase.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IOTest {
    public static void main(String[] args) throws IOException {


//        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("����������");
//        //Ĭ�ϵĻ���ģʽ��BufferReader����ȱ��
//        String str = bufr.readLine(); //����������Ϣ��Ĭ��ʹ�ûس�����
//        if(str.matches("\\d{1,3}"))  //   \d��ʾ����һ��0~9�е�����   ��һ��\��Ϊ��ת��   {1,3}��ʾƥ��1��3��
//            System.out.println(str);
//        else
//            System.out.println("������������");


        stringTest();




        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4));
//        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        List<Integer> collect = list.stream().filter(a -> a % 2 == 0).collect(Collectors.toList());
        System.out.println(collect);



    }


    /**
     * �ַ�����ز���
     * */
    private static void stringTest() {
        String s = "abc2de";

        int i = s.indexOf(97); // �ַ�a��ascll����97

        System.out.println(i);

        System.out.println('a'==97);

        String replace = s.replace("bc", "cb");
        System.out.println(replace);

        System.out.println(s.substring(0, 3));

        // ��ת�ַ���
        System.out.println(new StringBuilder(s).reverse().toString());
    }
}
