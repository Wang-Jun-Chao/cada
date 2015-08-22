package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-13
 * Time: 16:26
 * Declaration: All Rights Reserved !!!
 */
public final class Sort {
    private Sort() {
        throw new RuntimeException("The class com.king.Sort should not be initialized.");
    }

    /**
     * 排序数组归并方法
     *
     * @param a         待排序的数组
     * @param tmpArray  排序所需要的临时数组
     * @param leftPos   第一个子数组的第一个位置
     * @param rightPos  第二个子数组的第一个位置
     * @param rightEnd  第二个子数组的最后一个位置
     * @param <AnyType> 参数化类型
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void merge(AnyType[] a, AnyType[] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1; // 第一个子数组的最后一个位置
        int tmpPos = leftPos; // 向tmp数组中拷贝数据的起始位置

        int numElements = rightEnd - leftPos + 1; // 两个子数组中总的元素数

        while (leftPos <= leftEnd && rightPos <= rightEnd) { // 按从小到大的顺序向tmpArray中拷贝数据
            if (a[leftPos].compareTo(a[rightPos]) <= 0) {
                tmpArray[tmpPos++] = a[leftPos++];
            } else {
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }

        while (leftPos <= leftEnd) { // 将第一个子数组中的剩余数据拷贝到tmpArray数组中
            tmpArray[tmpPos++] = a[leftPos++];
        }

        while (rightPos <= rightEnd) { // 将第二个子数组中的剩余数据拷贝到tmpArray数组中
            tmpArray[tmpPos++] = a[rightPos++];
        }

        for (int i = 0; i < numElements; i++, rightEnd--) { // 将排好序的数据重新拷贝回a数组中
            a[rightEnd] = tmpArray[rightEnd];
        }
    }

    /**
     * 归并排序
     *
     * @param a         待排序的数组
     * @param tmpArray  排序所需要的临时数组
     * @param left      排序的起始位置
     * @param right     排序的结束位置
     * @param <AnyType> 参数化类型
     */
    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a, AnyType[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(a, tmpArray, left, center);
            mergeSort(a, tmpArray, center + 1, right);
            merge(a, tmpArray, left, center + 1, right);
        }
    }

    /**
     * 归并排序方法入口
     *
     * @param a         待排序的数组
     * @param <AnyType> 参数化类型
     */
    public static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] a) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
        mergeSort(a, tmpArray, 0, a.length - 1);
    }


}
