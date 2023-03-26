package com.actor.javatest._2_key_words._3_progress_control;

import java.util.Scanner;

/**
 * switch 观察
 * switch 语句用于基于某个表达式选择执行多个代码块中的某一个。
 * switch 条件的计算结果必须等于 byte、char、short、int、enum 或 String (long不行)。
 * case 块没有隐式结束点。break 语句通常在每个 case 块末尾使用，用于退出 switch 语句。
 * 如果没有 break 语句，执行流将接着进入后面的 case 和/或 default 块。
 */

public class _2_switch {
    public static void main(String[] args) {
        //1.default 先判断
        defaultFirst();
        //2.switch 的穿透应用
        season();
    }

    private static void defaultFirst() {
        int x = 2, y = 3;
        switch (x) {
            default:
                y++;
                System.out.println("default");  //进入这儿, 所以应该把default写到最后.
                break;
            case 3:
                y++;
                System.out.println("3");
            case 4:
                y++;
                System.out.println("4");
        }
        System.out.println(y);		            //y=4
    }

    private static void season() {
        System.out.println("请输入月份:");
        Scanner sc = new Scanner(System.in);
        int mounth = sc.nextInt();
        System.out.println(mounth);
        switch (mounth) {
            case 3:
            case 4:
            case 5:
                System.out.println("月是春季");
                break;
            case 6:
            case 7:
            case 8:
                System.out.println("月是夏季");
                break;
            case 9:
            case 10:
            case 11:
                System.out.println("月是秋季");
                break;
            case 12:
            case 1:
            case 2:
                System.out.println("月是冬季");
                break;
            default:
                System.out.println(": 输入错误!");
                break;
        }
    }
}