package com.king;

/**
 * 并查集算法
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-03
 * Time: 13:44
 * Declaration: All Rights Reserved !!!
 */
public final class UnionFind {
    private int[] id; // 顶点集合，parent[i]表示顶点i连通到顶点parent[i]
    private int[] rank; // 顶点所在连通分量标号集合，rank[i]表示顶点i所在的分量编号为rank[i]
    private int count; // 连通分量的数目

    /**
     * 构造方法
     *
     * @param num 顶点的数目
     */
    public UnionFind(int num) {
        count = num; // 初始化分量数，开始时一个顶点代表一个分量
        id = new int[num]; // 初始化顶点数组
        rank = new int[num]; // 初始化顶点所在连通分量标号集合
        for (int i = 0; i < num; i++) {
            id[i] = i; // 开始是顶点只是自己到自己
            rank[i] = 0; // 每个顶点的连通分量标号为0
        }
    }

    /**
     * 找p所在的连通分量的标识符
     * @param p 顶点的编号（0到数组长度减1）
     * @return p所在的连通分量的标识符（0到数组长度减1）
     */
    public int find(int p) {
        while (p != id[p]) {
            id[p] = id[id[p]];
            p = id[p];
        }

        return p;
    }

    /**
     * 获取连通分量的个数
     * @return 连通分量的个数
     */
    public int count() {
        return count;
    }

    /**
     * 判断两个顶点是否在同一个连通分量上
     * @param p 顶点
     * @param q 顶点
     * @return true：两个顶点在同一个连通分量上，false不在
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * 在顶点p和顶点q之间添加一条连接
     * @param p 顶点
     * @param q 顶点
     */
    public void union(int p, int q) {
        int i = find(p); // 找p所在的连通分量
        int j = find(q); // 找q所在的连通分量

        if (i == j) { // 相等说明在同一个连通分量上，不用添加连接了
            return;
        }

        // 让较小连通分量标号的根指向较大连通分量标号的根
        if (rank[i] < rank[j]) {
            id[i] = j;
        } else if (rank[i] > rank[j]) {
            id[j] = i;
        } else {
            rank[j] = i;
            rank[i]++;
        }

        count--; // 连通分量数减少
    }

}
