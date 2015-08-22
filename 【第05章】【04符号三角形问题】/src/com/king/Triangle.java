package com.king;

/**
 * 回溯法解符号三角形问题
 * <p/>
 * 问题描述：
 * 如下图是由14个“+”和14个“-”组成的符号三角形, 2个同号下面都是“+”，2个异号下面都是“-”。
 * - + + - + + +
 *  - + - - + +
 *   - - + - +
 *    + - - -
 *     - + +
 *      - +
 *       -
 * 在一般情况下，符号三角形的第一行有n个符号, 符号三角形问题要求对于给定的n，计算有多少个不同的符号三角形，
 * 使其所含的“+”和“-”的个数相同。
 * <p/>
 * 解题思路：
 * 1、不断改变第一行每个符号，搜索符合条件的解，可以使用递归回溯，为了便于运算，设+ 为0，- 为1，这样可以使
 * 用异或运算符表示符号三角形的关系++为+即0^0=0, --为+即1^1=0, +-为-即0^1=1, -+为-即1^0=1;
 * 2、因为两种符号个数相同，可以对题解树剪枝，当所有符号总数为奇数时无解，当某种符号超过总数一半时无解
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-06
 * Time: 09:03
 * Declaration: All Rights Reserved !!!
 */
public final class Triangle {
    private static int n;       // 第一行的符号个数
    private static int half;    // n*(n+1)/4
    private static int count;   // 当前"+"号个数
    private static int[][] p;   // 符号三角矩阵
    private static long sum;    // 已找到的符号三角形数

    private Triangle() {
        throw new RuntimeException("The class com.com.king.Triangle should not be initialized.");
    }

    /**
     * 求解符号三角形
     *
     * @param t 第一行第t个符号
     */
    private static void backTrace(int t) {
        // count > t "+"号已经超过符号数的一半
        // t * (t - 1) / 2 到当前为止所有的符号数
        // (t * (t - 1) / 2 - count) 到当前为止被标记为"-"号的数目
        // 若符号统计超过半数，或者另一种符号超过半数
        if (count > half || (t * (t - 1) / 2 - count) > half) {
            return;
        }

        if (t > n) { // 到达叶结点
            sum++; // 一次处理已经完成，说明存一种符合条件的情况
            // TODO 这里可以保存解
        } else {
            for (int i = 0; i < 2; i++) {
                p[1][t] = i; // 第一行符号
                count += i; // 当前"+"号个数

                for (int j = 2; j <= t; j++) { // 当第一行符号>=2时，可以运算出下面行的某些符号
                    p[j][t - j + 1] = p[j - 1][t - j + 1] ^ p[j - 1][t - j + 2]; // 通过异或运算下行符号
                    count += p[j][t - j + 1]; // 统计包括下面行的“+”号个数
                }

                backTrace(t + 1); // 在第一行添加一个元素

                for (int j = 2; j <= t; j++) { // 回溯，判断另一种符号情况
                    count -= p[j][t - j + 1];
                }

                count -= i;
            }
        }
    }

    /**
     * 符号三角形问题入口
     *
     * @param num 第一行的符号数目
     * @return 总计有多少种满足条件的符号三角形
     */
    public static long compute(int num) {

        if (num * (num + 1) % 4 != 0) { // 如果总的符号数不能被4整除，说明不存在解
            return 0;
        }

        // 数据初始化
        n = num;
        count = 0;
        sum = 0;
        half = num * (n + 1) / 4;
        p = new int[num + 1][num + 1];
        for (int i = 0; i < p.length; i++) {
            for (int j = 0; j < p.length; j++) {
                p[i][j] = 0;
            }
        }
        backTrace(1);
        return sum;

    }
}
