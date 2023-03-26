package com.actor.javatest._2_key_words._3_progress_control;

/**
 * while 循环
 * while 关键字用于指定一个只要条件为真就会重复的循环。
 */

public class _5_while {
    public static void main(String[] args) {
        /**
         * 在控制台输出所有的"水仙花数"
         * 所谓的水仙花数是指一个三位数(限本例),其各位数字的立方和等于该数本身.
         * 举例:153就是一个水仙花数.
         * 153 = 1*1*1+5*5*5+3*3*3
         */
        int i = 100;
        int count = 0;
        while (i < 1000) {
            int ge = i % 10;                //获取个位
            int shi = i / 10 % 10;          //获取十位
            int bai = i / 100 % 10;         //获取百位
            if (ge * ge * ge + shi * shi * shi + bai * bai * bai == i) {
                System.out.println(i);
                count++;
            }
            i++;
        }
        System.out.println(count);


        //2.while无限循环
        while (true) {
            int i1 = 0;
            i1++;
            System.out.println("i1=" + i1);
            if (i1 > 100) {
                break;            //用break跳出
            }
        }
    }
}