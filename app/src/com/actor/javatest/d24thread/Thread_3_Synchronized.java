package com.actor.javatest.d24thread;

/**
 * description: 同步代码块, 同步方法
 * author     : 李大发
 * date       : 2020/7/25 on 17:58
 */
public class Thread_3_Synchronized {

    public static void main(String[] args) {
        /**
         * 24.16_多线程(同步代码块)
         * 1.什么情况下需要同步
         *  当多线程并发, 有多段代码同时执行时, 我们希望某一段代码执行的过程中CPU不要切换到其他线程工作. 这时就需要同步.
         *  如果两段代码是同步的, 那么同一时间只能执行一段, 在一段代码没执行结束之前, 不会执行另外一段代码.
         * 2.同步代码块
         *  使用synchronized关键字加上一个锁对象来定义一段代码, 这就叫同步代码块
         *  多个同步代码块如果使用相同的锁对象, 那么他们就是同步的
         */
//        test1();

        /**
         * 24.17_多线程(同步方法)
         * 使用synchronized关键字修饰一个方法, 该方法中所有的代码都是同步的
         *  非静态同步函数的锁是:this
         *  静态的同步函数的锁是:字节码对象
         *
         * 非静态的同步方法的锁对象是神马?
         *  答:非静态的同步方法的锁对象是this
         *  静态的同步方法的锁对象是什么?
         *  是该类的字节码对象
         */
        test2();
    }


    /**
     * 24.16_多线程(同步代码块)
     * 1.什么情况下需要同步
     *  当多线程并发, 有多段代码同时执行时, 我们希望某一段代码执行的过程中CPU不要切换到其他线程工作. 这时就需要同步.
     *  如果两段代码是同步的, 那么同一时间只能执行一段, 在一段代码没执行结束之前, 不会执行另外一段代码.
     * 2.同步代码块
     *  使用synchronized关键字加上一个锁对象来定义一段代码, 这就叫同步代码块
     *  多个同步代码块如果使用"相同的锁"对象, 那么他们就是同步的
     */
    private static void test1() {
        final Printer p = new Printer();
        new Thread() {
            public void run() {
                while(true) {
                    p.print1();
                }
            }
        }.start();

        new Thread() {
            public void run() {
                while(true) {
                    p.print2();
                }
            }
        }.start();
    }
    static class Printer {
        final Object lock = new Object();
        final Class lock2 = Printer.class;
        private void print1() {
            synchronized(lock/*2*/) {//锁对象可以是任意对象,但是被锁的代码需要保证是同一把锁,不能用匿名对象
                System.out.print("天");
                System.out.print("王");
                System.out.print("盖");
                System.out.print("地");
                System.out.println("虎");
            }
        }
        private void print2() {
            synchronized(lock/*2*/) {
                System.out.print("提");
                System.out.print("莫");
                System.out.print("一");
                System.out.print("米");
                System.out.println("五-----------------------------------");
            }
        }
    }


    /**
     * 24.17_多线程(同步方法)
     * 使用synchronized关键字修饰一个方法, 该方法中所有的代码都是同步的
     *  非静态同步函数的锁是: this(调用该方法的对象)
     *  静态的同步函数的锁是: 该方法所在类的字节码对象
     */
    private static void test2() {
        final Printer2 p = new Printer2();
        new Thread() {
            public void run() {
                while(true) {
//                    p.print1();
                    p.print3();
                }
            }
        }.start();
        new Thread() {
            public void run() {
                while(true) {
//                    p.print2();
                    p.print5();
                }
            }
        }.start();
    }
    static class Printer2 {
        private synchronized void print1() {//锁对象是 this
            System.out.print("天");
            System.out.print("王");
            System.out.print("盖");
            System.out.print("地");
            System.out.println("虎");
        }

        private void print2() {
            synchronized(this) {        //和 print1 一起调用, 证明 非static 方法的锁对象是 this
//                String s = this.toString();//com.actor.javatest.d24thread.Thread_3_Synchronized$Printer2@4a62d7f0
                System.out.print("宝");
                System.out.print("塔");
                System.out.print("镇");
                System.out.print("河");
                System.out.println("妖-----------------------------------");
            }
        }
        private static synchronized void print3() {//static方法, 锁对象是 所在类的字节码对象
            System.out.print("天");
            System.out.print("王");
            System.out.print("盖");
            System.out.print("地");
            System.out.println("虎");
        }
        //不写static 一样效果, 因为锁和print3 是一样的.
        private static void print5() {
            synchronized(Printer2.class) {//和 print3 一起调用, 证明 static 方法的锁对象是 所在类的字节码对象
                System.out.print("提");
                System.out.print("莫");
                System.out.print("一");
                System.out.print("米");
                System.out.println("五-----------------------------------");
            }
        }
    }
}

