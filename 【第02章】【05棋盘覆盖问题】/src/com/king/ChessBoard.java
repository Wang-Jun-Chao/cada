package com.king;

/**
 * 棋盘覆盖问题
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-02
 * Time: 08:51
 * Declaration: All Rights Reserved !!!
 */
public class ChessBoard {
    private static int TILE = 1; // 骨牌编号

    private ChessBoard() {
        throw new RuntimeException("The class com.com.king.ChessBoard should not be initialized.");
    }

    /**
     * 棋盘覆盖问题
     *
     * @param tr    棋盘左上角的行号
     * @param tc    棋盘左上角的列号
     * @param dr    特殊方格左上角的行号
     * @param dc    特殊方格左上角的行号
     * @param size  棋盘大小，size = 2^k 棋盘规格为2^k*2^k
     * @param board 棋盘
     */
    public static void chessBoard(int tr, int tc, int dr, int dc, int size, int[][] board) {

        if (size == 1) {
            return;
        }

        int t = TILE++;
        int s = size / 2;

        // 覆盖左上角子棋盘
        if (dr < tr + s && dc < tc + s) {// 特殊方格在此棋盘中
            chessBoard(tr, tc, dr, dc, s, board);
        } else { // 特殊方格不在此棋盘中
            board[tr + s - 1][tc + s - 1] = t; // 用编号为t的骨牌覆盖右下角
            chessBoard(tr, tc, tr + s - 1, tc + s - 1, s, board); // 覆盖其余方格
        }

        if (dr < tr + s && dc >= tc + s) { // 特殊方格在此棋盘中
            chessBoard(tr, tc + s, dr, dc, s, board);
        } else { // 特殊方格不在此棋盘中
            board[tr + s - 1][tc + s] = t; // 用编号为t的骨牌覆盖左下角
            chessBoard(tr, tc + s, tr + s - 1, tc + s, s, board); // 覆盖其余方格
        }

        if (dr >= tr + s && dc < tc + s) { // 特殊方格在此棋盘中
            chessBoard(tr + s, tc, dr, dc, s, board);
        } else { // 特殊方格不在此棋盘中
            board[tr + s][tc + s - 1] = t; // 用编号为t的骨牌覆盖右上角
            chessBoard(tr + s, tc, tr + s, tc + s - 1, s, board); // 覆盖其余方格
        }

        if (dr >= tr + s && dc >= tc + s) { // 特殊方格在此棋盘中
            chessBoard(tr + s, tc + s, dr, dc, s, board);
        } else { // 特殊方格不在此棋盘中
            board[tr + s][tc + s] = t; // 用编号为t的骨牌覆盖左上角
            chessBoard(tr + s, tc + s, tr + s, tc + s, s, board); // 覆盖其余方格
        }
    }
}
