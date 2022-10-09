package com.levi.algorithm_practice.java.juc.pro_con;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Consumer implements Runnable {

    private TimeLogQueue queue = TimeLogQueue.getInstance();

    @Override
    public void run() {
        while (true) {
            try {
                String res = queue.get();

                if (res == null) {
                    LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
                    continue;
                }

                System.out.println(res);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
