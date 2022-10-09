package com.levi.algorithm_practice.testdisruptor.perf;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class LogInfoQueue {

    private ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

    private AtomicInteger atomicCount = new AtomicInteger();

    public int getMAX_SIZE() {
        return MAX_SIZE;
    }

    public void setMAX_SIZE(int MAX_SIZE) {
        this.MAX_SIZE = MAX_SIZE;
    }

    private int MAX_SIZE = 1000000; //日志容器最大容量

    /**
     * 构造，获取实例
     */
    private LogInfoQueue() {
    }
    private static class LogQueueInstance{
        private static final LogInfoQueue INSTANCE = new LogInfoQueue();
    }
    public static LogInfoQueue getInstance(){
        return LogQueueInstance.INSTANCE;
    }

    /**
     * 放入一条信息
     */
    public boolean add(AsyncFastLog obj) {
        if(atomicCount.intValue() >= MAX_SIZE){
            System.out.println("日志队列已满");
            return false;
        }

        queue.offer(obj);
        //放入队列后，队列容量加一
        atomicCount.incrementAndGet();
        return true;
    }

    /**
     * 获取一条日志信息
     */
    public AsyncFastLog getLogInfo(){
        Object poll = queue.poll();
        AsyncFastLog tmp = (AsyncFastLog) poll;

        if(tmp == null){
            return null;
        }
        if(atomicCount.decrementAndGet() < 0) {
            atomicCount.set(0);
        }

        return tmp;
    }

    /**
     * 获取队列大小
     * @return
     */
    public int size() {
        return atomicCount.get();
    }

}
