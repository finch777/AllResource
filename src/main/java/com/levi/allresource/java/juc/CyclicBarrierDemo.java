package com.levi.allresource.java.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Levi Wang
 * @create 2022-10-05 13:08
 *
 * CyclicBarrier��������˼�ǿ�ѭ��(Cyclic) ʹ�õ�����(barrier).
 * ��Ҫ����������,��һ���̵߳���һ������(Ҳ���Խ���ͬ����)ʱ������,֪�����һ���̵߳�������ʱ,���ϲŻῪ��,
 * ���б��������ص��̲߳Ż�����ɻ�,�߳̽�������ͨ��CyclicBarrier��await()����.
 *
 */

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(7,()->{
            System.out.println("�ٻ�����");
        });

        for (int i = 1; i <=7; i++) {
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t �ռ�����"+ temp +"������");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}