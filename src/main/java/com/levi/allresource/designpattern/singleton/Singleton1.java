package com.levi.allresource.designpattern.singleton;

/**
 * ����ģʽ
 *
 * ����ʽ: ��Ҫ��ʱ���ȥ����Ƿ��Ѿ����ˣ����û���ٴ������������汾�����̰߳�ȫ���̲߳���ȫ�����������Ƿ��ͬ����
 */

public class Singleton1 {

    private static Singleton1 single;
    private Singleton1(){
    };
    public static Singleton1 getInstance(){
        if (single == null) {
            single = new Singleton1();
        }
        return single;
    }
}


/**
 * ����ʽ
 * ʵ���ڳ�ʼ����ʱ����Ѿ������ˣ���������û���õ������Ƚ�������˵���ô���û���̰߳�ȫ�����⣬�������˷��ڴ�ռ�
 * ��Ϊ����ʵ���Ķ���ֻ��ִ��һ�Σ��Ǿ���������ص�ʱ��
 */
class Singleton2{
    private static Singleton2 single = new Singleton2();
    private Singleton2(){

    }
    public static Singleton2 getInstance(){
        return single;
    }
}


/**
 *˫�������ֽ�˫��У�������ۺ�������ʽ�Ͷ���ʽ���ߵ���ȱ�����϶��ɡ����������ʵ���У�
 * �ص�����synchronized�ؼ������ⶼ����һ�� if �����жϣ������ȱ�֤���̰߳�ȫ���ֱ�ֱ�����������ִ��Ч�ʣ�����ʡ���ڴ�ռ�
 */
class Singleton3 {

    private static Singleton3 single;
    private Singleton3(){

    }
    public static Singleton3 getInstance(){

        if (single == null){
            synchronized(Singleton3.class) {
                if (single == null){  //�ʣ�����ΪʲôҪ�ټ�һ���ж�
                    single = new Singleton3();
                }
            }
        }
        return single;
    }
}


/**
 * ��̬�ڲ���
 * ��̬�ڲ���ķ�ʽЧ������˫��������ʵ�ָ��򵥡������ַ�ʽֻ�����ھ�̬��������˫������ʽ����ʵ������Ҫ�ӳٳ�ʼ��ʱʹ��
 * ע������final
 */
class Singleton4{
    private static class SingletonHolder{
        private static final Singleton4 INSTANCE = new Singleton4();
    }
    private Singleton4(){
    }
    public static final Singleton4 getInstance(){
        return SingletonHolder.INSTANCE;
    }
}


/**
 * ö��
 */
enum Singleton5{

    INSTANCE;
    public void anyMethod(){

    }

}




enum Singleton {

    INSTANCE;

    public void doSomething() {
        System.out.println("doSomething");
    }

}

class Main {

    public static void main(String[] args) {
        Singleton.INSTANCE.doSomething();
    }

}

//ֱ��ͨ��Singleton.INSTANCE.doSomething()�ķ�ʽ���ü��ɡ����㡢����ְ�ȫ��



/**
 * ö�ٵķ�ʽ�ǱȽ��ټ���һ��ʵ�ַ�ʽ�����ǿ�����Ĵ���ʵ�֣�ȴ��������������������Զ�֧�����л����ƣ����Է�ֹ���ʵ������
 * �ܽ��£�һ������£�����ʽ�������̰߳�ȫ���̲߳���ȫ���ܷ�ʽ�����Ƚ����ã�����ʽ��˫����������ʹ�ã�
 * �ɸ��ݾ����������ѡ����Ҫ��ȷʵ�� lazy loading Ч��ʱ�����Կ��Ǿ�̬�ڲ����ʵ�ַ�ʽ�����漰�������л���������ʱ�����Ҳ���Գ���ʹ��ö�ٷ�ʽ��
 */










