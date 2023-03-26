package com.actor.javatest._2_key_words._3_progress_control;

/**
 * if 关键字指示有条件地执行代码块。条件的计算结果必须是布尔值。
 * if 语句可以有可选的 else 子句，该子句包含条件为 false 时将执行的代码。
 * 包含 boolean 操作数的表达式只能包含 boolean 操作数。
 */

public class _1_if {
    public static void main(String[] args) {
        //1.if
        boolean flag = true;
        if(flag) {
            System.out.println("flag = true");
        }

        //2.if    else
        boolean flag1 = false;
        if(flag1 = true) {
            System.out.println("flag1 = true");
        } else {
            System.out.println("flag1 = false");
        }

        //3.if    else if    else if    else
        int age = 17;
        if (age < 18) {
            System.out.println("未成年人");
        } else if (age >= 18 && age <= 60) {
            System.out.println("可以浏览本网站");
        } else {
            System.out.println("老人");
        }
    }
}