package com.king;

/**
 * 旅行售货员问题
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-25
 * Time: 10:02
 * Declaration: All Rights Reserved !!!
 */
public final class Traveling {


    private static class MinHeapNode implements Comparable<MinHeapNode> {
        private int lCost;  // 子树费用的政界
        private int cc;     // 当前费用
        private int rCost;  // x[s:n-1]中顶点最小出边费用和
        private int s;      // 当前正的处理的的第s（从0开始）个节点，根节点到当前顶点的路径为x[0:s]
        private int[] x;    // 保存节点处理的秦顺序

        /**
         * 比较函数，lCost值进行比较，lCost值较小的小
         * @param o 另一个比较对象
         * @return 比较结果
         */
        @Override
        public int compareTo(MinHeapNode o) {
            if (o == null) {
                return 1;
            } else {
                return this.lCost - o.lCost;
            }
        }
    }

    private Traveling() {
        throw new RuntimeException("The class com.com.king.Traveling should not be initialized.");
    }


    /**
     * 解旅行售货员问题的优先队列式分支限界法，注意：使用0作为无边标记
     *
     * @param a 图的邻接矩阵
     * @param n 图的顶点数
     * @param v 保存最优解的数组
     * @return 最优值
     */
    public static int solve(int[][] a, int n, int[] v) {
        final int NO_EDGE = 0; // 无边标记
        MinHeap<MinHeapNode> H = new MinHeap<>(64); // 最小堆
        int[] minOut = new int[n + 1]; // 保存顶点的最小出边费用，第i个顶点的最小出边费用保存在minOut[i]中
        int minSum = 0; // 最小出边和

        // 计算每个顶点的最小出边费用，和各个顶点的最小出边费用和
        // 如果有顶点没有出边，直接退出方法
        for (int i = 1; i <= n; i++) {
            int min = NO_EDGE;
            for (int j = 1; j <= n; j++) {
                if (a[i][j] != NO_EDGE && (a[i][j] < min || min == NO_EDGE)) {
                    min = a[i][j];
                }
            }

            if (min == NO_EDGE) {
                return NO_EDGE;
            }

            minOut[i] = min;
            minSum += min;
        }

        MinHeapNode E = new MinHeapNode();

        E.x = new int[n];
        for (int i = 0; i < n; i++) {
            E.x[i] = i + 1; // 初始化第i次搜索的顶点
        }

        E.s = 0; // 当前处理的是第E.s个节点
        E.cc = 0; // 当前费用0
        E.rCost = minSum; // 当前的最小费
        int bestC = NO_EDGE; // 当前的最优值

        // 搜索排列空间树
        while (E.s < n - 1) {
            if (E.s == n - 2) { // 当前扩展结点是叶节点的父节点
                // 再加上2条边构成回路
                // 所构成的回路是否优于当前最优解
                // a[E.x[n - 2]][E.x[n - 1]] != NO_EDGE：第E.x[n - 2]个顶点到第E.x[n - 1]个顶点有边
                // a[E.x[n - 1]][1] != NO_EDGE：到E.x[n - 1]到第一个顶点有边
                // E.cc + a[E.x[n - 2]][E.x[n - 1]] + a[E.x[n - 1]][1] < bestC：新的最优值小于当前记录的最优值
                // bestC == NO_EDGE：最优值不是初始值
                if (a[E.x[n - 2]][E.x[n - 1]] != NO_EDGE && a[E.x[n - 1]][1] != NO_EDGE
                        && (E.cc + a[E.x[n - 2]][E.x[n - 1]] + a[E.x[n - 1]][1] < bestC || bestC == NO_EDGE)) {
                    bestC = E.cc + a[E.x[n - 2]][E.x[n - 1]] + a[E.x[n - 1]][1]; // 记录最优值
                    E.cc = bestC; // 记录最优值
                    E.lCost = bestC; // 记录花费的下界
                    E.s++; // 标记将要处理下一个顶点
                    H.add(E); // 将顶点放入小顶堆中
                } else {
                    E.x = null;
                }
            } else { // 产生当前扩展节点的儿子节点
                for (int i = E.s + 1; i < n; i++) { // 对当前处理节点（即：第E.s个节点）之后的操作节点进行操作
                    // a[E.x[E.s]][E.x[i]] != NO_EDGE：第E.s个顶点到第E.x个顶点有边
                    if (a[E.x[E.s]][E.x[i]] != NO_EDGE) {
                        // 计算当前的旅行的费用
                        // E.cc：表示：从第1个顶点走到第s个顶点的费用，
                        // a[E.x[E.s]][E.x[i]]从编号E.s的顶点到编号E.x[i]的顶点的费用
                        int cc = E.cc + a[E.x[E.s]][E.x[i]];
                        // E.x[E.s]：第E.s个顶点的顶点编号是E.x[E.s]
                        // E.rCost - minOut[E.x[E.s]]：未处理过的顶点的最小出边和
                        int rCost = E.rCost - minOut[E.x[E.s]];
                        int b = cc + rCost; // 最小费用下界
                        if (b < bestC || bestC == NO_EDGE) { // 费用下界小于当前记录的最优值，或者最优值是初始值
                            MinHeapNode N = new MinHeapNode(); // 创建一个小顶堆节点
                            N.x = new int[n]; // 创建数组，用于记录旅行的顺序

                            System.arraycopy(E.x, 0, N.x, 0, n); // 复制旅行的顺序

                            // 记录旅行的顺序，即第E.s+1步旅行到了E.x[i]顶点，将它记录在N.x[s+1]中
                            // N.x[i]中记录原来E.s+1步的顶点
                            N.x[E.s + 1] = E.x[i];
                            N.x[i] = E.x[E.s + 1];

                            N.s = E.s + 1; // 当前的第几步
                            N.cc = cc; // 记当前的最优值
                            N.lCost = b; // 记录当前的价值下界
                            N.rCost = rCost; // 未处理过的顶点的最小出边和
                            H.add(N); // 添加到最小堆中
                        }
                    }
                }
                E.x = null;
            }

            if (H.isEmpty()) { // 如果堆为空就跳出循环
                break;
            } else { // 否则删除堆顶元素
                E = H.deleteTop();
            }
        }

        // 如果没有结果就返回无边标记
        if (bestC == NO_EDGE) {
            return NO_EDGE;
        }

        System.arraycopy(E.x, 0, v, 1, n); // 复制记录的旅行顺序

        E.x = null;
        H.clear();

        return bestC;
    }
}
