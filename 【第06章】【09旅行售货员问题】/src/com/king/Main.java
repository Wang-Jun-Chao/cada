package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-25
 * Time: 10:02
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        final int[][] m = {
                {0, 0,  0,  0,  0 },
                {0, 0,  30, 6,  4 },
                {0, 30, 0,  5,  10},
                {0, 6,  5,  0,  20},
                {0, 4,  10, 20, 0 }
        };

        final int n = 4;
        int[] v = new int[n + 1];

        System.out.println("The adjacency matrix of graph G is");
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                System.out.printf("%-4d", m[i][j]);
            }

            System.out.println();
        }

        int result = Traveling.solve(m,  n, v);
        int i = 1;
        System.out.println("The least cost is " + result);


        System.out.println("The route is ");
        for (int j = 1; j <= n; j++) {
            if (j != n) {
                System.out.print(v[j] + "->");
            } else {
                System.out.print(v[j] + "->1");
            }

        }
    }
}
