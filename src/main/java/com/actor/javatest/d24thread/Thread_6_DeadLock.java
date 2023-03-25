package com.actor.javatest.d24thread;

/**
 * description: 死锁
 * author     : 李大发
 * date       : 2020/7/27 on 15:45
 */
public class Thread_6_DeadLock {

    public static void main(String[] args) {
        test1();
    }

    /**
     * 24.20_多线程(死锁)
     * 多线程同步的时候, 如果同步代码嵌套, 使用相同锁, 就有可能出现死锁
     *      尽量不要嵌套使用
     *
     * 例: 一群哲学家做一个圆型饭桌上, 每人发一根筷子, 需要说服旁边的人给自己另外一根筷子.
     * 打印结果最后2行:
     *      哲学家1 手里有 筷子左 等待 筷子右
     *      哲学家2 手里有 筷子右 等待 筷子左
     */
    public static void test1() {
        String lock1 = "筷子左";
        String lock2 = "筷子右";
        new Thread("哲学家1") {
            public void run() {
                while (true) {
                    synchronized (lock1) {
                        System.out.printf("%s 手里有 %s 等待 %s%n", getName(), lock1, lock2);
                        synchronized (lock2) {
                            System.out.printf("%s 获取 %s 开始吃饭%n", getName(), lock2);
                        }
                    }
                }
            }
        }.start();
        new Thread("哲学家2") {
            public void run() {
                while (true) {
                    synchronized (lock2) {
                        System.out.printf("%s 手里有 %s 等待 %s%n", getName(), lock2, lock1);
                        synchronized (lock1) {
                            System.out.printf("%s 获取 %s 开始吃饭%n", getName(), lock1);
                        }
                    }
                }
            }
        }.start();
    }
}
