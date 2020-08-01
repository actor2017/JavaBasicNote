package com.actor.javatest.d25Thread;

/**
 * description: 类的描述
 * author     : 李大发
 * date       : 2020/7/27 on 16:34
 */
public class a {

    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    test1("www.baidu.com", "0");
                }
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    test1("www.qq.com", "1");
                }
            }
        }.start();
    }

    private /*synchronized*/ static void test1(String url, String params) {
        try {
            System.out.printf("%s: url=%s%n", Thread.currentThread().getName(), url);
            Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s: params=%s%n", Thread.currentThread().getName(), params);
    }
}
