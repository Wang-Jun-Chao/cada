package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-11
 * Time: 15:01
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        final int n = 8;
        final int m = 5;
        final int[][] B = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 1}
        };

        int[] arr = new int[n + 1];

        int result = BoardArrangement.solve(B, n, m, arr);
        System.out.println(result);
        for (int i = 1; i <= n; i++) {
            System.out.print("(P:" + i + ", B:" + arr[i] + ")");
        }
    }
}
