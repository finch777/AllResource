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
//        System.out.println("请输入年龄");
//        //默认的换行模式是BufferReader最大的缺点
//        String str = bufr.readLine(); //接受输入信息，默认使用回车换行
//        if(str.matches("\\d{1,3}"))  //   \d表示任意一个0~9中的数字   第一个\是为了转义   {1,3}表示匹配1到3个
//            System.out.println(str);
//        else
//            System.out.println("输入数据有误！");


        stringTest();




        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 3, 4));
//        List<Integer> collect = list.stream().distinct().collect(Collectors.toList());
        List<Integer> collect = list.stream().filter(a -> a % 2 == 0).collect(Collectors.toList());
        System.out.println(collect);



    }


    /**
     * 字符串相关操作
     * */
    private static void stringTest() {
        String s = "abc2de";

        int i = s.indexOf(97); // 字符a的ascll码是97

        System.out.println(i);

        System.out.println('a'==97);

        String replace = s.replace("bc", "cb");
        System.out.println(replace);

        System.out.println(s.substring(0, 3));

        // 翻转字符串
        System.out.println(new StringBuilder(s).reverse().toString());
    }
}
