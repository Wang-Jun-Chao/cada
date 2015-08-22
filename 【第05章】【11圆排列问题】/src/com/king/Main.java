package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-11
 * Time: 09:00
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        test1();
//        test2();
    }


    private static void test1() {
        final double[] r = {0, 76, 58, 1, 2}; // 第一个元素不使用
        final int n = 4;
        final double[] cordX = new double[n + 1];
        final double[] nr = new double[n + 1];


        System.out.println("The cycle's radius are ");
        for (int i = 1; i <= n; i++) {
            System.out.print(r[i] + " ");
        }
        System.out.println();

        double result = CycleArrangement.solve(n, r, cordX, nr);
        System.out.println("After arrangement the min length is " + result);

        System.out.println("The arrangement is");
        for (int i = 1; i <= n; i++) {
            System.out.println("(N:" + i + ", R:" + cordX[i] + ", C:" + nr[i] + ")");
        }
        System.out.println();
    }

    private static void test2() {
        final double[] r = {0, 1, 1, 2}; // 第一个元素不使用
        final int n = 3;
        final double[] cordX = new double[n + 1];
        final double[] nr = new double[n + 1];

        System.out.println("The cycle's radius are ");
        for (int i = 1; i <= n; i++) {
            System.out.print(r[i] + " ");
        }
        System.out.println();

        double result = CycleArrangement.solve(n, r, cordX, nr);
        System.out.println("After arrangement the min length is " + result);

        System.out.println("The arrangement is");
        for (int i = 1; i <= n; i++) {
            System.out.println("(N:" + i + ", R:" + cordX[i] + ", C:" + nr[i] + ")");
        }
        System.out.println();
    }

}
