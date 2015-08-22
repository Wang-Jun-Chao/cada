package com.king;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 装载问题，时间与空间复杂度都是O(2^n)
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-14
 * Time: 21:05
 * Declaration: All Rights Reserved !!!
 */
public final class MaxLoading {

    private MaxLoading() {
        throw new RuntimeException("The class com.com.king.MaxLoading should not be initialized.");
    }
    /**
     * 解决装载问题的入口
     *
     * @param w 集装箱重量数组
     * @param c 当前船的载重量
     * @return 船实际最大可以装载的重量
     */
    public static Integer solve(Integer[] w, Integer c) {
        Queue<Integer> Q = new ArrayDeque<>();  // 活动结点队列，用就存储当前处理时的载重量
        Q.add(-1);                              // 同层结点尾部标志
        int i = 0;                              // 当前扩展节点所处的层
        int Ew = 0;                             // 招展节点所相应的载重量
        int bestW = 0;
        int r = 0;                              // 剩余集装箱的重量

        for (int j = 1; j < w.length; j++) {
            r += w[j]; // 第一个集装箱已经被使用了，不必计算
        }

        // 搜索子集空间树
        while (true) {
            // 检查左儿子节点
            int wt = Ew + w[i]; // 左儿子节点的重量，即：当前集装箱装上船后的总载重量
            if (wt <= c) { // 如果加入当前的处理的集装箱，没有有超过船的载重量，就进行入队操作
                if (wt > bestW) {
                    bestW = wt;
                }
                // 如果不是最后一个节点，就将当前的载重量装入队列
                if (i < w.length - 1) {
                    Q.add(wt);
                }
            }


            // 检查右子节点，即：不加入当前集装箱，进入入队操作
            // Ew+r：当前装载重量加上剩余可用的重量，
            // 只有他们的和大于当前的记录的最优载重量才可能在接下来的搜索中找到最优的载重量
            if (Ew + r > bestW && i < w.length - 1) {
                Q.add(Ew);
            }

            Ew = Q.remove(); // 获取下一个扩展节点

            if (Ew == -1) { // 如果同一层的节点已经处理完了
                if (Q.isEmpty()) { // 队列已经为空
                    return bestW; // 返回最优值
                }

                Q.add(-1); // 加入新的同层尾部标志
                Ew = Q.remove(); // 取下一层的扩展节点
                i++; // 进入下一层
                r -= w[i]; // 剩余集装箱的重量
            }
        }
    }
}
