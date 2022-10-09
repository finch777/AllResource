package com.levi.algorithm_practice.java.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Levi Wang
 * @create 2022-10-05 13:10
 *
 * �ź�������Ҫ�û�����Ŀ��,һ�������ڶ�ȹ�����Դ���໥�ų�ʹ��,��һ�����ڲ�����Դ���Ŀ���.
 *
 */

public class SemaphoreDemo {

    public static void main(String[] args) {
        //ģ��3��ͣ��λ
        Semaphore semaphore = new Semaphore(3);
        //ģ��6������
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    //������Դ
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t������λ");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t ͣ3���뿪��λ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    //�ͷ���Դ
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }

}
