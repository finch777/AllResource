package com.levi.algorithm_practice.java.juc.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(5));

        for(int i=0;i<15;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("�̳߳����߳���Ŀ��"+executor.getPoolSize()+"�������еȴ�ִ�е�������Ŀ��"+
                    executor.getQueue().size()+"����ִ������������Ŀ��"+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}


class MyTask implements Runnable {
    private int taskNum;

    public MyTask(int num) {
        this.taskNum = num;
    }

    /**
     *
     * �߳̿����ü̳�Thread�����ʵ��Runnable�ӿ���ʵ��.
     *      Thread.sleep()��Thread��ķ���,ֻ�Ե�ǰ�߳�������,˯��һ��ʱ��.
     *      ����߳���ͨ���̳�Threadʵ�ֵĻ���2������û������
     *      ����߳���ͨ��ʵ��Runnable�ӿ���ʵ�ֵ�,����Thread��,����ֱ��ʹ��Thread.sleep()
     *      ����ʹ��Thread.currentThread()���õ���ǰ�̵߳����òſ��Ե���sleep(),
     *      ����Ҫ��Thread.currentThread().sleep()��˯��...
     *
     *
     */
    @Override
    public void run() {
        System.out.println("����ִ��task "+taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"ִ�����");
    }
}
