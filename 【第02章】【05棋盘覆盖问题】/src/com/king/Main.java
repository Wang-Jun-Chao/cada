package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-02
 * Time: 08:51
 * Declaration: All Rights Reserved !!!
 */
public class Main {
    public static void main(String[] args) {
        final int[][] board = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        ChessBoard.chessBoard(0, 0, 1, 3, board.length, board);

        for (int[] line : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(line[j] + " ");
            }
            System.out.println();
        }
    }
}
