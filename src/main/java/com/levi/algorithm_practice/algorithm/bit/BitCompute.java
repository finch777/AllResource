package com.levi.algorithm_practice.algorithm.bit;


public class BitCompute {
    public static void main(String[] args) {
        char c1 = 'd';
        //将该字母转换为小写
        char c2 = (char) (c1 | ' ');
        //将该字母转换为大写
        char c3 = (char) (c1 & '_');
        //大小写互换
        char c4 = (char) (c1 ^ ' ');
        System.out.println(c2);
        System.out.println(c3);
        System.out.println(c4);

        swap();

        isSame();

        addOne();

        System.out.println( - (1 << 32 ) );

    }

    private static void swap(){
        int a = 1, b = 2;
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("a="+a+" b="+ b);
    }


    private static void isSame(){
        int a = 2, b = 3;
        System.out.println((a ^ b) < 0);
    }

    private static void addOne(){
        int a = -3;
        int b = -(~a);
        System.out.println(b);
    }
    /*

    *
    * */


}
