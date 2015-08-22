package com.king;

/**
 * 机器类
 * Author: 王俊超
 * Date: 2015-01-04
 * Time: 08:58
 * Declaration: All Rights Reserved !!!
 */
public final class Machine implements Comparable<Machine> {
    private int id; // 机器编号
    private int avail; // 机器从开始时间算起什么时候可用

    /**
     * 构造函数
     *
     * @param id    机器编号
     * @param avail 机器从开始时间算起什么时候可用
     */
    public Machine(int id, int avail) {
        this.id = id;
        this.avail = avail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvail() {
        return avail;
    }

    public void setAvail(int avail) {
        this.avail = avail;
    }

    @Override
    public int compareTo(Machine o) {
        if (o == null) {
            return 1;
        } else {
            return this.avail - o.avail;
        }
    }

    @Override
    public String toString() {
        return "(M:" + id + " ,A:" + avail + ")";
    }
}
