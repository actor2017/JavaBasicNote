package com.actor.javatest.d25Thread;

/**
 * description: 单例设计模式
 * author     : 李大发
 * date       : 2020/8/1 on 18:00
 */
public class _1_Singleton {

    public static void main(String[] args) {
        _1_Singleton instance = _1_Singleton.getInstance();
    }


    protected static _1_Singleton instance;//懒汉式
    protected static _1_Singleton instance1 = new _1_Singleton();//饿汉式(推荐)
    protected static final _1_Singleton instance2 = new _1_Singleton();//不能修改

    //私有空参构造
    private _1_Singleton() {
    }

    //懒汉式
    public static _1_Singleton getInstance() {
        if (instance == null) {//每次都要判断.
            synchronized (_1_Singleton.class) {
                if (instance == null) instance = new _1_Singleton();
            }
        }
        return instance;
    }

    //饿汉式(推荐)
    public static _1_Singleton getInstance1() {
        return instance1;
    }
}
