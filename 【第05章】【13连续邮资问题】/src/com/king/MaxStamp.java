package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-12
 * Time: 09:48
 * Declaration: All Rights Reserved !!!
 */
public final class MaxStamp {
    private static int n;           // 邮票面值种类数
    private static int m;           // 每张信封最多允许贴的邮票数
    private static int maxValue;    // 当前最优值
    private static int maxInt;      // 大整数
    private static int maxL;        // 邮资上界
    private static int[] x;         // 当前解
    private static int[] y;         // 贴出各种邮资所需最少邮票数
    private static int[] bestX;     // 当前最优解

    private MaxStamp() {
        throw new RuntimeException("The class com.com.king.MaxStamp should not be initialized.");
    }

    /**
     * 解决连续邮资问题的递归算法
     *
     * @param i 第i种邮票
     * @param r 第一个不能用前i-1种邮票表示的数字
     */
    private static void traceBack(int i, int r) {
        // 从[0,x[i-2]*(m-1)]是连续的
        // 对第i种邮票进行处理
        for (int j = 0; j <= x[i - 2] * (m - 1); j++) {
            if (y[j] < m) { // y[j]：贴出j值所需要的最少邮票张数是y[j]
                for (int k = 1; k <= m - y[j]; k++) { // m - y[j]：还可以使用的邮票张数
                    if (y[j] + k < y[j + x[i - 1] * k]) {
                        // 设置j+x[i-1]*k的面值最多使用y[j]+k个邮票表示
                        y[j + x[i - 1] * k] = y[j] + k;
                    }
                }
            }
        }

        // 使用前i-1种邮票，找出第一个不能表示的面值
        while (y[r] < maxInt) {
            r++;
        }

        if (i > n) { // 如果所有的种数已经用完
            if (r - 1 > maxValue) { // 如果新的解大于已经保存的解
                maxValue = r - 1; // 记录最优值
                System.arraycopy(x, 1, bestX, 1, n); // 记录最优解
            }
            return;
        }

        int[] z = new int[maxL + 1]; // 用于保存当前面值的表示情况
        System.arraycopy(y, 1, z, 1, maxL);

        // 第i种邮票，其单张可以表示的选择范围为[x[i-1]+1, r]
        for (int j = x[i - 1] + 1; j <= r; j++) {
            x[i] = j; // 设置第i种邮票是面值是j
            traceBack(i + 1, r);

            System.arraycopy(z, 1, y, 1, maxL);
        }
    }


    /**
     * 连续邮资问题解决方法入口
     *
     * @param n     邮票种类
     * @param m     最多可以使用多少张邮票
     * @param bestX 保存最优解
     * @return 保存最优值
     */
    public static int solve(int n, int m, int[] bestX) {
        // 初始化
        MaxStamp.n = n;
        MaxStamp.m = m;
        MaxStamp.maxValue = 0;
        MaxStamp.maxInt = Integer.MAX_VALUE;
        MaxStamp.maxL = 8192;
        MaxStamp.bestX = bestX;
        MaxStamp.x = new int[n + 1];
        MaxStamp.y = new int[MaxStamp.maxL + 1];

        for (int i = 0; i <= n; i++) {
            MaxStamp.x[i] = 0;
        }

        for (int i = 1; i <= maxL; i++) {
            MaxStamp.y[i] = MaxStamp.maxInt;
        }

        MaxStamp.x[1] = 1; // 第张邮票的面值
        MaxStamp.y[0] = 0;

        traceBack(2, 1);

        MaxStamp.x = null;
        MaxStamp.y = null;

        return MaxStamp.maxValue;
    }
}
