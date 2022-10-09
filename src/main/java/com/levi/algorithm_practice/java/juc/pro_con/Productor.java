package com.levi.algorithm_practice.java.juc.pro_con;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class Productor implements Runnable{
    private TimeLogQueue queue = TimeLogQueue.getInstance();


    @Override
    public void run() {
        while(true) {
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(1));
            String str = String.valueOf(System.nanoTime());
            try {
                queue.add(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
