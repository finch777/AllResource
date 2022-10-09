package com.levi.allresource.testdisruptor.perf;

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

    private int MAX_SIZE = 1000000; //��־�����������

    /**
     * ���죬��ȡʵ��
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
     * ����һ����Ϣ
     */
    public boolean add(AsyncFastLog obj) {
        if(atomicCount.intValue() >= MAX_SIZE){
            System.out.println("��־��������");
            return false;
        }

        queue.offer(obj);
        //������к󣬶���������һ
        atomicCount.incrementAndGet();
        return true;
    }

    /**
     * ��ȡһ����־��Ϣ
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
     * ��ȡ���д�С
     * @return
     */
    public int size() {
        return atomicCount.get();
    }

}
