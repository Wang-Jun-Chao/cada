package com.king;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 王俊超
 * Date: 2015-01-06
 * Time: 14:34
 * Declaration: All Rights Reserved !!!
 */
public final class Main {

    public static void main(String[] args) {
        final List<int[]> result = new ArrayList<int[]>();
        final int n = 8;
        int sum = Queen.solve(n, result);

        System.out.println(n + " Queen");
        System.out.println("The number of solutions is: " + sum);
        System.out.println("Solutions are: ");

        for (int[] r : result) {
            for (int i = 1; i < r.length; i++) {
                System.out.print("(" + i + "," + r[i] + ")");
            }
            System.out.println();
        }
    }
}
