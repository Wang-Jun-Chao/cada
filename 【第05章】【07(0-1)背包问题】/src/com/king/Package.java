package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-06
 * Time: 16:22
 * Declaration: All Rights Reserved !!!
 */
public class Package {
    private static double c;        // 背包容量
    private static int n;           // 物品数
    private static double[] w;      // 物品重量数组
    private static double[] p;      // 物品价值数组
    private static double cw;       // 当前重量
    private static double cp;       // 当前价值
    private static double bestP;    // 当前最后价值

    private Package() {
        throw new RuntimeException("The class com.com.king.Package should not be initialized.");
    }

    /**
     * 内部类，用于保存每种物品的单价
     */
    private static class Unit implements Comparable<Unit> {
        private int id;             // 物品的编号
        private double unitPrice;   // 物品的单价

        public Unit() {
        }

        public Unit(int id, double unitPrice) {
            this.id = id;
            this.unitPrice = unitPrice;
        }

        /**
         * 两对象比较
         *
         * @param o 待比较对象
         * @return 1：当前对象比o小，-1：当前对象比o大，0：两者相等
         */
        @Override
        public int compareTo(Unit o) {
            if (o == null) {
                return -1;
            } else {
                if (this.unitPrice > o.unitPrice) {
                    return -1;
                } else if (this.unitPrice < o.unitPrice) {
                    return 1;
                }
            }
            return 0;
        }
    }

    /**
     * 计算在已经装了部分物品的前提下，最多可以装多少重量
     *
     * @param i 当前处理的结点
     * @return 实际最多可以装多少重量
     */
    private static double bound(int i) {
        double cLeft = c - cw; // 剩余可装载的重量
        double b = cp; // 记录当前的价值，记录实际最多可以装多少重量

        // 以物品单位重量价值递减序装入物品
        while (i <= n && w[i] <= cLeft) {
            cLeft -= w[i]; // 减少剩余可装载的重量
            b += p[i]; // 增加实际最多可以装多少重量
            i++; // 处理下一个节点
        }

        // i<=n 说明有可能还有剩余的容量可以装载物品，cLeft != 0说明w[i]不能全部装入船
        if (i <= n) {
            b += p[i] * cLeft / w[i]; // 将w[i]的一部分装入船，其价值为 (p[i]*cLeft/w[i])
        }

        return b;
    }


    /**
     * 解决0-1背包问题的递归算法
     *
     * @param i 第i个背包
     */
    private static void traceBack(int i) {
        if (i > n) { // 到达左子树
            bestP = cp; // 保存最优记录
            return;
        }

        if (cw + w[i] <= c) { // 进入左子树
            cw += w[i]; // 增加当前重量
            cp += p[i]; // 增加当前价值
            traceBack(i + 1); // 处理第i+1个节点
            cw -= w[i]; // 还原当前重量
            cp -= p[i]; // 还原当前价值
        }

        if (bound(i + 1) > bestP) { // 如果可装载的价值大于当前的最优价值
            traceBack(i + 1); // 处理第i+1个节点
        }
    }


    /**
     * 0-1背包问题回溯法入口
     *
     * @param p   价值数组
     * @param w   重量数组
     * @param cap 船的载重量
     * @param num 集装箱的数目
     * @return 可以装载的最大价值
     */
    public static double solve(double[] p, double[] w, double cap, int num) {
        double W = 0; // 总的重量
        double P = 0; // 总的价值
        Unit[] Q = new Unit[num]; // 单价数组，按单价排序时使用

        for (int i = 1; i <= num; i++) {
            Q[i - 1] = new Unit(i, p[i] / w[i]); // 创建单价对象
            P += p[i]; // 计算总的重量
            W += w[i]; // 计算总的价值
        }

        if (W <= cap) { // 如果总的重量小于船的载重量，则可以装所有物品
            return P;
        }

        //  依物品单位重量价值排序
        MergeSort.mergeSort(Q);

//        for (Unit u: Q) { // 输出排序结果
//            System.out.print("(ID:" + u.id + ", UP:" + u.unitPrice + ")");
//        }
//        System.out.println();

        Package.p = new double[num + 1]; // 价值数组，
        Package.w = new double[num + 1]; // 重量数组，
        for (int i = 1; i <= num; i++) {
            Package.p[i] = p[Q[i - 1].id]; // 保存的价值按单价从大到小排序
            Package.w[i] = w[Q[i - 1].id]; // 保存的重量按单价从大到小排序
        }

        Package.cp = 0;
        Package.cw = 0;
        Package.c = cap;
        Package.n = num;
        Package.bestP = 0;

        traceBack(1);

        Package.w = null;
        Package.p = null;

        return Package.bestP;


    }
}
