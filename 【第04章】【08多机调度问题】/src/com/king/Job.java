package com.king;

/**
 * 作业类
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-04
 * Time: 08:56
 * Declaration: All Rights Reserved !!!
 */
public final class Job implements Comparable<Job> {
    private int id; // 作业编号
    private int time; // 作业用时

    /**
     * 构造函数
     *
     * @param id   作业编号
     * @param time 作业用时
     */
    public Job(int id, int time) {
        this.id = id;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public int compareTo(Job o) {
        if (o == null) {
            return 1;
        } else {
            return this.time - o.time;
        }
    }

    @Override
    public String toString() {
        return "(J:" + id + ", T:" + time + ")";
    }
}
