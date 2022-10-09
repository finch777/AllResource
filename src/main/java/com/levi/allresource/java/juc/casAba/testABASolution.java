package com.levi.allresource.java.juc.casAba;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

/**
 * �˽ⷨ�Ŀӣ�Integer 128  blog
 */
public class testABASolution {

    private static AtomicStampedReference ai = new AtomicStampedReference<>(4, 0);

    public static void main(String[] args) {

        new Thread( () -> {
            boolean b1 = ai.compareAndSet(4,5, 0, 1);
            System.out.println(Thread.currentThread().getName() + "�Ƿ�4�ĳ���5 "+b1);

            boolean b2 = ai.compareAndSet(5,4, 1, 2);
            System.out.println(Thread.currentThread().getName() + "�Ƿ�5�ĳ���4 "+b2);


        }, "A").start();


        new Thread( () -> {
            //ģ����߳�ִ���������
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10));
            boolean b3 = ai.compareAndSet(4, 10, 0, 1);
            System.out.println(Thread.currentThread().getName() + "�Ƿ�4�ĳ���10 "+b3);
        }, "B").start();

        //�ȴ������߳����,Ϊʲô��1����Ϊmain�߳�
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(Thread.activeCount());

        System.out.println("ai���յ�ֵΪ��"+ai.getReference());

    }

}
