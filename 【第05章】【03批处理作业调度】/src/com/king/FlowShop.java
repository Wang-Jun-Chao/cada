package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-05
 * Time: 20:34
 * Declaration: All Rights Reserved !!!
 */
public final class FlowShop {
    private static int[][] time;            // 各作业所需的处理时间
    private static int[] curSchedule;       // 当前作业调度
    private static int[] bestSchedule;      // 当前最优作业调度
    private static int[] m2FinishTime;      // 机器2完成处理时间
    private static int m1FinishTime;        // 机器1完成处理时间
    private static int curFinishTime;       // 完成时间和
    private static int bestFinishTime;      // 当前最优值
    private static int jobNum;              // 作业数

    private FlowShop() {
        throw new RuntimeException("The class com.com.king.FlowShop should not be initialized.");
    }

    /**
     * 交换数组中两个数的位置
     *
     * @param a 待交换的数组
     * @param x 坐标
     * @param y 坐标
     */
    private static void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }

    /**
     * 回溯法
     *
     * @param i 回溯的层数
     */
    private static void traceBack(int i) {
        if (i > jobNum) {// 所有的节点已经处理完
            // 记录最优解，curSchedule[i]，第i次调度的是curSchedule[i]作业
            System.arraycopy(curSchedule, 1, bestSchedule, 1, jobNum);
            bestFinishTime = curFinishTime; // 记录最优值
        } else {
            for (int j = i; j <= jobNum; j++) {
                // 第j交调度为curSchedule[j]，在机器1上用时为time[curSchedule[j]][1]
                m1FinishTime += time[curSchedule[j]][1];
                // 机器2执行的是机器1已完成的作业，所以是i-1，
                // m2FinishTime[i - 1]第i-1次调度在机器2上完成的时间是m2FinishTime[i - 1]
                // 保证2号机器的第i-1次任务完成了，才将1号机器完成后的任务放到2号机器上去执行
                m2FinishTime[i] = ((m2FinishTime[i - 1] > m1FinishTime)
                        ? m2FinishTime[i - 1] : m1FinishTime) + time[curSchedule[j]][2];
                curFinishTime += m2FinishTime[i]; // 记录当前的完成时间

                if (curFinishTime < bestFinishTime) { // 如果当前的完成时间小于最优值，就是可进行其它的调度尝试
                    swap(curSchedule, i, j); // 交换调度
                    traceBack(i + 1); // 检查下一个节点
                    swap(curSchedule, i, j); // 还原调度安排
                }

                m1FinishTime -= time[curSchedule[j]][1]; // 还原在机器1上的调度第j号作业的时间
                curFinishTime -= m2FinishTime[i]; // 还原当前的完成时间

            }
        }
    }

    /**
     * 批处理作业调度入口
     *
     * @param m     作业安安排矩阵，下标为0的行和列不使用
     * @param n     作业数目
     * @param bestX 保存最优解
     * @return 最优值
     */
    public static int flow(int[][] m, int n, int[] bestX) {
        curSchedule = new int[n + 1];
        m2FinishTime = new int[n + 1];
        time = m;
        jobNum = n;
        bestSchedule = bestX;
        bestFinishTime = Integer.MAX_VALUE; // 最优值初始化为当前数组的最大值
        m1FinishTime = 0;
        curFinishTime = 0;

        for (int i = 0; i <= n; i++) {
            m2FinishTime[i] = 0;
            curSchedule[i] = i;
        }

        traceBack(1);

        return bestFinishTime;
    }

}
