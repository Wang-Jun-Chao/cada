package com.king;

/**
 * 边
 * Author: 王俊超
 * Date: 2015-01-03
 * Time: 18:45
 * Declaration: All Rights Reserved !!!
 */
public class EdgeNode implements Comparable<EdgeNode> {
    private int weight; // 边权重
    private int u; // 边的起点
    private int v; // 边的终点

    public EdgeNode(int u, int v, int weight) {
        this.weight = weight;
        this.u = u;
        this.v = v;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    @Override
    public int compareTo(EdgeNode o) {
        if (o == null) {
            return 1;
        } else {



            return (this.weight - o.weight);
        }
    }
}
