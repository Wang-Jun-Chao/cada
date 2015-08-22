package com.king;

/**
 * 归并排序
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-02
 * Time: 09:44
 * Declaration: All Rights Reserved !!!
 */
public class MergeSort {

    private MergeSort() {
        throw new RuntimeException("The class com.com.king.MergeSort should not be initialized.");
    }

    /**
     * 归并排序
     *
     * @param array 待排序的数组
     */
    public static void mergeSort(Comparable[] array) {
        Comparable[] tmpArray = new Comparable[array.length]; // 创建一个放置排序中间结果数组
        mergeSort(array, tmpArray, 0, array.length - 1);
    }

    /**
     * 归并排序，内部方法，进行递归调用
     *
     * @param array    待排序的数组
     * @param tmpArray 排序中间结果数组
     * @param left     排序的最开始位置
     * @param right    排序结束位置
     */
    private static void mergeSort(Comparable[] array, Comparable[] tmpArray,
                                  int left, int right) {
        if (left < right) { // 如果待排序的数目大于2才执行
            int center = (left + right) / 2;
            mergeSort(array, tmpArray, left, center);
            mergeSort(array, tmpArray, center + 1, right);
            merge(array, tmpArray, left, center + 1, right);
        }
    }

    /**
     * 归并操作，内部方法，归并两个排好序的了数组
     *
     * @param array    待排序的数组
     * @param tmpArray 排序中间结果数组
     * @param leftPos  第一个子数组的起始位置
     * @param rightPos 第二个子数组的起始位置
     * @param rightEnd 第二个子数组的结束位置
     */
    private static void merge(Comparable[] array, Comparable[] tmpArray,
                              int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        // 对两个子数组进行归并
        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (array[leftPos].compareTo(array[rightPos]) <= 0) {
                tmpArray[tmpPos++] = array[leftPos++];
            } else {
                tmpArray[tmpPos++] = array[rightPos++];
            }
        }

        // 复制第一个了数组中剩余的元素
        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = array[leftPos++];
        }
        // 复制第二个了数组中剩余的元素
        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = array[rightPos++];
        }

        // 将元素复制回原来的数组
        for (int i = 0; i < numElements; i++, rightEnd--) {
            array[rightEnd] = tmpArray[rightEnd];
        }
    }

}
