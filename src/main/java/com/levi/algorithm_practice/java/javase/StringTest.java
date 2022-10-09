package com.levi.algorithm_practice.java.javase;

/**
 * @author Levi Wang
 * @create 2022-10-09 18:57
 *
 *
 *
 */

public class StringTest {


    public static void main(String[] args) {
        stringTest();
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
