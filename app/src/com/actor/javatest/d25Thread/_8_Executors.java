package com.actor.javatest.d25Thread;

import java.util.concurrent.*;

/**
 * description: 25.10_多线程(线程池的概述和使用)
 * A:线程池概述
 *   程序启动一个新线程成本是比较高的，因为它涉及到要与操作系统进行交互。而使用线程池可以很好的提高性能，
 *   尤其是当程序中要创建大量生存期很短的线程时，更应该考虑使用线程池。线程池里的每一个线程代码结束后，
 *   并不会死亡，而是再次回到线程池中成为空闲状态，等待下一个对象来使用。
 *
 *   在JDK5之前，我们必须手动实现自己的线程池，从JDK5开始，Java内置支持线程池
 *
 * B:内置线程池的使用概述
 *   JDK5新增了一个 Executors 工厂类来产生线程池，有如下几个方法
 *   public static ExecutorService newFixedThreadPool(int nThreads)
 *   public static ExecutorService newSingleThreadExecutor()    //线程池里只有1条线程
 *
 *   ExecutorService: 线程池
 *   可以执行 Runnable 对象或者 Callable 对象代表的线程。它提供了如下方法
 *   Future<?> submit(Runnable task)
 *   <T> Future<T> submit(Callable<T> task)
 *
 * 使用步骤：
 *   创建线程池对象
 *   创建Runnable实例
 *   提交Runnable实例
 *   关闭线程池
 *
 * author     : 李大发
 * date       : 2020/8/1 on 22:10
 */
public class _8_Executors {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    //MyRunnable
    private static void test1() {

        ExecutorService pool = Executors.newFixedThreadPool(5);
        pool.submit(new MyRunnable());//提交Runnable实例
        pool.submit(new MyRunnable());
        pool.shutdown();              //关闭线程池(关闭后不能在提交Runnable?)

        boolean shutdown = pool.isShutdown();
    }

    /**
     * 25.11_多线程(多线程程序实现的方式3)
     * Callable 可计算
     * 好处：
     *   可以有返回值
     *   可以抛出异常
     *
     * 弊端：
     *   代码比较复杂，所以一般不用
     */
    private static void test2() {
        ExecutorService pool = Executors.newFixedThreadPool(5);
        Future<Integer> future1 = pool.submit(new MyCallable(100));//提交Callable实例
        Future<Integer> future2 = pool.submit(new MyCallable(200));

        try {
            Integer i1 = future1.get(); //等待计算完成, 获取结果
            Integer i2 = future2.get();
            System.out.println(i1);     //5050
            System.out.println(i2);     //20100
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        pool.shutdown();              //关闭线程池(关闭后不能在提交Callable?)
    }
}
class MyCallable implements Callable<Integer> {
    private int num;
    public MyCallable(int num) {
        this.num = num;
    }
    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= num; sum += i++);//1+2+3+4+5+...
        return sum;
    }
}