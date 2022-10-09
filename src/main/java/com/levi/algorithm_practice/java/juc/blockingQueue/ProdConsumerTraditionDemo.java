package com.levi.algorithm_practice.java.juc.blockingQueue;

/**
 * @author Levi Wang
 * @create 2022-10-05 13:22
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



class ShareData
{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //��
    public void increment()throws  Exception
    {
        lock.lock();
        try{
            //1.�ж�
            while (number != 0){
                //�ȴ�����������
                condition.await();
            }
            //2.�ɻ�
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.֪ͨ����
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //��
    public void decrement()throws  Exception
    {
        lock.lock();
        try{
            //1.�ж�
            while (number == 0){
                //�ȴ�����������
                condition.await();
            }
            //2.�ɻ�
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //3.֪ͨ����
            condition.signalAll();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

/**
 *
 * ��ͳ�������ߺ�������Demo
 * ��Ŀ��һ����ʼֵΪ��ı����������̶߳��佻�������һ����һ����һ��������
 */
public class  ProdConsumerTraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.increment();//����
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"AAA").start();

        new Thread(() -> {
            for (int i = 1; i <=5 ; i++) {
                try {
                    shareData.decrement();//��
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"BBB").start();

    }
}



