package com.king;

/**
 * 汉诺塔问题
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-02
 * Time: 08:13
 * Declaration: All Rights Reserved !!!
 */
public class Hanoi {
    /**
     * 汉诺塔问题
     *
     * @param n 圆盘的个数
     * @param a 柱子a
     * @param b 柱子b
     * @param c 柱子c
     */
    public static void hanoi(int n, char a, char b, char c) {
        if (n > 0) {
            hanoi(n - 1, a, c, b);// 借助b，将n-1个盘子从a移到c
            move(a, b);
            hanoi(n - 1, c, b, a); //借助a，将n-1个盘子从c移到b
        }
    }

    /**
     * 将柱子a顶端的盘子移动到柱子b的顶端
     *
     * @param a 柱子a
     * @param b 柱子b
     */
    private static void move(char a, char b) {
        System.out.println(a + "->" + b);
    }
}
