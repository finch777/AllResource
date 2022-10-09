package com.levi.algorithm_practice.java.juc.pro_con;

public class TestQueue {
    public static void main(String[] args) {
        new Thread(new Productor()).start();
        System.out.println("生产者线程已启动......");
        new Thread(new Consumer()).start();
        System.out.println("消费者线程已启动......");
    }
}
