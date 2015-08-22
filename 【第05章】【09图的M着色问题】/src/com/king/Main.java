package com.king;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 王俊超
 * Date: 2015-01-08
 * Time: 14:33
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
//        test1();
        test2();
    }


    private static void test1 () {
        final int N = 5;
        final int C = 3;
        final int[][] m = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 1, 0},
                {0, 1, 0, 1, 1, 1},
                {0, 1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0, 1},
                {0, 0, 1, 0, 1, 0}

        };

        List<int[]> r = new ArrayList<>();


        System.out.println("The adjacency matrix of graph G is");
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }

            System.out.println();
        }
        int num = Coloration.solve(N, C, m, r);

        System.out.println("The colors be used are " + C);
        System.out.println("The number of viable coloring scheme is: " + num);
        System.out.println("The solutions are");

        for (int[] a : r) {
            for (int i = 1; i < a.length; i++) {
                System.out.print("(V:" + i + ", M:" + a[i] + ")");
            }
            System.out.println();
        }
    }

    private static void test2() {
        final int N = 5;
        final int C = 3;
        final int[][] m = {
                {0, 0, 0, 0, 0, 0},
                {0, 0, 1, 1, 0, 0},
                {0, 1, 0, 1, 1, 1},
                {0, 1, 1, 0, 1, 0},
                {0, 0, 1, 1, 0, 1},
                {0, 0, 1, 0, 1, 0}

        };

        List<int[]> r = new ArrayList<>();


        System.out.println("The adjacency matrix of graph G is");
        for (int i = 1; i < m.length; i++) {
            for (int j = 1; j < m[i].length; j++) {
                System.out.print(m[i][j] + " ");
            }

            System.out.println();
        }
        int num = Coloration.solve(N, C, m, r);

        System.out.println("The colors be used are " + C);
        System.out.println("The number of viable coloring scheme is: " + num);
        System.out.println("The solutions are");

        for (int[] a : r) {
            for (int i = 1; i < a.length; i++) {
                System.out.print("(V:" + i + ", M:" + a[i] + ")");
            }
            System.out.println();
        }
    }
}
