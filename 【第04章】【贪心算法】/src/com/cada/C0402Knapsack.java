package com.cada;

/**
 * Author: 王俊超
 * Date: 2014-12-30
 * Time: 17:13
 * Declaration: All Rights Reserved !!!
 */
public class C0402Knapsack {
    public static final int NUMBER = 3;

    /**
     * 活动安排问题，贪心算法
     *
     * @param number     物品种类
     * @param capacity   背包总的容量
     * @param values     物品价值数组
     * @param weights    物品重量数组
     * @param percentage 物品选中百分比，全部选中就是1，否则就是包最后剩余容量除以最后选中的物品的总的质量
     */
    public static void knapsack(int number, float capacity, float[] values, float[] weights, float[] percentage) {

        // 排序的规则就是p[i] = v[i]/w[i]，按p[i]的从大到小排序，再将对应的v[i]，w[i]排序
        // sort(n,v,w);//这里假定w[],v[]已按要求排好序

        int i;
        for (i = 1; i < number; i++) {
            percentage[i] = 0; // 初始化数组x[]
        }

        // TODO 可以改进将外面的if放入到for循环里面
        float remainder = capacity; // 记录包剩余的容量
        for (i = 1; i < NUMBER; i++) { // 物品整件被装下x，[i]=1
            if (weights[i] > remainder) {
                break;
            }

            percentage[i] = 1;
            remainder -= weights[i];
        }

        if (i <= number) { // 物品i只有部分被装下
            percentage[i] = remainder / weights[i];
        }
    }

    public static void main(String[] args) {
        float capacity = 50;
        // 这里给定的物品按单位价值减序排序
        float[] weights = {0, 10, 20, 30}; // 下标从1开始
        float[] values = {0, 60, 100, 120};

        float[] percentage = new float[NUMBER + 1];

        System.out.println("背包所能容纳的重量为: " + capacity);
        System.out.println("待装物品的重量和价值分别为: ");
        for (int i = 1; i <= NUMBER; i++) {
            System.out.println("[" + i + "]:(" + weights[i] + "," + values[i] + ")");
        }

        knapsack(NUMBER, capacity, values, weights, percentage);
        System.out.println("选择装下的物品比例如下: ");
        for (int i = 1; i <= NUMBER; i++) {
            System.out.println("[" + i + "]:" + percentage[i]);
        }
    }

}
