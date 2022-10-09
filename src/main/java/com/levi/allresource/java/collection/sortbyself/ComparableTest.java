package com.levi.allresource.java.collection.sortbyself;

import java.util.Arrays;

/**
 * @author Levi Wang
 * @create 2022-10-05 20:22
 */

public class ComparableTest {

    public static void main(String[] args) {

        Number all[] = new Number[5];
        all[1] = new Number(1, 10);
        all[2] = new Number(2, 8);
        all[3] = new Number(3, 7);
        all[0] = new Number(4, 6);
        all[4] = new Number(2, 6);

//        Arrays.sort(all);

        Arrays.sort(all, (o1, o2) -> {
            if (o1.a != o2.a) return o1.a - o2.a;
            else if (o1.b != o2.b) return o1.b - o2.b;
            return 0;
        });

//        Arrays.sort(all, new Comparator<Number>() {
//            @Override
//            public int compare(Number o1, Number o2) {
//                if (o1.a > o2.a) return 1;
//                else if (o1.a < o2.a) return -1;
//                return 0;
//            }
//        });

//        Collections.sort(Arrays.asList(all), new Comparator<Number>() {
//            @Override
//            public int compare(Number o1, Number o2) {
//                if (o1.a > o2.a) return 1;
//                else if (o1.a < o2.a) return -1;
//                return 0;
//
//            }
//        });

        System.out.println(Arrays.toString(all));

    }

}

class Number implements Comparable{
    int a;
    int b;

    public Number(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Number{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Number) {
            Number number = (Number) o;
            if (this.b > number.b) return 1;
            else if (this.b < number.b) return -1;
            return 0;

        } else throw new RuntimeException("������������Ͳ�һ��");
    }
}



