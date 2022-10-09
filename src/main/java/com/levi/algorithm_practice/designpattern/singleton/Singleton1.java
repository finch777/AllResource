package com.levi.algorithm_practice.designpattern.singleton;

/**
 * 单例模式
 *
 * 懒汉式: 需要的时候才去检查是否已经有了，如果没有再创建；有两个版本，即线程安全和线程不安全，区别在于是否加同步锁
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
 * 饿汉式
 * 实例在初始化的时候就已经建好了，不管你有没有用到，都先建好了再说。好处是没有线程安全的问题，坏处是浪费内存空间
 * 因为创建实例的动作只会执行一次，那就是在类加载的时候
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
 *双检锁，又叫双重校验锁，综合了懒汉式和饿汉式两者的优缺点整合而成。看上面代码实现中，
 * 特点是在synchronized关键字内外都加了一层 if 条件判断，这样既保证了线程安全，又比直接上锁提高了执行效率，还节省了内存空间
 */
class Singleton3 {

    private static Singleton3 single;
    private Singleton3(){

    }
    public static Singleton3 getInstance(){

        if (single == null){
            synchronized(Singleton3.class) {
                if (single == null){  //问：这里为什么要再加一次判断
                    single = new Singleton3();
                }
            }
        }
        return single;
    }
}


/**
 * 静态内部类
 * 静态内部类的方式效果类似双检锁，但实现更简单。但这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用
 * 注意两个final
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
 * 枚举
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

//直接通过Singleton.INSTANCE.doSomething()的方式调用即可。方便、简洁又安全。



/**
 * 枚举的方式是比较少见的一种实现方式，但是看上面的代码实现，却更简洁清晰。并且她还自动支持序列化机制，绝对防止多次实例化。
 * 总结下，一般情况下，懒汉式（包含线程安全和线程不安全梁总方式）都比较少用；饿汉式和双检锁都可以使用，
 * 可根据具体情况自主选择；在要明确实现 lazy loading 效果时，可以考虑静态内部类的实现方式；若涉及到反序列化创建对象时，大家也可以尝试使用枚举方式。
 */










