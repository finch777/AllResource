package com.levi.algorithm_practice.java.juc.pro_con;

import java.util.concurrent.atomic.AtomicInteger;

public class TimeLogQueue {

    private TimeLogQueue () {}
    private static class TimeLogQueueHolder{
        static TimeLogQueue INSTANCE = new TimeLogQueue();
    }
    public static final TimeLogQueue getInstance() {
        return TimeLogQueueHolder.INSTANCE;
    }

    private MyBlockingQueue<String> queue = new MyBlockingQueue<>();

    AtomicInteger size = new AtomicInteger();
    private int capacity = 100000;

    public boolean add(String s) throws Exception {
        if (size.get() > capacity) {
            // 记录到错误日志里
            return false;
        }
        queue.put(s);
        size.incrementAndGet();
        return true;
    }

    public String get() throws InterruptedException {
        String res = queue.take();
        if (res == null) return null;
        if (size.decrementAndGet() < 0) size.set(0);
        return res;
    }

}
