package com.levi.allresource.java.juc.interview;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * �����߳̽����ӡA B C
 * �Զ�������
 */
public class PrintABCUsingLock {

    public static void main(String[] args) {
        ShareResource2 shareResource2 = new ShareResource2(10);

        new Thread(() -> {
            shareResource2.printABC("A", 1);
        }, "A").start();

        new Thread(() -> {
            shareResource2.printABC("B", 2);
        }, "B").start();

        new Thread(() -> {
            shareResource2.printABC("C", 0);
        }, "C").start();
    }

}


class ShareResource2 {
    private int times;
    private int number = 1;
    private static final Lock lock = new ReentrantLock();

    public ShareResource2 (int times) {
        this.times = times;
    }

    public void printABC(String name, int target) {
        for (int i = 0; i < times; ) {
            lock.lock();
            try {
                if (number % 3 == target) {
                    number ++;
                    i ++;
                    System.out.println(name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

    }

}