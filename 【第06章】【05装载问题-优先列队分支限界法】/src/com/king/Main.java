package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-19
 * Time: 10:28
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        // 集装箱重量数组
        int[] w = new int[]{0, 10, 40, 23, 41, 12, 6, 78, 44, 154, 40};
        int c1 = 250;
        int c2 = 200;
        int[] bestX = new int[w.length];

        int bestC1 = MaxLoading.solve(w, c1, w.length - 1, bestX);


        System.out.println("重量数组: ");
        for (int i = 1; i < w.length; i++) {
            System.out.printf("%-4d", w[i]);
        }
        System.out.println();
        System.out.println("选择结果: ");
        for (int i = 1; i < bestX.length; i++) {
            System.out.printf("%-4d", bestX[i]);
        }

        System.out.println();
        System.out.println("最优解是: " + bestC1);
    }
}
