package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-14
 * Time: 21:05
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        // 集装箱重量数组
        Integer[] w = new Integer[]{10, 40, 40, 23, 41, 12, 6, 78, 44, 154};
        int c1 = 250;
        int c2 = 200;

        int bestC1 = MaxLoading.solve(w, c1);
        System.out.println(bestC1);
    }

}
