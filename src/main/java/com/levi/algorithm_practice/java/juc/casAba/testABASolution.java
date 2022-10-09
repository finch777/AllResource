package com.levi.algorithm_practice.java.juc.casAba;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.LockSupport;

/**
 * 此解法的坑：Integer 128  blog
 */
public class testABASolution {

    private static AtomicStampedReference ai = new AtomicStampedReference<>(4, 0);

    public static void main(String[] args) {

        new Thread( () -> {
            boolean b1 = ai.compareAndSet(4,5, 0, 1);
            System.out.println(Thread.currentThread().getName() + "是否将4改成了5 "+b1);

            boolean b2 = ai.compareAndSet(5,4, 1, 2);
            System.out.println(Thread.currentThread().getName() + "是否将5改成了4 "+b2);


        }, "A").start();


        new Thread( () -> {
            //模拟此线程执行慢的情况
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10));
            boolean b3 = ai.compareAndSet(4, 10, 0, 1);
            System.out.println(Thread.currentThread().getName() + "是否将4改成了10 "+b3);
        }, "B").start();

        //等待其他线程完成,为什么是1，因为main线程
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(Thread.activeCount());

        System.out.println("ai最终的值为："+ai.getReference());

    }

}
