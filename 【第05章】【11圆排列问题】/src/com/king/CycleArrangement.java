package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-11
 * Time: 09:01
 * Declaration: All Rights Reserved !!!
 */
public final class CycleArrangement {
    private static double min;      // 当前最优值
    private static double[] x;      // 当前圆排列圆心横坐标
    private static double[] bestX;  // 最优解，即圆心的横坐标
    private static double[] bestR;  // 最优解对应圆的半径
    private static double[] r;      // 当前圆排列
    private static int n;           // 圆排列中圆的个数


    private CycleArrangement() {
        throw new RuntimeException("The class com.com.king.CycleArrangement should not be initialized.");
    }
    /**
     * 交换数组中两个元素的位置
     *
     * @param a 数组
     * @param x 位置
     * @param y 位置
     */
    private static void swap(double[] a, int x, int y) {
        double tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }

    /**
     * 计算当前所选择的圆的横坐标
     *
     * @param t 第t个圆
     * @return 第t个圆的横坐标
     */
    private static double center(int t) {
        double tmp = 0;
        // 第[1:t-1]个圆已经处理过了，计算第t个圆与第j个圆相切时的横坐标，
        // 最大值的就是要放置第t个圆的位置
        for (int j = 1; j < t; j++) {
            double valueX = x[j] + 2.0 * Math.sqrt(r[t] * r[j]);
            if (valueX > tmp) {
                tmp = valueX;
            }
        }

        return tmp;
    }

    /**
     * 计算当前圆排列的长度
     */
    private static void compute() {
        double low = 0;
        double high = 0;
        for (int i = 1; i <= n; i++) {
            // 找到圆的横坐标减半径的值
            // 考虑第一个圆的半径是1，第二个是1000的极端情况
            if (x[i] - r[i] < low) {
                low = x[i] - r[i];
            }

            // 找到圆的横坐标加半径的值
            // 考虑倒数第一个圆的半径是1，倒数第二个是1000的极端情况
            if (x[i] + r[i] > high) {
                high = x[i] + r[i];
            }
        }

        // 求得最小值
        if (high - low < min) {
            min = high - low; // 保存最优值
            System.arraycopy(x, 1, bestX, 1, n); // 保存最优解
            System.arraycopy(r, 1, bestR, 1, n); // 保存圆的坐标

        }
    }

    /**
     * 解决圆排列的递归算法
     *
     * @param t 第t个圆
     */
    private static void traceBack(int t) {
        if (t > n) { // 大于圆的个数说明已经处理完了
            compute();
        } else { // 否则没有处理完
            // 对没有处理完的圆依次进行处理
            for (int j = t; j <= n; j++) {
                swap(r, t, j); // 交换两个未处理的圆的位置

                double centerX = center(t); // 计算第t个圆的横坐标
                // centerX + r[t] + r[1] 当前已经处理的部分圆排列的一个估计值长度
                if (centerX + r[t] + r[1] < min) {
                    x[t] = centerX; // 标记圆的横坐标
                    traceBack(t + 1); // 处理下一个圆
                }

                swap(r, t, j); // 还原圆的位置
            }
        }
    }

    /**
     * 解决圆排列问题的入口方法
     *
     * @param num    圆的数目
     * @param arr    圆的半径数组，第一个元素不使用
     * @param result 保存最优解圆横坐标
     * @param radius 保存最优解对应的圆的半径
     * @return 当前问题的最优值
     */
    public static double solve(int num, double[] arr, double[] result, double[] radius) {
        // 初始化操作
        n = num;
        r = arr;
        min = Double.MAX_VALUE;
        x = new double[num + 1];
        bestX = result;
        bestR = radius;
        traceBack(1);
        x = null;

        return min;
    }
}
