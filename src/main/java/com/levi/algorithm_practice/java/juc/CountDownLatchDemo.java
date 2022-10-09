package com.levi.algorithm_practice.java.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author Levi Wang
 * @create 2022-10-05 13:04
 *
 *
 * ��һЩ�߳�����ֱ������һЩ��ɺ�ű�����
 *
 * CountDownLatch��Ҫ����������,��һ�������̵߳���await����ʱ,�����̻߳ᱻ����.
 * �����̵߳���countDown������������1(����countDown����ʱ�̲߳�������),����������ֵ��Ϊ0,�����await�������������̻߳ᱻ����,����ִ��
 *
 */

public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        closeDoor();

    }

    /**
     * ���Ű���
     * @throws InterruptedException
     */
    private static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t" + "������ϰ");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t�೤�����뿪����");
    }

}
