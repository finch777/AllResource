package com.levi.algorithm_practice.java.javase.generics;

import java.lang.reflect.InvocationHandler;
import java.security.PublicKey;
import java.util.Scanner;

// 泛型类
class GenericClass <T> {
    private T key;

    public GenericClass(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }
}

public class GenericClassTest {

    // 泛型方法
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        GenericClass<Integer> genericClass = new GenericClass<>(2);
        System.out.println(genericClass.getKey());

        Integer[] integers = new Integer[]{1, 2, 4};
        printArray(integers);
    }
}
