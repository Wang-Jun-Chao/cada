package com.king;

import java.util.LinkedList;

/**
 * 0-1背包问题
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-22
 * Time: 16:14
 * Declaration: All Rights Reserved !!!
 */
public final class Knapsack {
    private static BBNode E;                // 指向扩展节点的指针
    private static int c;                   // 背包容量
    private static int n;                   // 物品数
    private static int[] w;                 // 物品重量数组
    private static int[] p;                 // 物品价值数组
    private static int cw;                  // 当前重量
    private static int cp;                  // 当前价值
    private static int[] bestX;             // 最优解
    private static Unit[] Q;                // 物品单价数组，按从大到小排序
    private static MaxHeap<HeapNode> H;     // 最顶堆


    private Knapsack() {
        throw new RuntimeException("The class com.com.king.Knapsack should not be initialized.");
    }

    /**
     * 单位重量类
     */
    private static class Unit implements Comparable<Unit> {
        private int id;     // 在原始数组中的位置
        private double d;   // 单位重量价值

        public Unit() {
        }

        public Unit(int id, double d) {
            this.id = id;
            this.d = d;
        }

        /**
         * 比较函数
         *
         * @param o 另一个比较对象
         * @return -1：当前对象大于另一个对象，0：当前对象等于另一个对象，+1：当前对象小于另一个对象
         */
        @Override
        public int compareTo(Unit o) {
            if (o == null) {
                return -1;
            }

            if (this.d > o.d) {
                return -1;
            } else if (this.d < o.d) {
                return +1;
            } else {
                return 0;
            }
        }
    }


    /**
     * 标记类
     */
    private static class BBNode {
        private BBNode parent;      // 指向父节点的指针
        private boolean lChild;     // 左儿子节点标识，当前节点是左结点

        public BBNode() {

        }

        public BBNode(BBNode parent, boolean lChild) {
            this.parent = parent;
            this.lChild = lChild;
        }
    }

    /**
     * 堆节点类
     */
    private static class HeapNode implements Comparable<HeapNode> {
        private double uProfit;     // 节点的价值上界
        private int profit;         // 节点所相应的价值
        private int weight;         // 节点所相应的重量
        private int level;          // 活节点在子集树中所处的层序号
        private BBNode ptr;         // 指向活节点在子集中相应节点的指针

        public HeapNode(double uProfit, int profit, int weight, int level, BBNode ptr) {
            this.uProfit = uProfit;
            this.profit = profit;
            this.weight = weight;
            this.level = level;
            this.ptr = ptr;
        }

        /**
         * 比较函数
         *
         * @param o 另一个比较对象
         * @return >= +1：当前对象大于另一个对象，0：当前对象等于另一个对象，<= -1：当前对象小于另一个对象
         */
        @Override
        public int compareTo(HeapNode o) {
            if (o == null) {
                return 1;
            }
            return this.profit - o.profit;
        }
    }

    /**
     * 计算i节点所相应价值的上界
     *
     * @param i 第i个节点
     * @return 价值的上界
     */
    private static double bound(int i) {
        double cLeft = c - cw; // 剩余容量高
        double b = cp; // 价值上界

        // 以物品单位重量价值递减序装填剩余容量
        while (i <= n && w[i] <= cLeft) {
            cLeft -= w[i];
            b += p[i];
            i++;
        }

        // 装填剩余容量装满背包
        if (i <= n) {
            b += cLeft * p[i] / w[i];
        }

        return b;
    }


    /**
     * 将一个活节点插入到子集树和优先队列中
     *
     * @param up  价值上界
     * @param cp  当前的价值
     * @param cw  当前的载重重
     * @param ch  false：右孩子，true：左孩子
     * @param lev 当前处理是的第几层
     */
    private static void addLiveNode(double up, int cp, int cw, boolean ch, int lev) {
        BBNode b = new BBNode(E, ch);
        HeapNode N = new HeapNode(up, cp, cw, lev, b);
        H.add(N);
    }

    /**
     * 优先队列式分支限界法，返回最大价值，bestX返回最优解
     *
     * @return 返回最优值
     */
    private static int maxKnapsack() {
        // 初始化操作
        int i = 1; // 最开始处理的层数
        E = null;
        cp = 0; // 最开始背包的所有物品的价值
        cw = 0; // 最开始背包的所有物品的重量
        int bestP = 0; // 开始的最优值
        double up = bound(1); // 开始的价值上界

        // 搜索子集空间树
        while (i != n + 1) {
            int wt = cw + w[i]; // 添加物品i后，背包总重量
            if (wt <= c) { // 未超出背包容量
                if (cp + p[i] > bestP) { // 加入物品后使用得价值增加
                    bestP = cp + p[i]; // 重新计算最优值
                }
                addLiveNode(up, cp + p[i], cw + w[i], true, i);
            }

            up = bound(i + 1); // 重新计算价值上界
            if (up >= bestP) {
                addLiveNode(up, cp, cw, false, i);
            }

            HeapNode N = H.deleteTop(); // 删除堆顶元素
            E = N.ptr;
            cw = N.weight;
            cp = N.profit;
            up = N.uProfit;
            i = N.level + 1; // 处理下一层
        }

        // 问题的最优解
        for (int j = n; j > 0; j--) {
            bestX[Q[j- 1].id ] = E.lChild ? 1 : 0;;
            E = E.parent;
        }
        return cp;
    }

    public static int solve(int[] p, int[] w, int c, int n, int[] bestX) {
        int W = 0;
        int P = 0;
        H = new MaxHeap<>(64);

         Q = new Unit[n];
        for (int i = 1; i <= n; i++) {
            Q[i - 1] = new Unit(i, 1.0 * p[i] / w[i]);
            P += p[i];
            W += w[i];
        }

        Sort.mergeSort(Q); // 是以单价从大到小排序的

        Knapsack.p = new int[n + 1];
        Knapsack.w = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            Knapsack.p[i] = p[Q[i - 1].id];
            Knapsack.w[i] = w[Q[i - 1].id];
        }

        Knapsack.cp = 0;
        Knapsack.cw = 0;
        Knapsack.c = c;
        Knapsack.n = n;

        Knapsack.bestX = bestX;
        int bestP = maxKnapsack();

        Knapsack.w = null;
        Knapsack.p = null;
        Knapsack.bestX = null;
        Knapsack.H = null;
        Knapsack.Q = null;

        return bestP;
    }
}
