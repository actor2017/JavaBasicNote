package com.actor.javatest._2_key_words._2_class_method_variable;

/**
 * volatile /ˈvɑːlətl/ a.易失
 *          关键字用于表示可以被多个线程异步修改的成员变量。
 *
 * 注意：volatile 关键字在许多 Java 虚拟机中都没有实现。
 *       volatile 的目标用途是为了确保所有线程所看到的指定变量的值都是相同的。
 *
 * Java 语言中的 volatile 变量可以被看作是一种 “程度较轻的 synchronized”；
 * 与 synchronized 块相比，volatile 变量所需的编码较少，并且运行时开销也较少，但是它所能实现的功能也仅是 synchronized 的一部分。
 *
 *
 *
 * https://baijiahao.baidu.com/s?id=1595669808533077617&wfr=spider&for=pc
 * volatile作为java中的关键词之一，用以声明变量的值可能随时会别的线程修改，使用volatile修饰的变量会强制将修改的值立即写入主存，
 * 主存中值的更新会使缓存中的值失效(非volatile变量不具备这样的特性，非volatile变量的值会被缓存，线程A更新了这个值，
 * 线程B读取这个变量的值时可能读到的并不是是线程A更新后的值)。volatile会禁止指令重排。
 *
 * volatile 原理
 * 在生成汇编代码时会在volatile修饰的共享变量进行写操作的时候会多出Lock前缀的指令那么Lock前缀的指令在多核处理器下主要有这两个方面的影响：
 *
 * 数据发生修改时，将当前处理器缓存行的数据写回系统内存；
 * 这个写回内存的操作会使得其他CPU里缓存了该内存地址的数据无效
 * 为了提高处理速度，处理器不直接和内存进行通信，而是先将系统内存的数据读到内部缓存（L1，L2或其他）后再进行操作，但操作完不知道何时会写到内存。
 *
 * 如果对声明了volatile的变量进行写操作，JVM就会向处理器发送一条Lock前缀的指令，将这个变量所在缓存行的数据写回到系统内存。
 * 但是，就算写回到内存，如果其他处理器缓存的值还是旧的，再执行计算操作就会有问题。
 * 所以，在多处理器下，为了保证各个处理器的缓存是一致的，就会实现缓存一致性协议，
 * 每个处理器通过嗅探在总线上传播的数据来检查自己缓存的值是不是过期了，当处理器发现自己缓存行对应的内存地址被修改，
 * 就会将当前处理器的缓存行设置成无效状态，当处理器对这个数据进行修改操作的时候，会重新从系统内存中把数据读到处理器缓存里。
 * 因此，经过分析我们可以得出如下结论：
 *
 * Lock前缀的指令会引起处理器缓存写回内存；
 * 一个处理器的缓存回写到内存会导致其他处理器的缓存失效；
 * 当处理器发现本地缓存失效后，就会从内存中重读该变量数据，即可以获取当前最新值。
 * 这样针对volatile变量通过这样的机制就使得每个线程都能获得该变量的最新值。
 */

public class _17_volatile {

    /**
     * http://yun.itheima.com/course/629.html?2005zzp, 还有视频没看完
     * 1.1.1概述
     * 在多线程并发执行下, 多个线程修改共享的成员变量, 会出现一个线程修改了共享变量的值后, 另一个线程不能直接看到该线程修改后的变量的最新值.
     */
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
        while (true) {
            /**
             * 如果 flag 不加 volatile, 这儿的flag永远=false
             */
            if (thread.flag) {
                System.out.println("主线程进入循环执行~~~~~");
                break;
            }
        }
    }
}
class MyThread extends Thread {
    volatile boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(100L);
            flag = true;
            System.out.println("flag = true");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
