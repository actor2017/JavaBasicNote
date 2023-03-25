package com.actor.javatest._2_key_words._3_progress_control;

/**
 * default 默认
 * default 关键字用来标记 switch 语句中的默认分支。
 *
 * default 块没有隐式结束点。break 语句通常在每个 case 或 default 块的末尾使用，以便在完成块时退出 switch 语句。
 *
 * 如果没有 default 语句，其参数与任何 case 块都不匹配的 switch 语句将不执行任何操作。
 *
 * default 也可修饰 interface 里的方法, 修饰后这个方法要有默认实现.
 */

public interface _12_default {
    //default 修饰 interface 中的方法, 有默认返回
    default short getGender() {
        return 1;           //默认男
    }
}