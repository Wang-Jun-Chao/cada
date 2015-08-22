package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-06
 * Time: 16:21
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {

        final int n = 4; // 物品数
        final int c = 7; // 背包容量
        final double[] p = {0, 9, 10, 7, 4}; // 物品价值 下标从1开始
        final double[] w = {0, 3, 5, 2, 1}; // 物品重量 下标从1开始

        System.out.println("The capacity of package is " + c);
        System.out.println("Goods' weight and price are");
        for (int i = 1; i <= n; i++) {
            System.out.print("(W:" + w[i] + ", P:" + p[i] + ")");
        }
        System.out.println();

        double result = Package.solve(p, w, c, n);
        System.out.println("The maximum price the package can pack is " + result);
    }
}
