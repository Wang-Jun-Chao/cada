package com.king;

/**
 * 单源最短路径问题，Dijkstra算法
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-01
 * Time: 16:10
 * Declaration: All Rights Reserved !!!
 */
public final class Dijkstra {

    private static final int N = 5;

    /**
     * Dijkstra单源最短路
     *
     * @param n    顶点数目
     * @param v    顶点数组，下标为0的元素不使用
     * @param dist 顶点到各个顶点的距离数组，下标为0的元素不使用
     * @param prev 记录前驱顶点的数组，prev[i]表示源点经过prev[i]到i顶点的距离最短，下标为0的元素不使用
     * @param c    距离数组，c[i][j]表示顶点i直接到达顶点j的距离是c[i][j]，如果不可达就用整数的最大值表示，i>0，j>0
     */
    public static void dijkstra(int n, int v, int[] dist, int[] prev, int[][] c) {
        boolean[] s = new boolean[N + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = c[v][i]; // dist[i]表示当前从源v到顶点i的最短特殊路径长度
            s[i] = false; // 标记所有的顶点还没有被访问过

            // 此时的dist[i]表示v顶点到i顶点的距离，如果距离不是可表示的最大值，
            // 说明v可以达到i，i前一个顶点是v
            if (dist[i] != Integer.MAX_VALUE) {
                prev[i] = v;
            } else { // 如果不可以到达就记为0
                prev[i] = 0;
            }

        }

        dist[v] = 0; // v为源点，自己到自己的距离为0
        s[v] = true; // 标记顶点v已经被访问过了


        for (int i = 1; i <= N; i++) {
            int temp = Integer.MAX_VALUE;
            int u = v; // 记录上一个顶点

            for (int j = 1; j <= N; j++) {
                // 如果j顶点没有被访问过，并且源点v经中间路径可达j顶点，
                // 记录下【1】j顶点位置，【2】源点v经中间到j顶点的距离，
                // for的结果就是找到最后一个满足条件的顶点
                if (!s[j] && dist[j] < temp) {
                    u = j;
                    temp = dist[j];
                }
            }

            s[u] = true; // 标记顶点u已经被访问过了

            for (int j = 1; j <= N; j++) {
                if (!s[j] && c[u][j] < temp) {
                    int newDist = dist[u] + c[u][j]; // newDist为源点v经过u顶点到j顶点的距离
                    if (newDist < dist[j]) { // 此时的dist[j]为源点v不经过u顶点到j顶点的距离
                        dist[j] = newDist; // 更新新的最短路径
                        prev[j] = u; // 记录从u顶点到j使得源点v到j距离最短
                    }
                }
            }
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
