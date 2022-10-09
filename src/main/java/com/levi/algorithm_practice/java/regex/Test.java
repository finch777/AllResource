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
//        System.out.println("请输入年龄");
//        //默认的换行模式是BufferReader最大的缺点
//        String str = bufr.readLine(); //接受输入信息，默认使用回车换行
//        if(str.matches("\\d{1,3}"))  //   \d表示任意一个0~9中的数字   第一个\是为了转义   {1,3}表示匹配1到3个
//            System.out.println(str);
//        else
//            System.out.println("输入数据有误！");


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
        System.out.println("字符串中是否包含了 'nowcoder' 子字符串? " + isMatch);
    }

    /**
     * 判断字符创是否是一个有效的日期
     *
     * @param theStr
     * @return true 是，false否
     */
    public static boolean isDate(String theStr) {
        return theStr.matches("\\d{4}\\-\\d{1,2}\\-\\d{1,2}");
    }


}
