package com.actor.javatest._2_key_words._3_progress_control;

/**
 * do 关键字用于指定一个在每次迭代结束时检查其条件的循环。
 * do 循环体至少执行一次。
 * 条件表达式后面必须有分号。
 */
public class _6_do {
    public static void main(String[] args) {
        int i = 1;
        do {
            System.out.println("i=" + i++);
        } while (i <= 10);
    }
}