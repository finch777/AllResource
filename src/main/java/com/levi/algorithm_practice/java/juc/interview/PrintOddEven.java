package com.levi.algorithm_practice.java.juc.interview;

/*
* �����߳̽����ӡ������ż��
*
* �������ֱ�ӵ��������߳̽����ӡ0-100�Ľ⣬ֻ���times�ĳ�50���ɣ������߳����δ�ӡ������ż��
* */
public class PrintOddEven {

    private int times;
    private int number = 1;
    private static final Object OBJECT_LOCK = new Object();

    public PrintOddEven(int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        PrintOddEven printOddEven = new PrintOddEven(50);

        new Thread(() -> printOddEven.printOE(1), "A").start();

        new Thread(() -> printOddEven.printOE(0), "B").start();
    }

    private void printOE(int target) {
        for (int i = 0; i < times; ) {
            synchronized (OBJECT_LOCK) {
                while ((number & 1) != target) {
                    try {
                        OBJECT_LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(number);
                number ++;
                i ++;

                OBJECT_LOCK.notify();
            }
        }
    }

}
