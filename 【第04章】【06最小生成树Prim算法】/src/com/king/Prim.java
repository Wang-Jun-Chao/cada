package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-03
 * Time: 09:58
 * Declaration: All Rights Reserved !!!
 */
public final class Prim {
    private Prim() {
        throw new RuntimeException("The class com.com.king.Prim should not be initialized.");
    }

    /**
     * Prim最小生成树算法
     *
     * @param num    顶点个数组
     * @param weight 边权重数组
     */
    public static void prim(int num, int[][] weight) {
        // 其它顶点到部分最小生成树的不同顶点的最小权，即：V-S的顶点到S的了最小权
        int[] leastCost = new int[num + 1];
        // 其它顶点与S集合相连的最小权边的点，即：使得V-S的顶点到S取得最小权的S中的顶点
        int[] closest = new int[num + 1];
        // 标记顶点是否已经被访问过
        boolean[] visit = new boolean[num + 1];

        visit[1] = true; // 标记1号顶点已经补访问过
        for (int i = 2; i <= num; i++) {
            leastCost[i] = weight[1][i]; // 记录从1号顶点到其它顶点的代价
            closest[i] = 1; // 记录i是由1号到顶点直接访问到的
            visit[i] = false; // 标记其它顶点还没有被访问过
        }

        for (int i = 1; i < num; i++) {
            int min = Integer.MAX_VALUE;
            int j = 1;
            for (int k = 2; k <= num; k++) { // 找出V-S中使leastCost最小的顶点j，和最小的权
                if (leastCost[k] < min && !visit[k]) {
                    min = leastCost[k];
                    j = k;
                }
            }

            System.out.println("add vertex " + j + ". " + j + "---" + closest[j]);

            visit[j] = true; // 标记j已经被访问过了
            for (int k = 2; k <= num; k++) { // 将j添加到S中后，修改closest和leastCost的值
                // 为什么要执行下面的操作：原先j未加入顶点S集合时，k到S的权重最小值为leastCost[k]，
                // 如果leastCost[k]为经过其它的S中的顶点到达的，其权值有可能比weight[j][k]大，
                // 如果大的话就可以有通过j点到部分成树集合从而使leastCost[k]最小
                if (weight[j][k] < leastCost[k] && !visit[k]) {
                    leastCost[k] = weight[j][k];
                    closest[k] = j;
                }
            }
        }
    }
}
