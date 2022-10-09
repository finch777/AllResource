package com.levi.algorithm_practice.java.juc.pro_con;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Levi Wang
 * @create 2022-09-30 13:25
 */

public class Test {
    static int x = 0;

    public static void main(String[] args) throws InterruptedException {

//        LinkedBlockingQueue queue = new LinkedBlockingQueue<Integer>(3);
//
//        for (int i = 0; i < 5; i ++) {
//            int finalI = i;
//            new Thread(()->{
//                try {
//
//                    queue.put(finalI);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }, i + "号线程").start();
//        }
//
//        System.out.println(queue.take());
//        System.out.println(queue.take());
//        System.out.println("队列容量队列容量 "+queue.size());


        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();


        for (int i = 1; i <= 6; i ++) {
            new Thread(() -> {
                lock.lock();
                try {

                    while (x == 4) {
                        condition.await();
                    }
                    x += 1;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }, "A").start();
        }

        Thread.sleep(200);
        x--;
        x--;
        condition.signalAll();
        System.out.println(x);

    }

}
