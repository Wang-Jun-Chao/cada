package com.cada;

/**
 * 活动安排问题 贪心算法
 * <p/>
 * Author: 王俊超
 * Date: 2014-12-30
 * Time: 15:20
 * Declaration: All Rights Reserved !!!
 */
public class C0401GreedySelector {

    public static final int N = 11; // 活动数目

    /**
     * 活动的贪心选择算法
     *
     * @param number     活动的数目
     * @param start      活动的开始时间集合（非递减顺序）
     * @param finish     活动的结束时间集合（非递减顺序）
     * @param activities 沃动是否选中的集合，如是活动i被选中，则a[i]为true
     */
    public static void greedySelector(int number, int[] start, int[] finish, boolean[] activities) {
        activities[1] = true; // 第一个活动默认被选中
        int lastSelect = 1; // 记录最后次被选中的活动序号

        // 依次检查活动i是否与当前已选择的活动相容
        for (int i = 2; i <= N; i++) {
            if (start[i] > finish[lastSelect]) { // 如果活动相容
                activities[i] = true; // 标记活动被选中
                lastSelect = i; // 记录最后次被选中的活动序号
            } else {
                activities[i] = false; // 标记活动未被选中
            }
        }
    }


    public static void main(String[] args) {
        // 下标从1开始,存储活动开始时间
        int[] start = {0, 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        //下标从1开始,存储活动结束时间
        int[] finish = {0, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        boolean[] activities = new boolean[N + 1];

        System.out.println("各活动的开始时间,结束时间分别为: ");
        for (int i = 1; i <= N; i++) {
            System.out.println("[" + i + "]:(" + start[i] + "," + finish[i] + ")");
        }

        greedySelector(N, start, finish, activities);
        System.out.println("最大相容活动子集为: ");
        for (int i = 1; i < N; i++) {
            if (activities[i]) {
                System.out.println("[" + i + "]:(" + start[i] + "," + finish[i] + ")");
            }
        }
    }


}
