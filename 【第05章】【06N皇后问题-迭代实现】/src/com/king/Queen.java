package com.king;

import java.util.List;

/**
 * 在n*n的棋盘上放置彼此不受攻击的n个皇后。按国际象棋的规则，皇后可以与之处在同一行或者同一列或同一斜线上的棋子。
 * n后问题等价于在n*n格的棋盘上放置n皇后，任何2个皇后不放在同一行或同一列的斜线上。
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-06
 * Time: 15:17
 * Declaration: All Rights Reserved !!!
 */
public final class Queen {
    private static int n;           // 皇后个数
    private static int[] x;         // 当前解，x[i]表示第i个皇后放置在x[i]位置
    private static int sum;         // 当前已找到的可行方案数
    private static List<int[]> r;   // 保存所有的解

    private Queen() {
        throw new RuntimeException("The class com.com.com.king.Queen should not be initialized.");
    }

    /**
     * 判断第k个皇后所在的位置x[k]是否满足约束条件
     *
     * @param k 第k个皇后
     * @return true，x[k]可以放置皇后，false，x[k]不可以放置皇后
     */
    private static boolean isPlaceable(int k) {
        for (int i = 1; i < k; i++) { // x[1:k-1]表示之前的皇后所在位置
            // 与之前的皇后进行检查，如有在同一行或者同一斜线上说明x[k]位置不能放置皇后
            if (Math.abs(k - i) == Math.abs(x[i] - x[k]) || x[i] == x[k]) {
                return false;
            }
        }

        return true;
    }

    /**
     * 解决皇后问题的迭代算法
     */
    private static void backTrace() {
        x[1] = 0; // 第一个皇后开始未放入棋盘
        int k = 1; // k表示第k个皇后，从第一个皇后开始处理
        // 为什么k>0？在搜索的过程中总会遇到回溯的情况，并且会一直回滚，总会有k=0的时候，
        // 此时代表已经回溯完了，整个算法已经执行结束
        while (k > 0) {
            x[k] += 1; // 第k个皇后后右移动一个位置
            // 如果还可以移动位置，并且这个位置不可以放置第k个皇后，
            // 则一直找到可以放置位置或者不可以移动位置为止
            while (x[k] <= n && !isPlaceable(k)) {
                x[k] += 1;
            }

            if (x[k] <= n) { // 这是一个可以放置皇后的位置
                if (k == n) { // 如果是最后一个皇后可以放置的位置
                    sum++; // 解的数目加1
                    // 以下是保存解
                    int[] result = new int[x.length];
                    System.arraycopy(x, 1, result, 1, x.length - 1);
                    r.add(result);
                } else { // 不是最后一个皇后
                    k++; // 标记处理下一个皇后
                    x[k] = 0; // 设置下一个皇后的初始位置
                }
            } else {
                k--; // 回溯，返回到上一层
            }
        }
    }

    /**
     * 解决皇后问题的入口
     *
     * @param num    皇后数目
     * @param result 保存所有的解
     * @return 总计的解法
     */
    public static int solve(int num, List<int[]> result) {
        // 初始化操作
        n = num;
        sum = 0;
        r = result;
        x = new int[num + 1]; // 创建皇后位置数组，0号下标不使用
        for (int i = 0; i <= num; i++) {
            x[i] = 0; // 初始时所有的皇后都不在棋盘内
        }

        backTrace();

        return sum;
    }


}
