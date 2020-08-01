package com.actor.javatest.d25Thread;

/**
 * description: object.notifyAll();
 *              25.05_多线程(三个或三个以上间的线程通信)
 *              多线程通信较难, 建议查资料看视频
 *
 *              notifyAll() 叫醒所有线程, 不科学
 *                          JDK5之前无法唤醒指定的一个线程, 请看下一个示例: ReentrantLock
 *
 * 本例效果: 3个线程 依次执行
 *
 * author     : 李大发
 * date       : 2020/8/1 on 20:34
 */
public class _5_notifyAll {

    public static void main(String[] args) {
        Printer2 p = new Printer2();

        new Thread() {//线程1
            public void run() {
                while(true) {
                    try {
                        p.print1();
                    } catch (InterruptedException ignore) { }
                }
            }
        }.start();

        new Thread() {//线程2
            public void run() {
                while(true) {
                    try {
                        p.print2();
                    } catch (InterruptedException ignored) { }
                }
            }
        }.start();

        new Thread() {//线程3
            public void run() {
                while(true) {
                    try {
                        p.print3();
                    } catch (InterruptedException ignored) { }
                }
            }
        }.start();
    }
}

class Printer2 {
    private int flag = 1;
    public void print1() throws InterruptedException {
        synchronized(this) {
            while(flag != 1) {              //这儿是 while
                this.wait();                //当前线程等待
            }
            System.out.print("天");
            System.out.print("王");
            System.out.print("盖");
            System.out.print("地");
            System.out.println("虎");
            flag = 2;
//            this.notify();                  //随机唤醒单个等待的线程
            this.notifyAll();
        }
    }
    public void print2() throws InterruptedException {
        synchronized(this) {
            while(flag != 2) {
                this.wait();
            }
            System.out.print("提");
            System.out.print("莫");
            System.out.print("一");
            System.out.print("米");
            System.out.println("五-----------------------------------");
            flag = 3;
//            this.notify();
            this.notifyAll();
        }
    }
    public void print3() throws InterruptedException {
        synchronized(this) {
            while(flag != 3) {
                this.wait();
            }
            System.out.print("宝");
            System.out.print("塔");
            System.out.print("镇");
            System.out.print("河");
            System.out.println("妖------------------");
            flag = 1;
//            this.notify();
            this.notifyAll();
        }
    }
}
