package com.levi.algorithm_practice.java.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Levi Wang
 * @create 2022-10-05 13:25
 *
 *  ����˵˵synchronized��Lock��������lock��ʲô�ô���
 *   synchronized��JVM���棬����JAVA�Ĺؼ���
 *   synchronized �ǲ���Ҫ�ֶ��ͷ�������synchronized����ִ�����Ժ�ϵͳ���Զ����߳��ͷŶ�����ռ��
 *    synchronized�����жϣ��������׳����쳣����������ִ�����
 *    synchronized�Ƿǹ�ƽ��
 *    synchronized��֧�־�ȷ���ѣ�ֻ��������ѻ����ǻ���ȫ���߳�
 *
 *   Lock��API����ľ����࣬����java5�Ժ��³���һ����
 *    lock����Ҫ�ֶ�ȥ�ͷ�������û��������ȥ�ͷ������Ϳ��ܵ�������������
 *    lock�ǿ����жϵģ���Ҫ�����ó�ʱ�ķ�����
 *    lockĬ���Ƿǹ�ƽ��������Ҳ֧�ֹ�ƽ��
 *    lock��֧�־�ȷ����
 *
 *
 * ���߳�֮�䰴˳����ã�ʵ��A->B->C �����߳�����
 *  AA��ӡ5�Σ� BB��ӡ10�Σ�CC��ӡ15��
 *  ������
 *  AA��ӡ5�Σ� BB��ӡ10�Σ�CC��ӡ15��
 *  ��ʮ��
 **/
class ShareResource{
    private int number = 1;
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    //��һ���߳�
    public void print5(){
        lock.lock();
        try{
            //�ж�
            while (number!=1){  //˵�����״��߳̽���
                c1.await(); //��������
            }
            //2.�ɻ�
            for (int i = 1; i <=5 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.֪ͨB�߳�
            number = 2;
            c2.signal();//֪ͨb�߳�

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //�ڶ����߳�
    public void print10(){
        lock.lock();
        try{
            //�ж�
            while (number!=2){  //˵�����״��߳̽���
                c2.await(); //��������
            }
            //2.�ɻ�
            for (int i = 1; i <=10 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.֪ͨB�߳�
            number = 3;
            c3.signal();//֪ͨb�߳�

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    //��3���߳�
    public void print15(){
        lock.lock();
        try{
            //�ж�
            while (number!=3){  //˵�����״��߳̽���
                c3.await(); //��������
            }
            //2.�ɻ�
            for (int i = 1; i <=15 ; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //3.���»�ȥ֪ͨA�߳�
            number = 1;
            c1.signal();//֪ͨA�߳�

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                shareResource.print5();
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                shareResource.print10();
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i <=10 ; i++) {
                shareResource.print15();
            }
        },"C").start();
    }

}



