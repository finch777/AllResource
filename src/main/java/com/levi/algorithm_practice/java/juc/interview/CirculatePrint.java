package com.levi.algorithm_practice.java.juc.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CirculatePrint {

    public static void main(String[] args) {

        firstCase();


    }

    private static void firstCase() {
        ShareResource shareResource = new ShareResource();

        new Thread (() -> {
            for (int i = 0; i < 10; i ++) {
                shareResource.print5();
            }
        }, "A").start();

        new Thread (() -> {
            for (int i = 0; i < 10; i ++) {
                shareResource.print10();
            }
        }, "B").start();

        new Thread (() -> {
            for (int i = 0; i < 10; i ++) {
                shareResource.print15();
            }
        }, "C").start();
    }
}


/**
 * ÿ�ִ�ӡ5��A��10��B��15��C��һ����ӡ10��
 *
 * ʹ��Condition�Դﵽ��ȷ���ѵ�Ŀ��
 * */
class ShareResource {
    // 1��ӦA��2��ӦB��3��ӦC
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            // û�ֵ��͵ȴ�
            while (number != 1) {
                c1.await();
            }

            for (int i = 1; i <= 5; i ++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }

            number = 2;
            c2.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {

            while (number != 2) {
                c2.await();
            }

            for (int i = 1; i <= 10; i ++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }

            number = 3;
            c3.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try{

            while (number != 3) {
                c3.await();
            }

            for (int i = 1; i <= 15; i ++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }

            number = 1;
            c1.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }



}
