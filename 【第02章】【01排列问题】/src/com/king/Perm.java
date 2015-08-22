package com.king;

/**
 * 排列问题
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-01
 * Time: 19:25
 * Declaration: All Rights Reserved !!!
 */
public class Perm {

    private Perm() {
    }

    /**
     * 排列问题
     *
     * @param array 待排列的数组
     * @param start 排列的起始位置
     * @param end   排列的结束位置
     * @param <T>   参数化类型
     */
    public static <T> void perm(T[] array, int start, int end) {
        if (start == end) { // 如果起始和结束位置相同说明一次排列已经完成
            for (T t : array) { // 输出排列信息
                System.out.print(t);
            }

            System.out.println();
        } else { // 否则排列没有完成
            for (int i = start; i <= end; i++) {
                swap(array, start, i); // 将第一个元素分别与区间后面的元素进行交换
                perm(array, start + 1, end);
                swap(array, start, i); // 现场还原
            }
        }

    }

    /**
     * 交换数组中两个数的位置
     *
     * @param array 数组
     * @param x     位置x
     * @param y     位置y
     * @param <T>   参数化类型
     */
    private static <T> void swap(T[] array, int x, int y) {
        T temp = array[x];
        array[x] = array[y];
        array[y] = temp;
    }
}
