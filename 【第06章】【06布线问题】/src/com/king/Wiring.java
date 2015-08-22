package com.king;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 布线问题
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-22
 * Time: 11:21
 * Declaration: All Rights Reserved !!!
 */
public final class Wiring {
    /**
     * 方格的位置类
     */
    public static class Position {
        private int row;
        private int col;

        /**
         * 构造函数
         *
         * @param row 位置所在的行
         * @param col 位置所在的列
         */
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * 判断两个位置是否相等
         *
         * @param o 另一个位置
         * @return true：两个位置相等，false：两个位置不相等
         */
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Position)) return false;

            Position position = (Position) o;
            return col == position.col && row == position.row;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }

    private Wiring() {
        throw new RuntimeException("The class com.com.king.Wiring should not be initialized.");
    }

    /**
     * 计算从起始位置start到目标位置finish的最短布线路径
     *
     * @param grid   布线的约束方格，grid[x][y]取0表示可以布线，1表示不可以，grid的四周不可以布线
     * @param start  布线的起始位置
     * @param finish 布线的结束位置
     * @param path   存放布线结果
     * @return 是否可以布线，true：可以，false：不可以
     */
    public static boolean solve(int[][] grid, Position start, Position finish, Deque<Position> path) {

        // 如果起始点与终止点是同一个位置，直接返回结果
        if (start.equals(finish)) {
            path.add(start);
            return true;
        }


        Position[] offset = new Position[4]; //初始化相对位移
        offset[0] = new Position(0, 1); // 相对当前位置的右边
        offset[1] = new Position(1, 0); // 相对当前位置的下边
        offset[2] = new Position(0, -1); // 相对当前位置的上边
        offset[3] = new Position(-1, 0); // 相对当前位置的左边

        int neighbors = 4; // 一个位置的周围邻居数目：上，下，左，右

        Position here, nbr = null; // 用于记录当前位置和相邻的位置

        here = new Position(start.row, start.col); // 开始时当前位置是起始位置

        grid[start.row][start.col] = 2; // 将起始位置的标号记为2
        Deque<Position> Q = new LinkedList<>(); // 创建一个双向队列对象

        do {
            // 找当前位置可达的邻居
            for (int i = 0; i < neighbors; i++) {
                nbr = new Position(here.row + offset[i].row, here.col + offset[i].col); // 记录邻居
                if (grid[nbr.row][nbr.col] == 0) { // 如果邻居可达
                    grid[nbr.row][nbr.col] = grid[here.row][here.col] + 1; // 对邻居进行标记
                    if (nbr.equals(finish)) { // 如果邻居是终止位置，跳出for
                        break;
                    }

                    Q.addLast(nbr); // 将邻居添加到队列的末尾
                }
            } // end for

            if (nbr.equals(finish)) { // 如果已经到达终点退出do-while
                break;
            }

            if (Q.isEmpty()) { // 如果在还未搜索到终止位置，队列已经为空，说明两个位置之间无路径
                return false;
            }

            here = Q.removeFirst(); // 设置当前位置
        } while (true);

        int pathLen = grid[finish.row][finish.col] - 2; // 记录最短的路径长度

        here = finish; // 将当前位置设置为终点，返回搜索，找最短的路径

        for (int j = pathLen - 1; j >= 0; j--) {
            path.addFirst(here); // 将当前路径当回到结果集中
            for (int i = 0; i < neighbors; i++) {
                nbr = new Position(here.row + offset[i].row, here.col + offset[i].col);
                if (grid[nbr.row][nbr.col] == j + 2) {
                    break;
                }
            }

            here = nbr;
        }

        path.addFirst(start); // 将起点添加到结果集中

        return true;
    }
}
