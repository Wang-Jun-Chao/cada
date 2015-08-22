package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-04
 * Time: 16:19
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        // 集装箱重量数组
        int[] w = new int[]{10, 40, 40, 23, 41, 12, 6, 78, 44, 154};
        int c1 = 250;
        int c2 = 200;
        int[] result = new int[w.length];

        int bestC1 = MaxLoading.maxLoading(w, c1, result);

        int weight = 0;
        for (int i: w) {
            weight += i;
        }

        if (c2 < weight - bestC1) {
            System.out.println("No solution.");
            return;
        }

        System.out.println("c1 can load: " + bestC1);
        System.out.print("c1 contains container: ");
        for (int i = 0; i <result.length; i++) {
            if (result[i] != 0){
                System.out.print(i + " ");
            }
        }

        System.out.println();
        System.out.println("c2 can load: " + (weight - bestC1));
        System.out.print("c2 contains container: ");
        for (int i = 0; i <result.length; i++) {
            if (result[i] == 0){
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }
}
