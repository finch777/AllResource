package com.levi.allresource.java.juc.casAba;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * ģ��CAS������ABA����
 * ABA����ٷ����ͣ����ж���̶߳�һ��ԭ������в�����ʱ��
 * ĳ���߳��ڶ�ʱ���ڽ�ԭ�����ֵA�޸�ΪB�������Ͻ����޸�ΪA����ʱ�����̲߳���֪�����ǻ��޸ĳɹ���
 */
public class testABAProblem {
    private static AtomicInteger ai = new AtomicInteger(4);

    public static void main(String[] args) {

        new Thread( () -> {
            boolean b1 = ai.compareAndSet(4,5);
            System.out.println(Thread.currentThread().getName() + "�Ƿ�4�ĳ���5 "+b1);

            boolean b2 = ai.compareAndSet(5,4);
            System.out.println(Thread.currentThread().getName() + "�Ƿ�5�ĳ���4 "+b2);


        }, "A").start();


        new Thread( () -> {
            //ģ����߳�ִ���������
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10));
            boolean b3 = ai.compareAndSet(4, 10);
            System.out.println(Thread.currentThread().getName() + "�Ƿ�4�ĳ���10 "+b3);
        }, "B").start();


        //�ȴ������߳����
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println("ai���յ�ֵΪ��"+ai.get());



    }
}
