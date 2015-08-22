package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-06
 * Time: 09:02
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {

        for (int i = 1; i <= 10; i++) {
            System.out.println("(N:" + i + ", S:" + Triangle.compute(i) + ")");
        }
    }
}
