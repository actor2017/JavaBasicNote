package com.actor.javatest.d24thread;

/**
 * description: 线程的一些方法
 * author     : 李大发
 * date       : 2020/7/24 on 14:57
 */
public class Thread_2_Method {

    public static void main(String[] args) {
//        test1();//24.09_多线程(获取名字和设置名字), th.getName();  th.setName("name");
//        test2();//24.10_多线程(获取当前线程的对象), Thread.currentThread();
//        test3();//24.11_多线程(休眠线程),          Thread.sleep(1_000L);

//        test4();//24.12_多线程(守护线程),          th.setDaemon(boolean on);
                //非守护线程死了后, 守护线程就死掉
                //非守护线程: 車
                //守护线程:   马, 相, 士...

//        test5();//24.13_多线程(加入线程)
                //th.join(), 参数=0, 当前线程暂停, 等待指定的线程"全部执行结束"后, 当前线程再继续
                //th.join(long millis), 可以等待指定的毫秒之后继续"交替执行"(不是等待指定线程执行完再执行)

//        test6();//24.14_多线程(礼让线程), Thread.yield();//让出CPU, 效果不明显

//        test7();//24.15_多线程(设置线程的优先级), th.setPriority(x);
                //默认: Thread.NORM_PRIORITY 5, 取值范围: [Thread.MIN_PRIORITY, Thread.MAX_PRIORITY] //[1-10]
                //只是设置执行的优先级, 但还是交替执行
    }


    /**
     * 24.09_多线程(获取名字和设置名字)
     * 1.获取名字
     *  通过 th.getName();方法获取线程对象的名字, 默认名称: Thread-0, Thread-1
     * 2.设置名字
     *  通过构造函数可以传入String类型的名字
     *  通过 th.setName(String);方法可以设置线程对象的名字
     */
    private static void test1() {
        new Thread("芙蓉姐姐") {    //1.通过构造方法给name赋值
            public void run() {
                System.out.printf("ThreadName1: %s%n", this.getName());//
            }
        }.start();

        Thread thread = new Thread() {
            public void run() {
                this.setName("凤姐"); //2.通过 setName() 方法
                System.out.printf("ThreadName2: %s%n", this.getName());
            }
        };
//        thread.setName("凤姐");       //2.通过 setName() 方法
        thread.start();
    }


    /**
     * Thread.currentThread()返回当前正在执行线程的引用
     */
    private static void test2() {
        new Thread() {
            public void run() {
                System.out.printf("ThreadName1: %s%n", getName());
            }
        }.start();

        new Thread(new Runnable() {
            public void run() {
                System.out.printf("ThreadName2: %s%n", Thread.currentThread().getName());
            }
        }).start();

        Thread.currentThread().setName("我是主线程");    //获取主函数线程的引用,并改名字, 默认名称: main
        System.out.printf("ThreadName3: %s%n", Thread.currentThread().getName());//获取主函数线程的引用,并改名字
    }


    /**
     * 24.11_多线程(休眠线程)
     * Thread.sleep(毫秒,纳秒), 控制当前线程休眠若干毫秒1秒= 1000毫秒 1秒 = 1000 * 1000 * 1000纳秒 1000000000
     *
     * 主线程运行完了后, 再运行子线程
     */
    private static void test3() {
        //主线程
        for(int i = 20; i >= 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("倒计时第 %d 秒%n", i);
        }

        new Thread() {
            public void run() {
                for(int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + "...aaaaaaaaaa");
                }
            }
        }.start();

        new Thread() {
            public void run() {
                for(int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(getName() + "...bb");
                }
            }
        }.start();
    }


    /**
     * 24.12_多线程(守护线程)
     * th.setDaemon(), 设置一个线程为守护线程, 该线程不会单独执行, 当其他非守护线程都执行结束后, 自动退出
     *
     * 非守护线程死了后, 守护线程就死掉
     * 非守护线程: 車
     * 守护线程:   马, 相, 士...
     */
    private static void test4() {
        Thread t1 = new Thread() {
            public void run() {
                for(int i = 0; i < 2; i++) {
                    System.out.println(getName() + "...aaaaaaaaaaaaaaaaaaaa");
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for(int i = 0; i < 50; i++) {
                    System.out.println(getName() + "...bb");
                }
            }
        };
        t2.setDaemon(true);         //设置为守护线程
        t1.start();
        t2.start();
    }


    /**
     * 24.13_多线程(加入线程)
     * th.join(), 参数=0, 当前线程暂停, 等待指定的线程"全部执行结束"后, 当前线程再继续
     * th.join(long millis), 可以等待指定的毫秒之后继续"交替执行"(不是等待指定线程执行完再执行)
     */
    private static void test5() {
        final Thread t1 = new Thread() {
            public void run() {
                for(int i = 0; i < 100; i++) {
                    System.out.println(getName() + "...aaaaaaaaaaaaa");
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                for(int i = 0; i < 10; i++) {
                    if(i == 2) {
                        try {
//                            t1.join();//参数=0
                            t1.join(1L);//插队指定的时间,过了指定时间后,两条线程交替执行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(getName() + "...bb");
                }
            }
        };
        t1.start();
        t2.start();
    }


    /**
     * 24.14_多线程(礼让线程), 效果不明显
     */
    private static void test6() {
        new MyThread("aaaaaaaaaaaaa").start();
        new MyThread("bb").start();


    }
    static class MyThread extends Thread {
        public MyThread(String name) {
            super(name);
        }
        public void run() {
            for(int i = 1; i <= 1000; i++) {
                if(i % 10 == 0) {
                    Thread.yield(); //10的整数时, 让出CPU
                }
                System.out.println(getName() + "..." + i);
            }
        }
    }


    /**
     * 24.15_多线程(设置线程的优先级), th.setPriority(x);
     * 默认: Thread.NORM_PRIORITY 5, 取值范围: [Thread.MIN_PRIORITY, Thread.MAX_PRIORITY] //[1-10]
     *
     * 只是设置执行的优先级, 但还是交替执行
     */
    private static void test7() {
        Thread t1 = new Thread(){
            public void run() {
                for(int i = 0; i < 100; i++) {
                    System.out.println(getName() + "...aaaaaaaaa" );
                }
            }
        };
        Thread t2 = new Thread(){
            public void run() {
                for(int i = 0; i < 100; i++) {
                    System.out.println(getName() + "...bb" );
                }
            }
        };
        t1.setPriority(Thread.MIN_PRIORITY);    //1, 设置最小的线程优先级
        t2.setPriority(Thread.MAX_PRIORITY);    //10, 设置最大的线程优先级
        t1.start();
        t2.start();
    }
}
