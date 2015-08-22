package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-03
 * Time: 12:56
 * Declaration: All Rights Reserved !!!
 */
public final class Kruskal {
    private Kruskal() {
        throw new RuntimeException("The class com.com.king.Kruskal should not be initialized.");
    }


    public static boolean kruskal(int n, int e, EdgeNode[] E, EdgeNode[] t) {
        MinHeap<EdgeNode> minHeap = new MinHeap<>(e);
        // 初始化最小堆
        for (int i = 1; i <= e; i++) {
            minHeap.add(E[i]);
        }

        UnionFind unionFind = new UnionFind(n);

        int k = 1;
        while (e > 0 && k < n - 1) {
            EdgeNode x;
            x = minHeap.deleteTop();
            e--;

            int a = unionFind.find(x.getU());
            int b = unionFind.find(x.getV());

            if (a != b) {
                t[k++] = x;
                unionFind.union(a, b);
            }
        }

        return k == n - 1;
    }
}
