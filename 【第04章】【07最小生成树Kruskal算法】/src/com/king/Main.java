package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-03
 * Time: 12:57
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        final int N = 10; // 图的边数
        final int M = 6; // 图的顶点数
        EdgeNode[] E = new EdgeNode[N + 1];
        EdgeNode[] t = new EdgeNode[N + 1];

        int[][] data = {
                {}, // 这个只做点占位使用
                {1, 3, 1},
                {1, 2, 6},
                {1, 4, 5},
                {2, 3, 5},
                {2, 5, 3},
                {3, 4, 5},
                {3, 5, 6},
                {3, 6, 4},
                {4, 6, 2},
                {5, 6, 6}};

        for (int i = 1; i <= N; i++) {
            E[i] = new EdgeNode(data[i][0], data[i][1], data[i][2]);
        }

        System.out.println("连通带权图所有边的两端顶点，权分别为: ");
        for (int i = 1; i <= N; i++) {
            System.out.println("(u:" + E[i].getU() + ", v:" + E[i].getV() + ", w:" + E[i].getWeight() + ")");
        }

        boolean result = Kruskal.kruskal(M + 1, N, E, t);

        if (result) {
            System.out.println("Kruskal算法最小生成树选择结果为: ");
            for (int i = 1; i < M; i++) {
                System.out.println("(u:" + t[i].getU() + ", v:" + t[i].getV() + ", w:" + t[i].getWeight() + ")");
            }
        }
    }

}
