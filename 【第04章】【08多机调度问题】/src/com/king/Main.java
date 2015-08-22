package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-04
 * Time: 09:41
 * Declaration: All Rights Reserved !!!
 */
public final class Main  {
    public static void main(String[] args) {

        final int[] time = {2, 14, 4, 16, 6, 5, 3}; // 作业用时
        final int num = 3; // 机器数目
        Job[] jobs = new Job[time.length];
        for (int i = 0; i < time.length; i++) {
            jobs[i] = new Job(i + 1, time[i]);
        }

        for (Job job : jobs) {
            System.out.println(job);
        }

        MultiDispatch.multiDispatch(jobs, num);

    }
}
