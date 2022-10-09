package com.levi.algorithm_practice.testdisruptor;

public class AppServer {
    public static void main(String[] args) {
        new Thread(new ProducerThread()).start();
    }
}
