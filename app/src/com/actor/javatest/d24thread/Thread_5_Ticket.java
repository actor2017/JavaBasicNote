package com.actor.javatest.d24thread;

/**
 * description: 火车站
 * author     : 李大发
 * date       : 2020/7/27 on 11:32
 */
public class Thread_5_Ticket {

    public static void main(String[] args) {

        /**
         * 24.18_多线程(线程安全问题), TicketsSeller extends Thread
         */
//        test1();

        /**
         * 24.19_多线程(火车站卖票的例子用实现Runnable接口), MyTicket implements Runnable
         */
        test2();
    }


    /**
     * 24.18_多线程(线程安全问题)
     * * 多线程并发操作同一数据时, 就有可能出现线程安全问题
     * * 使用同步技术可以解决这种问题, 把操作数据的代码进行同步, 不要多个线程一起操作
     *
     * 需求:铁路售票,一共100张,通过四个窗口卖完.
     * 多线程并发操作同一数据时, 就有可能出现线程安全问题
     */
    private static void test1() {
        new TicketsSeller("窗口1").start();
        new TicketsSeller("窗口2").start();
        new TicketsSeller("窗口3").start();
        new TicketsSeller("窗口4").start();
    }
    static class TicketsSeller extends Thread {
        private static int tickets = 100;       //加static, 4个线程共用一个成员变量
//        static Object lock = new Object();       //加static, 同一把锁
        private TicketsSeller(String name) {
            super(name);
        }
        @Override
        public void run() {
            while(true) {
                synchronized(TicketsSeller.class/*lock*/) {
                    if(tickets == 0) break;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("%s: 这是第 %03d 号票%n", getName(), tickets--);
                }
            }
        }
    }


    /**
     * 24.19_多线程(火车站卖票的例子用实现Runnable接口)
     */
    private static void test2() {
        MyTicket mt = new MyTicket();
        new Thread(mt, "窗口1").start();
        new Thread(mt, "窗口2").start();
        new Thread(mt, "窗口3").start();
        new Thread(mt, "窗口4").start();
    }
    static class MyTicket implements Runnable {
        private int tickets = 1000;              //1. 可不写成 static, 因为只创建了1份对象
        @Override
        public void run() {
            while(true) {
                synchronized(/*MyTicket.class*/this) {//2. 这儿可以用this, 因为只创建了一个Runnable对象
                    if(tickets == 0) break;
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.printf("%s: 这是第 %03d 号票%n", Thread.currentThread().getName(), tickets--);
                }
            }
        }
    }
}