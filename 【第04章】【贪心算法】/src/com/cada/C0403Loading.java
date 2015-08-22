package com.cada;

import java.util.Arrays;

/**
 * 问题描述：有一批集装箱要装上一艘载重量为c的轮船。其中集装箱i的重量为Wi。最优装载问题要求确定
 * 在装载体积不受限制的情况下，将尽可能多的集装箱装上轮船。
 * 求解过程：最优装载问题可用贪心算法求解。采用重量最轻者先装的贪心选择策略，可产生最优装载问题
 * 的最优解。具体代码如下
 * <p/>
 * Author: 王俊超
 * Date: 2014-12-30
 * Time: 21:07
 * Declaration: All Rights Reserved !!!
 */
public class C0403Loading {

    public static final int NUMBER = 4; // 集装箱的数目


    /**
     * 交换数组中x，y两个下标元素的值
     *
     * @param array 元素集合
     * @param x     下标
     * @param y     下标
     */
    public static void swap(int[] array, int x, int y) {
        int temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }

    /**
     * 选择排序，将array中的元素按从小到大的序列启示录到indexes数组中，
     * indexes[i]表示在array数组中，第i大的数，在array中的位置是indexes[i]
     *
     * @param array   待排序数组
     * @param indexes 位置索引数组
     * @param number  数组中元素的个数
     */
    public static void selectSort(int[] array, int[] indexes, int number) {
        int[] tempArray;
        tempArray = Arrays.copyOf(array, NUMBER + 1); // 拷贝数组

        int min; // 用于记录较小的数
        for (int i = 1; i <= NUMBER; i++) { // 默认设置array数组中第i大的数在i位置
            indexes[i] = i;
        }

        // 选择排序
        for (int i = 1; i < NUMBER; i++) {
            min = i; // 暂时记录最小的数
            for (int j = i + 1; j <= NUMBER; j++) {
                if (tempArray[min] > tempArray[j]) {
                    min = j; // 打到小的就记录
                }
            }

            swap(tempArray, i, min); // 交换每次遍历找到的最小的数
            swap(indexes, i, min); // 交换每次遍历找到的最小的数的下标
        }

    }

    /**
     * 贪心算法: 最优装载问题 算法loading的主要计算量在于将集装箱依其重量从小到大排序，
     * 故算法所需的计算时间为 O(nlogn)
     *
     * @param selections 集装街选中数组，选中的值为1，否则为0
     * @param weigths    集装箱重量数组
     * @param capacity   船的最大载重量
     * @param number     集装箱总的数目
     */
    public static void loading(int[] selections, int[] weigths, int capacity, int number) {
        // 存储排完序后weigths[]的原始索引
        // indexes[i]表示在weigths数组中，第i大的数，在weigths中的位置是indexes[i]
        int[] indexes = new int[NUMBER + 1];

        selectSort(weigths, indexes, number);

        for (int i = 1; i <= number; i++) { // 默认情况下一个集装箱都不选中
            selections[i] = 0;
        }

        // 如是还有集装箱，并且还可以装下此伪装箱，进行如下操作
        for (int i = 0; i <= number && weigths[indexes[i]] <= capacity; i++) {
            selections[indexes[i]] = 1;  // 标记选中此集装箱
            // 更新剩余容量，capacity最初表示的是船的最大载重量，之后表示的是剩余载重量
            capacity -= weigths[indexes[i]];
        }
    }

    public static void main(String[] args) {
        int capacity = 70;
        int weigths[] = {0, 20, 10, 26, 15};// 下标从1开始
        int[] indexes = new int[NUMBER + 1];

        System.out.println("轮船载重为: " + capacity);
        System.out.println("待装物品的重量分别为: ");
        for (int i = 1; i <= NUMBER; i++) {
            System.out.print(weigths[i] + " ");
        }
        System.out.println();

        loading(indexes, weigths, capacity, NUMBER);

        System.out.println("贪心选择结果为: ");
        for (int i = 1; i <= NUMBER; i++) {
            System.out.print(indexes[i] + " ");
        }
        System.out.println();
    }
}
