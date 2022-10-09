package com.levi.allresource.java.juc;


public class ThreadLocalTest {

    static ThreadLocal<String> localVar = new ThreadLocal<>();

    static void print(String str) {
        //��ӡ��ǰ�߳��б����ڴ��б��ر�����ֵ
        System.out.println(str + " :" + localVar.get());
        //��������ڴ��еı��ر���
        localVar.remove();
    }

    public static void main(String[] args) {
        Thread t1  = new Thread(new Runnable() {
            @Override
            public void run() {
                //�����߳�1�б��ر�����ֵ
                localVar.set("localVar1");
                //���ô�ӡ����
                print("thread1");
                //��ӡ���ر���
                System.out.println("after remove : " + localVar.get());
            }
        });

        Thread t2  = new Thread(new Runnable() {
            @Override
            public void run() {
                //�����߳�1�б��ر�����ֵ
                localVar.set("localVar2");
                //���ô�ӡ����
                print("thread2");
                //��ӡ���ر���
                System.out.println("after remove : " + localVar.get());
            }
        });

        t1.start();
        t2.start();
    }
}