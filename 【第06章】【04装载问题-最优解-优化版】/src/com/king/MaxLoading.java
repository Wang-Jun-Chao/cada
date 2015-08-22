package com.king;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
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
    private static int n;               // 当明的最后一个节点位置
    private static int bestW;           // 当前的最优值
    private static Deque<QNode> queue;  // 活动节点队列
    private static QNode bestE;         // 当前最优扩展节点
    private static boolean[] bestX;     // 当前最优解

    /**
     * 用于构造最优解而创建的节点类
     */
    private static class QNode {
        private QNode parent;   // 指向父亲节点的引用
        private boolean lChild; // 左儿子节点的标志，当为true说明当装上船
        private int weight;     // 结点所对应的载重量（累计重量）
        private int index;      // 当前处理的节点编号

        public QNode() {
        }

        public QNode(QNode parent, boolean lChild, int weight, int index) {
            this.parent = parent;
            this.lChild = lChild;
            this.weight = weight;
            this.index = index;
        }

        @Override
        public String toString() {
            return "(I:" + index + ", L:" + lChild + ", W:" + weight + ")->" + parent;
        }
    }

    private MaxLoading() {
        throw new RuntimeException("The class com.com.king.MaxLoading should not be initialized.");
    }


    private static void enQueue(int wt, int i, QNode parent, boolean ch) {
//        System.out.println("(wt:" + wt + ", bestX:" + bestW + ")(i:" + i + ", n:" + n + ")");

        QNode b = new QNode(parent, ch, wt, i);
        queue.addLast(b);


        if (i == n) { // 最后一个节点
            if (wt == bestW) { // 节点可行
                bestE = parent; // 前当最优载重量
                bestX[n] = ch; // 标记当前节点是否加入到最解中

                // 记录最优解
                while (b.parent != null) {
                    bestX[b.index] = b.lChild;
                    b = b.parent;
                }
            }
        }
    }

    /**
     * 解决装载问题的入口
     *
     * @param w 集装箱重量数组
     * @param c 当前船的载重量
     * @return 船实际最大可以装载的重量
     */
    public static Integer solve(Integer[] w, Integer c, boolean[] bestX) {
        // 初始化
        n = w.length - 1;
        bestW = 0;
        queue = new LinkedList<>(); // 活动结点队列，用就存储当前处理时的载重量
        queue.add(null); // 同层结点尾部标志
        QNode e = null; // 当前扩展节点
        bestE = null; // 当前最优扩展节点
        MaxLoading.bestX = bestX;
        for (int j = 0; j < bestX.length; j++) { // 最优解初始化
            bestX[j] = false;
        }


        int i = 0; // 当前扩展节点所处的层
        int ew = 0; // 招展节点所相应的载重量
        int r = 0; // 剩余集装箱的重量

        for (int j = 1; j <= n; j++) {
            r += w[j]; // 第一个集装箱已经被使用了，不必计算
        }

        // 搜索子集空间树
        while (i <= n) {
            // 检查左儿子节点
            int wt = ew + w[i]; // 左儿子节点的重量，即：当前集装箱装上船后的总载重量
            if (wt <= c) { // 如果加入当前的处理的集装箱，没有有超过船的载重量，就进行入队操作
                if (wt > bestW) {
                    bestW = wt;
                }
                enQueue(wt, i, e, true);
            }

            // 检查右子节点，即：不加入当前集装箱，进入入队操作
            // Ew+r：当前装载重量加上剩余可用的重量，
            // 只有他们的和大于当前的记录的最优载重量才可能在接下来的搜索中找到最优的载重量
            if (ew + r > bestW) {
                enQueue(ew, i, e, false);
            }

            e = queue.removeFirst(); // 获取下一个扩展节点

            if (e == null) { // 如果同一层的节点已经处理完了
                if (queue.isEmpty()) { // 队列已经为空
                    break;
                }

                queue.addLast(null); // 加入新的同层尾部标志
                e = queue.removeFirst(); // 取下一层的扩展节点
                i++; // 进入下一层

                if (i > n) {
                    r = 0;
                } else {
                    r -= w[i]; // 剩余集装箱的重量
                }
            }
            ew = e.weight;
        }
        return bestW;
    }
}
