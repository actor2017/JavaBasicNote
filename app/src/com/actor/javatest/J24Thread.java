package com.actor.javatest;

/**
 * description: 线程
 * author     : 李大发
 * date       : 2020/7/23 on 15:29
 *
 * 今日内容
 *  多线程的概述
 *  jvm运行的原理
 *  实现多线程的两种方式
 *  线程的控制
 *  同步机制
 *
 * 24.01_多线程(多线程的引入)
 * 1.什么是线程
 *  线程是程序执行的一条路径, 一个进程中可以包含多条线程
 *  多线程并发执行可以提高程序的效率, 可以同时完成多项工作
 *
 * 24.02_多线程(多线程并行和并发的区别)
 *  并行就是两个任务同时运行，就是甲任务进行的同时，乙任务也在进行。(需要多核CPU)
 *  并发是指两个任务都请求运行，而处理器只能按受一个任务，就把这两个任务安排轮流进行，由于时间间隔较短，使人感觉两个任务都在运行。
 *
 * 24.03_多线程(Java程序运行原理和JVM的启动是多线程的吗)
 *  A:Java程序运行原理
 *      Java命令会启动java虚拟机，启动JVM，等于启动了一个应用程序，也就是启动了一个进程。
 *      该进程会自动启动一个 “主线程” ，然后主线程去调用某个类的 main 方法。
 *  B:JVM的启动是多线程的吗
 *      JVM启动至少启动了垃圾回收线程和主线程，所以是多线程的。
 */
public class J24Thread {


    public static void main(String[] args) {
//        test1();//证明jvm是多线程的, 结果: 交叉打印
//        test2();//24.04_多线程(多线程程序实现的方式1), 继承Thread
//        test3();//24.05_多线程(多线程程序实现的方式2), 实现Runnable
        test4();//24.08_多线程(匿名内部类实现线程的两种方式)
    }

    /**
     * 证明jvm是多线程的, 结果: 交叉打印
     */
    private static void test1() {
        for(int i = 0; i < 1000000; i++) {
            new Demo();
        }
        for(int i = 0; i < 100000; i++) {
            System.out.println("我是主线程的执行代码");
        }
    }
    static class Demo {
        @Override
        public void finalize() {
            System.out.println("垃圾被清扫了");
        }
    }


    /**
     * 24.04_多线程(多线程程序实现的方式1), 结果: 交替打印
     * 1.继承Thread
     *  定义类继承Thread
     *  重写run方法
     *  把新线程要做的事写在run方法中
     *  创建线程对象
     *  开启新线程, 内部会自动执行run方法
     */
    private static void test2() {
        MyThread mt = new MyThread();   //4,创建Thread类的子类对象
        mt.start();                     //5,开启线程

        for(int i = 0; i < 100; i++) {
            System.out.println("bb");
        }
    }
    static class MyThread extends Thread {  //1,继承Thread
        @Override
        public void run() {                 //2,重写run方法
            for(int i = 0; i < 100; i++) { //3,将要执行的代码写在run方法中
                System.out.println("aaaaaaaaaaaa");
            }
        }
    }


    /**
     * 24.05_多线程(多线程程序实现的方式2)
     * 实现Runnable
     *  定义类实现Runnable接口
     *  重写run方法
     *  把新线程要做的事写在run方法中
     *  创建自定义的Runnable的子类对象
     *  创建Thread对象, 传入Runnable
     *  调用start()开启新线程, 内部会自动调用Runnable的run()方法
     */
    private static void test3() {
        MyRunnable mr = new MyRunnable();   //4,创建Runnable的子类对象
        Thread t = new Thread(mr);          //5,将其当作参数传递给Thread的构造函数
        t.start();                          //6,开启线程
        for(int i = 0; i < 100; i++) {
            System.out.println("bb");
        }
    }
    static class MyRunnable implements Runnable {//1,定义一个类实现Runnable
        @Override
        public void run() {                     //2,重写run方法
            for(int i = 0; i < 100; i++) {      //3,将要执行的代码写在run方法中
                System.out.println("aaaaaaaaaaaa");
            }
        }
    }


    /**
     * 24.08_多线程(匿名内部类实现线程的两种方式)
     */
    private static void test4() {
        new Thread() {                          //1, new Thread
            @Override
            public void run() {                 //2,重写 Thread.run() 方法
                for(int i = 0; i < 100; i++) {  //3,将要执行的代码写在run方法中
                    System.out.println("aaaaaaaaaaaaaa");
                }
            }
        }.start();                              //4,开启线程

        new Thread(new Runnable() {             //1,将Runnable的子类对象传递给Thread的构造方法
            @Override
            public void run() {                 //2,重写 Runnable.run() 方法
                for(int i = 0; i < 100; i++) {  //3,将要执行的代码写在run方法中
                    System.out.println("bb");
                }
            }
        }).start();                             //4,开启线程
    }
}