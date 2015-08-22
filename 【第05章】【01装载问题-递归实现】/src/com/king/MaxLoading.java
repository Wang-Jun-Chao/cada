package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-04
 * Time: 16:22
 * Declaration: All Rights Reserved !!!
 */
public final class MaxLoading {

    private static int num;             // 集装箱数
    private static int[] weight;        // 集装箱重量数组
    private static int limitWeight;     // 第一艘轮船的载重量
    private static int currentWeight;   // 当前载重量
    private static int bestWeight;      // 当前最优载重量
    private static int remainWeight;    // 剩余集装箱重量
    private static int[] curResult;     // 当前解
    private static int[] bestResult;    // 当前最优解


    private MaxLoading() {
        throw new RuntimeException("The class com.com.king.MaxLoading should not be initialized.");
    }


    /**
     * 使用递归回溯法求解装载问题，调用递归方法backtrack(0)实现回溯搜索
     *
     * @param weightArray 重量数组
     * @param capacity    轮船的载重量
     * @param result      最优解
     * @return 不超过c1Weight的最大子集和
     */
    public static int maxLoading(int[] weightArray, int capacity, int[] result) {
        // 初始化数据成员
        num = weightArray.length - 1;
        weight = weightArray;
        limitWeight = capacity;
        currentWeight = 0;
        bestWeight = 0;
        curResult = new int[result.length];
        bestResult = result;
        remainWeight = 0;

        // 初始化剩余集装箱的重量
        for (int i : weight) {
            remainWeight += i;
        }

        // 计算最优载重量
        backtrack(0);

        return bestWeight;
    }

    /**
     * 搜索子集树的第i层子树
     *
     * @param i 第i层子树
     */
    private static void backtrack(int i) {
        if (i > num) {
            if (currentWeight > bestWeight) {
                System.arraycopy(curResult, 0, bestResult, 0, curResult.length); // 记录最优结果
                bestWeight = currentWeight; // 记录当前的载重量
            }

            return;
        }

        remainWeight -= weight[i];
        if (currentWeight + weight[i] <= limitWeight) {
            curResult[i] = 1;
            currentWeight += weight[i];
            backtrack(i + 1);
            currentWeight -= weight[i];
        }

        // 【注意】为什么要在【1】处还原，而不是在【2】，因为下面的if表示不装载x[i]，remainWeight表示的是
        // 可用的剩下的装载量，即使用不装载x[i]，在接下来的装载中，x[i]所对应的集装箱重量也是不能使用的，
        // 所以才在【1】处进行还原
        // remainWeight += weight[i]; // 【2】

        if (currentWeight + remainWeight > bestWeight) {
            curResult[i] = 0;
            backtrack(i + 1);
        }

        remainWeight += weight[i]; // 【1】
    }
}
