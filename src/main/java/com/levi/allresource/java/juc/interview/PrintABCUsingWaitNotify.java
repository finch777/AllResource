package com.levi.allresource.java.juc.interview;

public class PrintABCUsingWaitNotify {

    private int times;
    private int number = 1;
    private static final Object OBJECT_LOCK = new Object();

    public PrintABCUsingWaitNotify(int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        PrintABCUsingWaitNotify printABCUsingWaitNotify = new PrintABCUsingWaitNotify(10);

        new Thread(() -> {
            printABCUsingWaitNotify.printABC("A", 1);
        }, "A").start();

        new Thread(() -> {
            printABCUsingWaitNotify.printABC("B", 2);
        }, "B").start();

        new Thread(() -> {
            printABCUsingWaitNotify.printABC("C", 0);
        }, "C").start();

    }

    /*
    * �����̣߳�ÿ���߳̽�����ѭ��times��
    * һ��ʼ�������̶߳�������Ȼ��ʼ��OBJECT_LOCK�����������Ϊnumberһ��ʼ��1�����Լ�ʹB��C����������Ҳ��waitס
    * A�߳�������֮�󣬴�ӡ��A"��Ȼ���number�ĳ�2�����Լ��������ջ�еı���i��1��Ȼ����B��C��
    * ��ʱnumberΪ2������ֻ��B��������ִ�У���number�ĳ�3�����Լ��������ջ�еı���i��1��Ȼ����A��C��
    * ��ʱnumberΪ3������ֻ��C��ִ�У���number�ĳ�3�����Լ��������ջ�еı���i��1��Ȼ����A��C��
    * ......
    * ֱ��ÿ���߳�˽�е�i���ﵽtimes����ֹ
    * */
    private void printABC(String name, int target) {
        for (int i = 0; i < times;) {
            synchronized (OBJECT_LOCK) {
                while (number % 3 != target) {
                    try {
                        OBJECT_LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                number ++;
                i ++;
                System.out.println(name);
                OBJECT_LOCK.notifyAll();
            }
        }
    }

}

