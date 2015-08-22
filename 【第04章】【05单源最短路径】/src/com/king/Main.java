package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-01
 * Time: 16:10
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        final int N = 5;
        final int m = Integer.MAX_VALUE;
        int[][] c = {
                {m, m, m,  m,  m,  m},
                {m, 0, 10, m,  30, 100},
                {m, m, 0,  50, m,  m},
                {m, m, m,  0,  m,  10},
                {m, m, 20, m,  0,  60},
                {m, m, m,  m,  m,  0}
        };
        int[] prev = new int[N + 1];
        int[] dist = new int[N + 1];
        final int v = 1; // 源点


        Dijkstra.dijkstra(N, v, dist, prev, c);
        for (int i = 2; i <= N; i++) {
            System.out.println("(1)->(" + i + ")的距离: " + dist[i]);
            Dijkstra.traceBack(v, i, prev);
            System.out.println();
        }
    }
}
