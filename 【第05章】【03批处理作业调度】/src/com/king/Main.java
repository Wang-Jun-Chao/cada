package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-05
 * Time: 20:34
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        final int n = 3;
        final int[][] m = {{0, 0, 0}, {0, 2, 1}, {0, 3, 1}, {0, 2, 3}};
        int[] bestX = new int[n + 1];



        System.out.println("M(i,j)值如下：");
        for (int i = 1; i <= n; i++) {
            System.out.println("(J: "+i+", M1: " + m[i][1] + ", M2: " + m[i][2] + ")");
        }
//        System.out.println();

        int bestF = FlowShop.flow(m, n, bestX);

        System.out.println("当前最优值:" + bestF);
        System.out.println("当前最优作业调度");
        for (int i = 1; i <= n; i++) {
            System.out.print("(J: " + bestX[i] + ")");
        }

    }
}
