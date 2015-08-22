package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-09
 * Time: 09:24
 * Declaration: All Rights Reserved !!!
 */
public final class Traveling {
    private static int n;               // 图G的顶点数
    private static int[] x;             // 当前解
    private static int[] bestX;         // 当前最优解
    private static int[][] a;           // 图G的领接矩阵
    private static int cc;              // 当前费用
    private static int bestC;           // 当前最优值
    private final static int NE = 0;    // 无边标记


    private Traveling() {
        throw new RuntimeException("The class com.com.king.Traveling should not be initialized.");
    }

    /**
     * 交换数组两个位置的元素
     *
     * @param a 数组
     * @param x 位置
     * @param y 位置
     */
    private static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    /**
     * 旅行员售货问题，递归解法
     *
     * @param i 第i个顶点
     */
    private static void traceBack(int i) {
        if (i == n) { // 如果已经处理到了最后一个顶点
            // a[x[n - 1]][x[n]] != NE 表示顶点n-1到顶点n有边，即可达
            // a[x[n]][1] != NE 表示顶点n到1有边，即可以到达原来的出发点
            // cc + a[x[n - 1]][x[n]] + a[x[n]][1] 当前的最小旅行代价
            // bestC == NE 还没有计算最优值
            if (a[x[n - 1]][x[n]] != NE && a[x[n]][1] != NE
                    && (cc + a[x[n - 1]][x[n]] + a[x[n]][1] < bestC || bestC == NE)) {

                System.arraycopy(x, 1, bestX, 1, n); // 保存最优解
                bestC = cc + a[x[n - 1]][x[n]] + a[x[n]][1]; // 保存最优值
            }
        } else {
            // 数组x的i位置之前所有顶点已经被处理过了，
            // 对[i:n]位置的顶点进行尝试
            for (int j = i; j <= n; j++) {
                // 如果i-1位置的顶点与j位置的顶点可达
                // 并且代价小于目前的最做优代价，或者还没有计算过最优价值
                if (a[x[i - 1]][x[j]] != NE && (cc + a[x[i - 1]][x[j]] < bestC || bestC == NE)) {
                    swap(x, i, j); // 交换两个顶点的位置
                    cc += a[x[i - 1]][x[i]]; // 累加当前的最优值
                    traceBack(i + 1); // 处理下一个顶点
                    cc -= a[x[i - 1]][x[i]]; // 还原当前的最优值
                    swap(x, i, j); // 还原顶点的位置
                }
            }
        }
    }

    /**
     * 旅行员售货问题解决的入口
     *
     * @param matrix 图G的领接矩阵
     * @param vertex 保存当前最优当前解
     * @param num    图G的顶点数
     * @return 当前最优值
     */
    public static int solve(int[][] matrix, int[] vertex, int num) {
        // 初始化操作
        x = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            x[i] = i;
        }
        a = matrix;
        n = num;
        bestC = NE;
        bestX = vertex;
        cc = 0;

        traceBack(2);
        x = null;

        return bestC;
    }
}
