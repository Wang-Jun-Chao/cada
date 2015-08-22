package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-12
 * Time: 09:46
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        final int n = 5;
        final int m = 4;
        int[] bestX = new int[n + 1];

        System.out.println("The kinds of stamps are " + n);
        System.out.println("The max stamp number is " + m);


        int result = MaxStamp.solve(n, m, bestX);

        System.out.println("The max stamp value is " + result);
        System.out.print("The best solution is ");
        for (int i = 1; i <= n ; i++) {
            System.out.print(bestX[i] + " ");
        }
    }
}
