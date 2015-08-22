package com.king;

import java.util.List;

/**
 * Author: 王俊超
 * Date: 2015-01-08
 * Time: 14:33
 * Declaration: All Rights Reserved !!!
 */
public final class Coloration {
    private static int n;               // 图的顶点数
    private static int m;               // 可用的颜色数
    private static int[][] a;           // 图的邻接矩阵
    private static int[] x;             // 当前解
    private static List<int[]> bestX;   // 所有最优解的集合
    private static int sum;             // 当前已找到的可m着色方案数

    private Coloration() {
        throw new RuntimeException("The class com.com.king.Coloration should not be initialized.");
    }

    /**
     * 判断顶点k是否可以着色
     *
     * @param k 第k个顶点
     * @return true：可以着色，false：不可以着色
     */
    private static boolean isColorable(int k) {
        // 检查颜色是否可用
        for (int i = 1; i <= n; i++) {
            // a[k][i]==1说明k、i之间相邻
            // x[i]==x[k]说明两个顶点的颜色值相同，颜色值可以取[1:m]，0说明没有着色
            if (a[k][i] == 1 && x[i] == x[k] && i != k) {
                return false;
            }
        }

        return true;
    }

    private static void traceBack(int t) {
        if (t > n) {
            ++sum;
            int[] r = new int[x.length];
            System.arraycopy(x, 1, r, 1, n);
            bestX.add(r);
        } else {
            for (int i = 1; i <= m; i++) {
                x[t] = i;
                if (isColorable(t)) {
                    traceBack(t + 1);
                }

                x[t] = 0;
            }
        }
    }

    public static int solve(int num, int color, int[][] matrix, List<int[]> result) {
        n = num;
        m = color;
        a = matrix;
        sum = 0;
        x = new int[num + 1];
        bestX = result;
        for (int i = 0; i <= num; i++) {
            x[i] = 0;
        }
        traceBack(1);
        x = null;
        return sum;
    }
}
