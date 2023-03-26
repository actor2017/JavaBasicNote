package com.actor.javatest._2_key_words._3_progress_control;

/**
 * continue 继续
 * continue 关键字用来跳转到 for、while 或 do 循环的下一个迭代。 只能在循环中
 *
 * continue 总是跳到最深层 while、for 或 do 语句的下一个迭代。
 */
public class _8_continue {
    public static void main(String[] args) {
        for (int x = 1; x <= 10; x++) {
            if (x == 4) {
                continue;        //终止本次循环,继续下次循环, 不会输出x=4
            }
            System.out.println("x = " + x);
        }
    }
}

