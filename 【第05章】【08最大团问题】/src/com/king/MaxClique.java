package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-08
 * Time: 10:16
 * Declaration: All Rights Reserved !!!
 */
public final class MaxClique {
    private static int[][] a;       // 图G的邻接矩阵
    private static int n;           // 图G的顶点数
    private static int[] x;         // 当前解
    private static int[] bestX;     // 当前最优解
    private static int cn;          // 当前顶点数
    private static int bestN;       // 当前最大顶点数

    private MaxClique() {
        throw new RuntimeException("The class com.com.king.MaxClique should not be initialized.");
    }

    /**
     * 最大团问题解决的递归算法
     * @param i 第i个顶点
     */
    private static void traceBack(int i) {
        if (i > n) { // 说明所有顶点已经处理完了，产生了最优解
            System.arraycopy(x, 1, bestX, 1, n); // 记录最优解
            bestN = cn; // 记录最优值
            return;
        }

        boolean ok = true; // 检查当前顶点i与当前团的连接
        for (int j = 1; j < i; j++) { // [1:i-1]个顶点已经处理过了，它们中有些顶点在团中
            // x[j] != 0说明顶点j在团中，a[i][j] == 0说明i，j之间无边
            if (x[j] != 0 && a[i][j] == 0) {
                ok = false; // 说明加入i顶点后不能够成团
                break;
            }
        }

        if (ok) { // 说明加入i可以构成团
            x[i] = 1; // 标记i顶点已经加入
            cn++; // 当前团中的顶点数加1
            traceBack(i + 1); // 处理下一个顶点
            x[i] = 0; // 还原，标记i顶点已经除去
            cn--; // 还原，当前团中的顶点数减1
        }

        // cn当前团中的顶点数
        // n-1剩余待处理的顶点数
        // cn + n - i预计最大团顶点数
        if (cn + n - i > bestN) {
            x[i] = 0; // 标记第i个顶点不在团中
            traceBack(i + 1);
        }
    }

    /**
     * 最大团问题解决的入口
     * @param m 图G的邻接矩阵
     * @param v 保存最优解的顶点数组
     * @param num 顶点的数目
     * @return 最大团中的顶点数
     */
    public static int solve(int[][] m, int[] v, int num) {
        x = new int[num + 1];
        a = m;
        n = num;
        cn = 0;
        bestN = 0;
        bestX = v;

        traceBack(1);

        x = null;
        a = null;

        return bestN;

    }
}
