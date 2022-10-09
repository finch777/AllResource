package com.levi.allresource.java.juc.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Levi Wang
 * @create 2022-10-05 13:23
 */

class MyResource
{
    private volatile boolean FLAG = true; //Ĭ�Ͽ�������������+����
    private AtomicInteger atomicInteger = new AtomicInteger();

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    //������
    public void MyProd() throws Exception{
        String data = null;
        boolean retValue ; //Ĭ����false

        while (FLAG)
        {
            //�����������������
            data = atomicInteger.incrementAndGet()+"";//����++i����˼
            retValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if (retValue){ //�����true����ô����ǰ����̲߳������ݳɹ�
                System.out.println(Thread.currentThread().getName()+"\t�������"+data+"�ɹ�");
            }else {  //��ô���ǲ���ʧ��
                System.out.println(Thread.currentThread().getName()+"\t�������"+data+"ʧ��");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        //���FLAG��false�ˣ����ϴ�ӡ
        System.out.println(Thread.currentThread().getName()+"\t���ϰ��ͣ�ˣ���ʾFLAG=false,��������");
    }

    //������
    public void MyConsumer() throws Exception
    {
        String result = null;
        while (FLAG) { //��ʼ����
            //�����ӵȲ����������������������ݾͲ�ȡ��
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if (null == result || result.equalsIgnoreCase("")){ //���ȡ����������
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t ����������û��ȡ�����ݣ������˳�");
                System.out.println();
                System.out.println();
                return;//�˳�
            }
            System.out.println(Thread.currentThread().getName()+"\t���Ѷ�������"+result+"�ɹ�");
        }
    }

    //��ͣ����
    public void stop() throws Exception{
        this.FLAG = false;
    }

}

class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args)  throws Exception{
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t �����߳�����");
            try {
                myResource.MyProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t �����߳�����");
            System.out.println();
            System.out.println();
            try {
                myResource.MyConsumer();
                System.out.println();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        try { TimeUnit.SECONDS.sleep(5); }catch (Exception e) {e.printStackTrace();}
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5����ʱ�䵽����bossMain���߳̽�ͣ�������");
        myResource.stop();
    }
}
