package com.king;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Author: 王俊超
 * Date: 2015-01-22
 * Time: 11:21
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        test2();
    }

    private static void test1() {
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 1, 1, 0, 0, 1},
                {1, 1, 0, 0, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        Wiring.Position start = new Wiring.Position(3, 2);
        Wiring.Position finish = new Wiring.Position(4, 6);
        Deque<Wiring.Position> bestX = new LinkedList<>();

        boolean result = Wiring.solve(grid, start, finish, bestX);

        System.out.println(result);
        System.out.println(bestX);
    }

    private static void test2() {
        // 图和起始位置不同
        int[][] grid = {
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 1, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 1, 0, 0, 1},
                {1, 0, 0, 0, 1, 1, 0, 0, 1},
                {1, 1, 0, 0, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1},
        };

        Wiring.Position start = new Wiring.Position(3, 4);
        Wiring.Position finish = new Wiring.Position(4, 6);
        Deque<Wiring.Position> bestX = new LinkedList<>();

        boolean result = Wiring.solve(grid, start, finish, bestX);

        System.out.println(result);
        System.out.println(bestX);
    }

}
