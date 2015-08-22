package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-11
 * Time: 15:01
 * Declaration: All Rights Reserved !!!
 */
public final class BoardArrangement {

    private static int n;       // 电路板数
    private static int m;       // 连接板数
    private static int[] x;     // 当前解
    private static int[] bestX; // 当前最优解
    private static int bestD;   // 当前最优密度
    private static int[] total; // total[j]=连接块j的电路板数
    private static int[] now;   // now[j]=当前解中所含连接块j的电路板数
    private static int[][] B;   // 连接块数组

    private BoardArrangement() {
        throw new RuntimeException("The class com.com.king.BoardArrangement should not be initialized.");
    }

    /**
     * 交换数组中两个元素的位置
     *
     * @param a 数组
     * @param x 位置
     * @param y 位置
     */
    private static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    /**
     * 电路板排列问题的递归算法
     *
     * @param i  第i个电路板
     * @param cd 当前的密度
     */
    private static void traceBack(int i, int cd) {
        if (i == n) { // 如果已经是最后一块电路板，说明所有的电路板已经安排好了
            System.arraycopy(x, 1, bestX, 1, n); // 保留最优值
            bestD = cd; // 保存最优解
        } else { // 处理未按排的电路板
            // [i:n]位置的电路板还没有处理要进行处理
            for (int j = i; j <= n; j++) {
                int ld = 0; // 用户保存新计算的部分排列的密度

                for (int k = 1; k <= m; k++) {
                    // 计算加入第j块电路板后Nk中电路板的数目
                    // x[j]：第j个插槽是电路板x[j]
                    // B[v][u]只取0或者1，当第v号电路板在Nj块中时取1，否则取0
                    now[k] += B[x[j]][k];

                    // 如果j加入，有线在已经排列好的部分外面，所以密度要加1
                    if (now[k] > 0 && total[k] != now[k]) {
                        ld++;
                    }
                }

                if (cd > ld) { // 如果新加入电路板j使密度比原的大，更新密度
                    ld = cd;
                }

                if (ld < bestD) { // 如果当前的密度比最优值小，就处理下一个电路板
                    swap(x, i, j);
                    traceBack(i + 1, ld);
                    swap(x, i, j);
                }

                // 还原操作
                for (int k = 1; k <= m; k++) {
                    now[k] -= B[x[j]][k];
                }
            }
        }
    }

    /**
     * 解决电路板排列问题的入口
     *
     * @param B     连接块数组，B[v][u]只取0或者1，当第v号电路板在Nj块中时取1，否则取0
     * @param n     电路板数
     * @param m     连接板数
     * @param bestX 最优解
     * @return 最优值
     */
    public static int solve(int[][] B, int n, int m, int[] bestX) {
        BoardArrangement.x = new int[n + 1];
        BoardArrangement.total = new int[n + 1];
        BoardArrangement.now = new int[n + 1];
        BoardArrangement.B = B;
        BoardArrangement.n = n;
        BoardArrangement.m = m;
        BoardArrangement.bestX = bestX;
        BoardArrangement.bestD = m + 1;

        for (int i = 1; i <= m; i++) {
            BoardArrangement.total[i] = 0;
            BoardArrangement.now[i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            BoardArrangement.x[i] = i;
            for (int j = 1; j <= m; j++) {
                BoardArrangement.total[j] += B[i][j];
            }
        }
        BoardArrangement.traceBack(1, 0);
        BoardArrangement.x = null;
        BoardArrangement.total = null;
        BoardArrangement.now = null;

        return BoardArrangement.bestD;
    }
}
