package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-26
 * Time: 15:04
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        final int N = 5;
        final int[][] m = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1, 1},
                {0, 1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0, 1},
                {0, 1, 1, 1, 1, 0}

        };

        int[] v = new int[N + 1];

        System.out.println("The adjacency matrix of graph G is");
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }

            System.out.println();
        }

        int result = MaxClique.solve(m, N, v);

        System.out.println("The number of vertices of the maximum clique of graph G is " + result);

        for (int i = 1; i < v.length; i++) {
            if (v[i] == 1) {
                System.out.print("(V: " + i + ")");
            }
        }
        System.out.println();

        System.out.println("The solution vector of the maximum clique of a graph G is");
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                if (m[i][j] == 1 && v[i] == 1 && v[j] == 1) {
                    System.out.print(m[i][j] + " ");
                } else {
                    System.out.print("0 ");
                }

            }

            System.out.println();
        }
    }
}
