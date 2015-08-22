package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-13
 * Time: 09:38
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        final int N = 5;
        final int m = Integer.MAX_VALUE;
        int[][] c = {
                {m, m, m, m, m, m},
                {m, 0, 10, m, 30, 100},
                {m, m, 0, 50, m, m},
                {m, m, m, 0, m, 10},
                {m, m, 20, m, 0, 60},
                {m, m, m, m, m, 0}
        };
        int[] prev = new int[N + 1];
        int[] dist = new int[N + 1];
        final int v = 1; // 源点


        ShortestPath.solve(N, v, dist, prev, c);
        for (int i = 2; i <= N; i++) {
            System.out.println("(1)->(" + i + ")的距离: " + dist[i]);
            traceBack(v, i, prev);
            System.out.println();
        }
    }

    /**
     * 输出最短路径
     *
     * @param v    源点
     * @param i    终点
     * @param prev 记录前驱顶点的数组，prev[i]表示源点经过prev[i]到i顶点的距离最短
     */
    public static void traceBack(int v, int i, int[] prev) {

        if (i < 1) { // v->i不可达
            System.out.print("路径不可达");
            return;
        }

        if (v == i) { // 已经过找到了源点了
            System.out.print(i);
            return;
        }

        traceBack(v, prev[i], prev);
        System.out.print("->" + i);
    }
}
