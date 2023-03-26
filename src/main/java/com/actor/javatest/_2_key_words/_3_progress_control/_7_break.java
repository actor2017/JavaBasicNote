package com.actor.javatest._2_key_words._3_progress_control;

/**
 * break 跳出，中断
 * break 关键字用于提前退出 for、while 或 do 循环，或者在 switch 语句中用来结束 case 块。
 *
 * break 总是退出最深层的 while、for、do 或 switch 语句。
 */
public class _7_break {
    public static void main(String[] args) {
        for (int x = 1; x <= 10; x++) {
            if (x == 4) {
                break;                            //跳出循环
            }
            System.out.println("x = " + x);
        }
    }
}