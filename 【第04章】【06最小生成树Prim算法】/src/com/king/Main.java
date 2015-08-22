package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-03
 * Time: 09:57
 * Declaration: All Rights Reserved !!!
 */
public final class Main {
    public static void main(String[] args) {
        //              ①
        //            /  |  \
        //           6   1   5
        //          /    |    \
        //        ②-5--③--5--④
        //         \    /\    /
        //          3  6  4  2
        //           \/    \/
        //           ⑤--6-⑥
        //最小生成树为：
        //              ①
        //               |
        //               1
        //               |
        //        ②-5--③     ④
        //         \     \    /
        //          3     4  2
        //           \     \/
        //           ⑤    ⑥
        //

        int m = Integer.MAX_VALUE;
        int[][] weight = {
                {0, 0, 0, 0, 0, 0, 0},
                {0, m, 6, 1, 5, m, m},
                {0, 6, m, 5, m, 3, m},
                {0, 1, 5, m, 5, 6, 4},
                {0, 5, m, 5, m, m, 2},
                {0, m, 3, 6, m, m, 6},
                {0, m, m, 4, 2, 6, m}};//上图的矩阵
        Prim.prim(weight.length - 1, weight);
    }
}
