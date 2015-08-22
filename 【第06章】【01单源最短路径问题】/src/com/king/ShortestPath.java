package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-13
 * Time: 09:39
 * Declaration: All Rights Reserved !!!
 */
public final class ShortestPath {

    private static int n;       // 图G的顶点数
    private static int[] prev;  // 前驱顶点数组
    private static int[][] c;   // 图G的领接矩阵
    private static int[] dist;  // 最短距离数组


    /**
     * 最小堆的结点
     */
    private static class MinHeapNode implements Comparable<MinHeapNode> {
        private int i;      // 顶点编号
        private int length; // 当前路长（就是从起始点到当前点走的路径长）

        @Override
        public int compareTo(MinHeapNode o) {
            if (o == null) {
                return 1;
            }
            return this.length - o.length;
        }

        @Override
        public String toString() {
            return "(I:" + i + ", L:" + length + ")";
        }
    }

    private ShortestPath() {
        throw new RuntimeException("The class com.com.king.ShortestPath should not be initialized.");
    }


    /**
     * 单源最短路径问题的优先队列式分支限界法
     *
     * @param v 起始顶点
     */
    private static void searchPath(int v) {
        MinHeap<MinHeapNode> H = new MinHeap<>(); // 创建一个最小堆数据结构
        MinHeapNode E = new MinHeapNode(); // 创建一个最小堆结点

        // 定义源为初始扩展节点
        E.i = v;
        E.length = 0;
        dist[v] = 0;


        // 搜索问题的解空间
        while (true) {
            for (int j = 1; j <= n; j++) {

                // c[E.i][j] != Integer.MAX_VALUE：说明第E.i个顶点与第j个顶点可达
                // E.length + c[E.i][j] < dist[j]：表示经过E.i顶点到j顶点比不经过E.i顶点到j路径短
                // E.i != j：不是自己到自己
                if (c[E.i][j] != Integer.MAX_VALUE && (E.length + c[E.i][j] < dist[j]) && E.i != j) {
                    dist[j] = E.length + c[E.i][j]; // 记录新的路径
                    prev[j] = E.i; // 记录前驱

                    MinHeapNode N = new MinHeapNode(); // 创建一个新的最小堆结点
                    N.i = j; // 记录顶点编号
                    N.length = dist[j]; // 记录从开始顶点到当前顶点的路径长度
                    H.add(N); // 插入到最小堆中

                } // end if
            } // end for

            try {
                E = H.deleteTop(); // 删除堆顶结点
            } catch (Exception e) {
                break;
            }

            if (H.size() == 0) {
                break;
            }
        } // end while
    }

    /**
     * 解决单源最短路径的入口
     *
     * @param n    顶点数目
     * @param v    顶点数组，下标为0的元素不使用
     * @param dist 顶点到各个顶点的距离数组，下标为0的元素不使用
     * @param prev 记录前驱顶点的数组，prev[i]表示源点经过prev[i]到i顶点的距离最短，下标为0的元素不使用
     * @param c    距离数组，c[i][j]表示顶点i直接到达顶点j的距离是c[i][j]，如果不可达就用整数的最大值表示，i>0，j>0
     */
    public static void solve(int n, int v, int[] dist, int[] prev, int[][] c) {
        // 初化操作
        ShortestPath.n = n;
        ShortestPath.dist = dist;
        for (int i = 1; i <= n; i++) {
            ShortestPath.dist[i] = Integer.MAX_VALUE;
        }
        ShortestPath.prev = prev;
        ShortestPath.c = c;

        searchPath(v);
    }
}
