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
    private static int bestW;   // 当前的最优值

    private MaxLoading() {
        throw new RuntimeException("The class com.com.king.MaxLoading should not be initialized.");
    }
    /**
     * 入队操作
     *
     * @param Q  入队的队列
     * @param wt 当前的载重量
     * @param i  当前处理的集装箱编号
     * @param n  最大的集装箱编号
     */
    private static void enQueue(Queue<Integer> Q, Integer wt, Integer i, Integer n) {
        if (i == n) { // 如果处理的是最后一个集装箱
            if (wt > bestW) { // 当前的载重量大于已经记录的最大载重量
                bestW = wt; // 记录新的最大载重量
            }
        } else { // 不是最后一个集装箱
            Q.add(wt); // 将当前的载重最入队
        }
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
        bestW = 0;                              // 当前最优的载重量

        // 搜索子集空间树
        while (true) {
            // 检查左儿子节点，如果加入当前的处理的集装箱，没有有超过船的载重量，就进行入队操作
            if (Ew + w[i] <= c) {
                enQueue(Q, Ew + w[i], i, w.length - 1);
            }

            // 不加入当前集装箱，进入入队操作
            enQueue(Q, Ew, i, w.length - 1);

            Ew = Q.remove(); // 获取下一个扩展节点

            if (Ew == -1) { // 如果同一层的节点已经处理完了
                if (Q.isEmpty()) { // 队列已经为空
                    return bestW; // 返回最优值
                }

                Q.add(-1); // 加入新的同层尾部标志
                Ew = Q.remove(); // 取下一层的扩展节点
                i++; // 进入下一层
            }
        }
    }
}
