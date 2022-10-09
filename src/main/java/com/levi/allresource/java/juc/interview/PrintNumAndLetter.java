package com.levi.allresource.java.juc.interview;


/*
* �����߳̽����ӡ���ֺ���ĸ
* ���磺A1B2C3D4......Y25Z26
*
* ���ֻ��һ��������ģ��ʹ�ӡABCһ����������ÿ���̵߳��߼���Ϊ��һ���������ӡĿ�겻һ������������Ե�����������
* �������������A��ӡ5�Σ�B��ӡ10�Σ�C��ӡ15���������󣬾���ɲ����ˣ�ֻ��д��ͬ�ķ���ȥʵ�֡���ʵҲ�����������
* ģ�������Ѳ����ĳɴ�AAAAA, BBBBBBBBBB,������������
* */
public class PrintNumAndLetter {

    private int times;
    private int number = 1;
    private static Object LOCK = new Object();
    public PrintNumAndLetter(int times) {
        this.times = times;
    }

    public static void main(String[] args) {
        PrintNumAndLetter printNumAndLetter = new PrintNumAndLetter(20);
        new Thread(() -> printNumAndLetter.printNL('A', 1)).start();

        new Thread(() -> printNumAndLetter.printNL('1', 0)).start();

    }

    /*
    * ��������Ĳ�����ʾÿ���̲߳�ͬ�Ĵ����Ϊ������һ���߳���numberΪ������ʱ���ӡ����һ����numberΪż����ʱ���ӡ
    * �����target���Ǳ�ʶ����̵߳����Ǹ�ʲôʱ���ӡ�ģ�
    * ��c��ʶÿ���߳�Ҫ��ӡ�Ĳ�ͬ�Ķ���
    *
    * */
    private void printNL(char c, int target) {
        for (int i = 0; i < times; ) {
            synchronized (LOCK) {
                while ( (number & 1) != target) {
                    try {
                        LOCK.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println((char)(c + i % 9));
                number ++;
                i ++;
                LOCK.notifyAll();
            }
        }
    }

}
