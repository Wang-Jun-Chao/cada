package com.king;

import com.sun.istack.internal.NotNull;

/**
 * 二分搜索
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-02
 * Time: 08:25
 * Declaration: All Rights Reserved !!!
 */
public class BinarySearch {
    private BinarySearch() {
        throw new RuntimeException("The class com.com.king.BinarySearch should not be initialized.");
    }

    /**
     * 二分搜索，在数组中搜索值value的元素，返回第一个满足的下标
     *
     * @param value 待搜索的值
     * @param array 搜索的数组
     * @param <T>   参数化类型
     * @return 搜索到的元素下标，没有就返回-1
     */
    public static <T extends Comparable<T>> int binarySearch(T value, T[] array) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (value == null && array[mid] == null || value != null && value.compareTo(array[mid]) == 0) {
                return mid;
            }

            if (value == null || value.compareTo(array[mid]) < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -1;
    }
}
