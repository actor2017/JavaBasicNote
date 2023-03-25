package com.actor.javatest.d25Thread;

/**
 * description: 线程通信, 等待唤醒机制
 * 1.什么时候需要通信
 *   多个线程并发执行时, 在默认情况下CPU是随机切换线程的
 *   如果我们希望他们有规律的执行, 就可以使用通信, 例如每个线程执行一次打印
 *  2.怎么通信(Object方法)
 *   wait();        当前线程等待
 *   wait(long timeout); timeout时间到后才开始等待
 *   notify();      随机唤醒1个等待的线程
 *   notifyAll();   唤醒全部等待的线程
 *
 *  注意:
 *   这两个方法必须在"同步代码"中执行, 并且使用"同步锁对象"来调用
 *
 *  Tips:
 *   1.在同步代码块中,用哪个对象锁, 就用哪个对象调用wait方法
 *
 *   2.为什么wait方法和notify方法定义在Object这个类中?
 *     锁对象可以是任意对象,那么任意对象对应的类都是Object类的子类,
 *     也就是Object是所有的类的基类,所以将将方法定义在Object这个类中就会让任意对象
 *     对其调用所以wait方法和notify方法需要定义在Object这个类中
 *
 *   3.sleep方法和wait方法的区别?
 *     1.sleep在同步代码块或者同步函数中, 不释放锁
 *       wait在同步代码块或者同步函数中, "释放锁"
 *     2.sleep方法必须传入参数,参数其实就是时间,时间到了自动醒来
 *       wait方法可以传入参数,也可以不传入参数,
 *       如果给wait方法传入时间参数,用法与sleep相似,时间到后才开始等待(通常用的都是没有参数的wait方法)
 *
 * author     : 李大发
 * date       : 2020/8/1 on 20:03
 */
public class _4_wait_notify {

    public static void main(String[] args) {
        Printer p = new Printer();

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
    }
}

class Printer {
    private int flag = 1;
    public void print1() throws InterruptedException {
        synchronized(this) {
            if(flag != 1) {                 //这儿是 if
                this.wait();                //当前线程等待, this -> Printer对象的
            }
            System.out.print("天");
            System.out.print("王");
            System.out.print("盖");
            System.out.print("地");
            System.out.println("虎");
            flag = 2;
            this.notify();                  //随机唤醒单个等待的线程
        }
    }
    public void print2() throws InterruptedException {
        synchronized(this) {
            if(flag != 2) {
                this.wait();
            }
            System.out.print("提");
            System.out.print("莫");
            System.out.print("一");
            System.out.print("米");
            System.out.println("五-----------------------------------");
            flag = 1;
            this.notify();
        }
    }
}
