package com.levi.algorithm_practice.java.juc.interview;

import java.util.concurrent.Semaphore;

/*
* ͨ�� N ���߳�˳��ѭ����ӡ�� 1 �� maxNum��N���û�ָ��
*
* */
public class PrintNumberUsingSemaphore {
    private int maxNum;
    private volatile int index = 1;
    private static final int THREAD_COUNT = 10;
    public PrintNumberUsingSemaphore(int maxNum) {
        this.maxNum = maxNum;
    }

    public static void main(String[] args) {
        PrintNumberUsingSemaphore printNumberUsingSemaphore = new PrintNumberUsingSemaphore(100);
        Semaphore[] queue = new Semaphore[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i ++) {
            if (i == 0) queue[i] = new Semaphore(1);
            else queue[i] = new Semaphore(0);
        }

        for (int i = 0; i < THREAD_COUNT; i ++) {
            final int x = i;
            new Thread(() -> printNumberUsingSemaphore.print(x ,queue[x], queue[(x + 1) % THREAD_COUNT])).start();
        }
    }

    private void print(int num, Semaphore cur, Semaphore nxt) {

        while (index + THREAD_COUNT - 1 <= maxNum) {
            try {
                cur.acquire();
                System.out.println( "this is NO." + (num + 1) + " thread��" + index++);
                nxt.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
