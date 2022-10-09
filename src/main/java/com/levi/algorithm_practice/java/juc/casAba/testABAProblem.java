package com.levi.algorithm_practice.java.juc.casAba;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 模拟CAS带来的ABA问题
 * ABA问题官方解释：当有多个线程对一个原子类进行操作的时候，
 * 某个线程在短时间内将原子类的值A修改为B，又马上将其修改为A，此时其他线程不感知，还是会修改成功。
 */
public class testABAProblem {
    private static AtomicInteger ai = new AtomicInteger(4);

    public static void main(String[] args) {

        new Thread( () -> {
            boolean b1 = ai.compareAndSet(4,5);
            System.out.println(Thread.currentThread().getName() + "是否将4改成了5 "+b1);

            boolean b2 = ai.compareAndSet(5,4);
            System.out.println(Thread.currentThread().getName() + "是否将5改成了4 "+b2);


        }, "A").start();


        new Thread( () -> {
            //模拟此线程执行慢的情况
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(10));
            boolean b3 = ai.compareAndSet(4, 10);
            System.out.println(Thread.currentThread().getName() + "是否将4改成了10 "+b3);
        }, "B").start();


        //等待其他线程完成
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println("ai最终的值为："+ai.get());



    }
}
