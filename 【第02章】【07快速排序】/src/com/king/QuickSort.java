package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-02
 * Time: 13:11
 * Declaration: All Rights Reserved !!!
 */
public class QuickSort {

    private static final int CUTOFF = 3;

    private QuickSort() {
        throw new RuntimeException("The class com.com.king.QuickSort should not be initialized.");
    }

    /**
     * 快速排序方法入口
     *
     * @param a 等待排序的数组
     */
    public static void quickSort(Comparable[] a) {
        quickSort(a, 0, a.length - 1);
    }

    /**
     * 快速排序正真实现
     *
     * @param a     等待排序的数组
     * @param left  数组中要排序的第一个元素的位置
     * @param right 数组中要排序的最后一个元素的位置
     */
    private static void quickSort(Comparable[] a, int left, int right) {
        if (left + CUTOFF <= right) { // 排序的元素大于三个的时候才使用快速排序
            Comparable pivot = median3(a, left, right); // 找出一个中枢值

            // 开始分区
            int i = left;
            int j = right - 1;

            // 循环结束后，i的位置即为中枢的位置
            // 原因如下，a[i]之前的元素都是不大于pivot的，a[j]之后的元素都是不小于pivot的
            // 又i = j + 1，所以a[j=i-1]<=a[i]，a[i]>=pivot，a[j]<=pivot
            // 所以i才是中枢元素最后的位置
            while (true) {
                do {
                    ++i; // 从最左边找到第一个不小于中枢值的元素
                } while (a[i].compareTo(pivot) < 0);

                do {
                    --j; // 从最右边找到第一个不小于中枢值的元素
                } while (a[j].compareTo(pivot) > 0);

                // 上面两个do-while与下面的两while实现的功能一样
//                while (a[++i].compareTo(pivot) < 0) {
//                    // 这里什么都不需要做
//                }
//                while (a[--j].compareTo(pivot) > 0) {
//                    // 这里什么都不需要做
//                }
                if (i < j) { // 如果左右都找到一个元素，并且不是同一个
                    swapReferences(a, i, j); // 交换两个元素的位置
                } else { // 否则跳出循环
                    break;
                }
            }

            swapReferences(a, i, right - 1); // 保存中枢元素
            quickSort(a, left, i - 1); // 排序不比中枢元素大的元素
            quickSort(a, i + 1, right); // 排序不比中枢元素小的元素
        } else {
            insertionSort(a, left, right);
        }
    }

    /**
     * 内部插入排序算法
     *
     * @param a     排序的数组
     * @param left  排序的起点
     * @param right 排序的终点
     */
    private static void insertionSort(Comparable[] a, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            Comparable tmp = a[p];
            int j;

            // p位置之前的元素是排好序的，找a[p]的正确位置，将a[p]从后向前一个一个比较
            // 如果前面的元素比a[p]大，就将他们向后移动一个位置，当循环结束时的j就是a[p]的正确位置
            for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }

            a[j] = tmp; // 将a[p]放入正确的位置
        }
    }

    /**
     * 找出最左，最右，中间位置三个元素中第二小的元素
     *
     * @param a     待排序的数组
     * @param left  最左的序号
     * @param right 最右的序号
     * @return 最左，最右，中间位置三个元素中第二小的元素
     */
    private static Comparable median3(Comparable[] a, int left, int right) {
        int center = (left + right) / 2;

        // 下面三个if完成对最左，最右，中间位置三个元素按从小到大排序
        if (a[center].compareTo(a[left]) < 0) {
            swapReferences(a, left, center);
        }

        if (a[right].compareTo(a[left]) < 0) {
            swapReferences(a, left, right);
        }

        if (a[right].compareTo(a[center]) < 0) {
            swapReferences(a, center, right);
        }

        // 将第二大小的元素存放到右边倒数第二个位置
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    /**
     * 交换数组中x，y位置元素的值
     *
     * @param a 待交换的数组
     * @param x 待交换的位置
     * @param y 待交换的位置
     */
    private static void swapReferences(Object[] a, int x, int y) {
        Object tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;
    }
}
