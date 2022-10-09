package com.levi.algorithm_practice.java.juc.interview;

import java.util.concurrent.Semaphore;

/*
* ʹ��semaphoreѭ����ӡABC
* */
public class PrintABCUsingSemaphore {
    private int times;
    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);
    public PrintABCUsingSemaphore (int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        PrintABCUsingSemaphore printABCUsingSemaphore = new PrintABCUsingSemaphore(10);

        new Thread(() -> printABCUsingSemaphore.print("A", semaphoreA, semaphoreB), "A").start();

        new Thread(() -> printABCUsingSemaphore.print("B", semaphoreB, semaphoreC), "B").start();

        new Thread(() -> printABCUsingSemaphore.print("C", semaphoreC, semaphoreA), "C").start();

    }

    private void print(String name, Semaphore cur, Semaphore nxt) {
        for (int i = 0; i < times; i ++) {
            try {
                cur.acquire();
                System.out.println(name);
                nxt.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
