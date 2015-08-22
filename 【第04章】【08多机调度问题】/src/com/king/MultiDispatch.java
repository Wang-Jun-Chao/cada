package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-04
 * Time: 09:03
 * Declaration: All Rights Reserved !!!
 */
public final class MultiDispatch {

    private MultiDispatch() {
        throw new RuntimeException("The class com.com.king.MultiDispatch should not be initialized.");
    }

    public static void multiDispatch(Job[] jobs, final int num) {
        if (jobs.length <= num) {
            System.out.println("Dispatch each job a machine.");
        }

        MergeSort.mergeSort(jobs); // 对作业按从小到大排序

        MinHeap<Machine> minHeap = new MinHeap<>(num);
        for (int i = 1; i <= num; i++) { // 向小顶堆中添加机器
            minHeap.add(new Machine(i, 0));
        }

        for (int i = jobs.length - 1; i >= 0; i--) {
            Machine machine = minHeap.deleteTop();

            System.out.println("Dispatch M:" + machine.getId() + " T(" + machine.getAvail() + ":"
                    + (machine.getAvail() + jobs[i].getTime()) + ") to J:" + jobs[i].getId());

            machine.setAvail(machine.getAvail() + jobs[i].getTime()); // 更新机器下次可使用的最早时间
            minHeap.add(machine);
        }

    }
}
