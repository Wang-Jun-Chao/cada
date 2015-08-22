package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-22
 * Time: 16:14
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        final int n = 4; // 物品数
        final int c = 7; // 背包容量
//        final int[] p = {0, 9, 10, 7, 4}; // 物品价值 下标从1开始
//        final int[] w = {0, 3, 5, 2, 1}; // 物品重量 下标从1开始

        final int[] p = {0, 10, 9, 7, 4}; // 物品价值 下标从1开始
        final int[] w = {0, 5 , 3, 2, 1}; // 物品重量 下标从1开始
        int[] bestX = new int[p.length];

        System.out.println("The capacity of package is " + c);
        System.out.println("Goods' weight and price are");
        for (int i = 1; i <= n; i++) {
            System.out.print("(W:" + w[i] + ", P:" + p[i] + ")");
        }
        System.out.println();

        double result = Knapsack.solve(p, w, c, n, bestX);
        System.out.println("The maximum price the package can pack is " + result);
        System.out.print("The result is ");
        for (int i = 1; i < bestX.length; i++) {
            if (bestX[i] == 1) {
                System.out.print("(W:" + w[i] + ", P:" + p[i] + ")");
            }
        }
        System.out.println();

    }
}
