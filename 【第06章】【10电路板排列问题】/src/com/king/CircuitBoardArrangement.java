package com.king;

/**
 * 电路板排列问题
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-26
 * Time: 18:19
 * Declaration: All Rights Reserved !!!
 */
public final class CircuitBoardArrangement {
    /**
     * 电板节点类，用于保存处理过程中产生的中间结果
     */
    private static class BoardNode implements Comparable<BoardNode> {
        private int[] x;    // x[1:n]记录电路板排列
        private int s;      // x[1:s]是当前节点所相应的部分排列
        private int cd;     // x[1:s]的密度
        private int[] now;  // now[j]是x[1:s]所含连接块j中电路板数

        /**
         * 比较方法，根据cd值进行比较
         *
         * @param o 另一个比较对象
         * @return 比较结果
         */
        @Override
        public int compareTo(BoardNode o) {
            if (o == null) {
                return 1;
            } else {
                return this.cd - o.cd;
            }
        }
    }

    private CircuitBoardArrangement() {
        throw new RuntimeException("The class com.com.king.CircuitBoardArrangement should not be initialized.");
    }

    /**
     * 找两个数中的较大的值
     *
     * @param a 一个数
     * @param b 一个数
     * @return 较大的那一个
     */
    private static int max(int a, int b) {
        return a >= b ? a : b;
    }

    public static int solve(int[][] B, int n, int m, int[] bestX) {
        // 初始化操作
        MinHeap<BoardNode> H = new MinHeap<>(64); // 不顶堆
        BoardNode E = new BoardNode(); // 创建一个板节点对象
        E.x = new int[n + 1]; // 记录排列结果的对象
        E.s = 0; // 当前处理的节点是第多少个
        E.cd = 0; // 已经处理的完的节点中的最大密度
        E.now = new int[m + 1]; // E.now[i] = x[1:s]所含连接块i中电路板数

        int[] total = new int[m + 1]; // 所有电路板中，total[i] = 连接块i中的电路板数

        for (int i = 1; i <= m; i++) {
            total[i] = 0;
            E.now[i] = 0;
        }

        for (int i = 1; i <= n; i++) {
            E.x[i] = i;  // 初始排列为1, 2, 3, ..., n
            for (int j = 1; j <= m; j++) {
                total[j] += B[i][j]; // 连接块中电路板数
            }
        }


        int bestD = m + 1;

        do { // 节点扩展
            if (E.s == n - 1) { // 仅一个儿子节点未处理的情况

                int ld = 0; // 最后一块电路板加入后产生的密度
                for (int j = 1; j <= m; j++) { // 计算最后一块插槽插入后的密度
                    ld += B[E.x[n]][j]; // x[n]：第n个插槽的插入的电路板是x[n]
                }
                if (ld < bestD) { // 如果最后加入的电路板不使密码增大，则是一个最优解
                    System.arraycopy(E.x, 0, bestX, 0, E.x.length);
                    bestD = max(ld, E.cd);
                }
            } else { // 产生当前扩展节点的所有儿子节点，即处理的不是最后一个插槽
                // [E.s+1, n]是未处理过的插槽
                for (int i = E.s + 1; i <= n; i++) {
                    BoardNode N = new BoardNode(); // 创建一个板节点
                    N.now = new int[m + 1];
                    for (int j = 1; j <= m; j++) {
                        N.now[j] = E.now[j] + B[E.x[i]][j]; // 新插入的电路板，电路板插在插槽第i个位置
                    }

                    int ld = 0; // 新插入的电路板密度
                    for (int j = 1; j <= m; j++) {
                        // N.now[j] > 0：有属于j组的电路板
                        // total[j] != N.now[j]：TODO 作用未知
                        // N.now[j] > 0 && total[j] != N.now[j]：说明在第i个插槽插入x[i]，使得对应组中的电路板数增加
                        if (N.now[j] > 0 && total[j] != N.now[j]) {
                            ld++;
                        }
                    }

                    N.cd = max(ld, E.cd); // 找较大的密度
                    if (N.cd < bestD) { // 如果新加入的电路板的密码小于已经记录的最大密度
                        N.x = new int[n + 1];
                        N.s = E.s + 1; // 标记下一个将要处理的节点

                        System.arraycopy(E.x, 1, N.x, 1, n); // 复制电路板排列情况

                        // 下面两句本质是交换插槽中电路板的位置
                        N.x[N.s] = E.x[i];
                        N.x[i] = E.x[N.s];

                        H.add(N); // 将中间处理结果放入优先级队列中
                    }
                }
            }

            if (H.isEmpty()) {
                return bestD;
            } else {
                E = H.deleteTop();
            }

        } while (E.cd < bestD);

        return bestD;
    }
}
