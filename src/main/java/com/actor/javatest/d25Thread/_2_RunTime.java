package com.actor.javatest.d25Thread;

import java.io.IOException;

/**
 * description: Runtime 饿汉式
 * author     : 李大发
 * date       : 2020/8/1 on 18:29
 */
public class _2_RunTime {

    /**
     * @see Runtime 饿汉式
     */
    public static void main(String[] args) throws IOException {
        Runtime runtime = Runtime.getRuntime();

        //关机
//        runtime.exec("shutdown -s -t 300");//在单独的进程中执行指定的命令,变量, 详见api
        //取消关机
        runtime.exec("shutdown -a");
    }
}
