package com.actor.javatest.d25Thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * description: Timer 计时器
 *              TimerTask implements Runnable
 *
 * author     : 李大发
 * date       : 2020/8/1 on 18:38
 */
public class _3_Timer {

    public static void main(String[] args) {
        Timer timer = new Timer();

        /**
         * 在固定时间以周期period时间执行任务
         */
        timer.schedule(new MyTimerTask(),
                /**
                 * 参1: 年, 2016 - 1900 = 116
                 * 参2: 月, 0~11
                 */
                new Date(2016 - 1900/* = 116*/, 6, 1, 14, 22, 50),

                3000);//过多长时间再重复执行
    }
}

//TimerTask implements Runnable
class MyTimerTask extends TimerTask {
    @Override
    public void run() {
        System.out.println("起床背英语单词: " + new Date());
    }
}
