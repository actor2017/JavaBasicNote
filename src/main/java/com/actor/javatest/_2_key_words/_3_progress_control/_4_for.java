package com.actor.javatest._2_key_words._3_progress_control;

import java.util.Scanner;

/**
 * for 循环
 * for 关键字用于指定一个在每次迭代结束前检查其条件的循环。
 * for 语句的形式为 for(initialize; condition; increment)
 *      控件流进入 for 语句时，将执行一次 initialize 语句。
 *      每次执行循环体之前将计算 condition 的结果。如果 condition 为 true，则执行循环体。
 *      每次执行循环体之后，在计算下一个迭代的 condition 之前，将执行 increment 语句。
 *
 * for(初始化表达式;条件表达式;循环后的操作表达式) {
 *     循环体;
 * }
 */

public class _4_for {
    public static void main(String[] args) {
        //1.九九乘法表
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("%dx%d=%2d ", j, i, i * j);
            }
            System.out.println();
        }

        //2.死循环
        Scanner sc = new Scanner(System.in);
        int a;
        for (; ; ) {
            System.out.println("请输入第一个0-10间的整数:");
            a = sc.nextInt();
            if (a >= 0 && a <= 10) {
                break;          //用break跳出
            } else {
                System.out.println("输入错误,请重新输入!");
            }
        }

        /**
         * 求Sn = a + aa + aaa + … + aa…a 的值（最后一个数中 a 的个数为 n ），
         * 其中 a 是一个1~9的数字，例如：2 + 22 + 222 + 2222 + 22222 （此时 a=2 n=5 ）
         *
         * 输入一行，包括两个整数，第一个为a，第2个为n（1 ≤ a, n ≤ 9），以空格分隔。
         * 输出一行，Sn的值。
         * 样例输入
         * 2  5
         * 样例输出
         * 24690
         */
        System.out.println("求Sn = a + aa + aaa + … + aa…a 的值, 清输入 a(1~9) 和 n(1~9)，以空格分隔, 样例输入: 2  5");
        Scanner sc1 = new Scanner(System.in);
        int a1 = sc1.nextInt();
        int n = sc1.nextInt();
        int c = 0;			//定义内循环的值
        int Sn = 0;			//定义总数
        for (int i = 0; i < n; i++) {	//循环5次
            c = c * 10 + a1;		    //2,22,222,2222,22222
            Sn = Sn + c;		        //2,24,246,2468,24690
        }
        System.out.println(Sn);
    }
}