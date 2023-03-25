package com.actor.javatest.d25Thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * description: 25.07_多线程(JDK1.5的新特性互斥锁)
 * Reentrant 互斥锁, 替换掉 synchronized, "依次唤醒"
 *
 * 1.同步
 *   使用ReentrantLock 类的 lock() 和 unlock() 方法进行同步
 *
 * 2.通信
 *   使用 ReentrantLock 类的 newCondition() 方法可以获取 Condition 对象
 *   需要等待的时候使用 Condition 的 await() 方法, 唤醒的时候用 signal() 方法
 *   不同的线程使用不同的 Condition, 这样就能区分唤醒的时候找哪个线程了
 *
 * ReentrantLock:   /riː'entrənt/ Reentrant Lock 可重入 锁
 * Condition:       /kənˈdɪʃn/ 条件, 情况, 状况, 状态
 *
 * ReentrantLock implements Lock
 *
 * 本例效果: 3个线程 依次唤醒, 依次执行
 *
 * author     : 李大发
 * date       : 2020/8/1 on 21:01
 */
public class _6ReentrantLock_lock_unlock {

    public static void main(String[] args) {
        final Printer3 p = new Printer3();

        new Thread() {//线程1
            public void run() {
                while (true) {
                    try {
                        p.print1();
                    } catch (InterruptedException ignored) { }
                }
            }
        }.start();

        new Thread() {//线程2
            public void run() {
                while (true) {
                    try {
                        p.print2();
                    } catch (InterruptedException ignored) { }
                }
            }
        }.start();

        new Thread() {//线程3
            public void run() {
                while (true) {
                    try {
                        p.print3();
                    } catch (InterruptedException e) { }
                }
            }
        }.start();
    }
}

class Printer3 {
    //ReentrantLock implements Lock
    private ReentrantLock r = new ReentrantLock();
    private Condition c1 = r.newCondition();
    private Condition c2 = r.newCondition();
    private Condition c3 = r.newCondition();

    private int flag = 1;
    public void print1() throws InterruptedException {
//        synchronized(this) {          //ReentrantLock 替换掉 synchronized
        r.lock();                       //获取锁
        if (flag != 1) {                //由于线程是交替唤醒, 所以这儿不再需要 while
//            this.wait();
            c1.await();                 //c1等待
        }
        System.out.print("天");
        System.out.print("王");
        System.out.print("盖");
        System.out.print("地");
        System.out.println("虎");
        flag = 2;
        c2.signal();                    //唤醒c2
        r.unlock();                     //释放锁
//        this.notifyAll();             //不用再全部唤醒
//        }
    }

    public void print2() throws InterruptedException {
        r.lock();
        if (flag != 2) {
//            this.wait();
            c2.await();
        }
        System.out.print("提");
        System.out.print("莫");
        System.out.print("一");
        System.out.print("米");
        System.out.println("五-----------------------------------");
        flag = 3;
        c3.signal();
        r.unlock();
    }

    public void print3() throws InterruptedException {
        r.lock();
        if (flag != 3) {
//            this.wait();
            c3.await();
        }
        System.out.print("宝");
        System.out.print("塔");
        System.out.print("镇");
        System.out.print("河");
        System.out.println("妖------------------");
        flag = 1;
        c1.signal();
        r.unlock();
    }
}
