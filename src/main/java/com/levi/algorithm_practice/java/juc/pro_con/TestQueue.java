package com.levi.algorithm_practice.java.juc.pro_con;

public class TestQueue {
    public static void main(String[] args) {
        new Thread(new Productor()).start();
        System.out.println("�������߳�������......");
        new Thread(new Consumer()).start();
        System.out.println("�������߳�������......");
    }
}
