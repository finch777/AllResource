package com.levi.algorithm_practice.java.regex;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        while(true) {
//            String ins = scanner.nextLine();
////            String ino = ins.replaceAll("(.)\\1+", "$1$1");
//
////            System.out.println(ino);
//            System.out.println(Arrays.toString(ins.split("(.)\\1+")));
//        }


//        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("����������");
//        //Ĭ�ϵĻ���ģʽ��BufferReader����ȱ��
//        String str = bufr.readLine(); //����������Ϣ��Ĭ��ʹ�ûس�����
//        if(str.matches("\\d{1,3}"))  //   \d��ʾ����һ��0~9�е�����   ��һ��\��Ϊ��ת��   {1,3}��ʾƥ��1��3��
//            System.out.println(str);
//        else
//            System.out.println("������������");


//        demo1();

        demo2();


    }

    private static void demo2() {
        String Str = new String("Welcome to Yiibai.com");

        System.out.print("Return Value :" );
        System.out.println(Str.matches("(.*)Yii(.*)"));

        System.out.print("Return Value :" );
        System.out.println(Str.matches("Yii"));

        System.out.print("Return Value :" );
        System.out.println(Str.matches("Welcome(.*)"));
    }


    private static void demo1() {
        String content = "I am coder " +
                "from nowcoder.com.";

        String pattern = ".*nowcoder.*";

        boolean isMatch = Pattern.matches(pattern, content);
        System.out.println("�ַ������Ƿ������ 'nowcoder' ���ַ���? " + isMatch);
    }

    /**
     * �ж��ַ����Ƿ���һ����Ч������
     *
     * @param theStr
     * @return true �ǣ�false��
     */
    public static boolean isDate(String theStr) {
        return theStr.matches("\\d{4}\\-\\d{1,2}\\-\\d{1,2}");
    }


}
