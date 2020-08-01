package com.actor.javatest.d25Thread;

/**
 * description: 25.08_多线程(线程组的概述和使用)
 *  ThreadGroup 线程组，对一批线程进行分类管理
 *
 *  默认情况下，所有的线程都属于主线程组。
 *   public final ThreadGroup getThreadGroup()//通过线程对象获取他所属于的组
 *   public final String getName()//通过线程组对象获取他组的名字
 *
 *  我们也可以给线程设置分组
 *   1,ThreadGroup(String name) 创建线程组对象并给其赋值名字
 *   2,创建线程对象
 *   3,Thread(ThreadGroup group, Runnable target, String name)
 *   4,设置整组的优先级或者守护线程
 *
 * author     : 李大发
 * date       : 2020/8/1 on 21:31
 */
public class _7_ThreadGroup {
    public static void main(String[] args) {
//        test1();
        test2();
    }

    //线程组的使用,默认是主线程组
    private static void test1() {
        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(mr, "张三");
        Thread t2 = new Thread(mr, "李四");

        ThreadGroup tg1 = t1.getThreadGroup();//获取线程组
        ThreadGroup tg2 = t2.getThreadGroup();

        String name1 = tg1.getName();//线程组里面的方法
        String name2 = tg2.getName();
        System.out.println(name1);//main    (线程默认情况下属于main线程组)
        System.out.println(name2);//main
        System.out.println(Thread.currentThread().getThreadGroup().getName());//main
    }

    //自己设定线程组
    private static void test2() {
        ThreadGroup tg = new ThreadGroup("这是一个新的组");//组名

        MyRunnable mr = new MyRunnable();
        Thread t1 = new Thread(tg, mr, "线程名张三");//将这个线程放进这个组
        Thread t2 = new Thread(tg, mr, "线程名李四");

        System.out.println(t1.getThreadGroup().getName());//这是一个新的组
        System.out.println(t2.getThreadGroup().getName());//这是一个新的组

        //通过线程组设置后台线程，表示该组的"所有线程"都是守护线程
        tg.setDaemon(true);
    }
}
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for(int i = 0; i < 1000; i++) {
            System.out.println(Thread.currentThread().getName() + "...." + i);
        }
    }
}