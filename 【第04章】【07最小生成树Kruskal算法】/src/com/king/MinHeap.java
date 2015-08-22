package com.king;

import java.util.ArrayList;
import java.util.List;

/**
 * 小顶堆的实现
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-01
 * Time: 10:07
 * Declaration: All Rights Reserved !!!
 */
public final class MinHeap<T extends Comparable<T>> {
    // 堆中元素存放的集合
    private List<T> items;
    // 用于计数
    private int cursor;

    /**
     * 构造一个椎，始大小是0
     */
    public MinHeap() {
        this(0);
    }

    /**
     * 造诣一个指定初始大小的堆
     *
     * @param size 初始大小
     */
    public MinHeap(int size) {
        items = new ArrayList<>(size);
        cursor = -1;
    }

    /**
     * 向上调整堆
     *
     * @param index 被上移元素的起始位置
     */
    public void siftUp(int index) {
        T intent = items.get(index); // 获取开始调整的元素对象

        while (index > 0) { // 如果不是根元素
            int parentIndex = (index - 1) / 2; // 找父元素对象的位置
            T parent = items.get(parentIndex);  // 获取父元素对象
            if (intent.compareTo(parent) < 0) { //上移的条件，子节点比父节点小
                items.set(index, parent); // 将父节点向下放
                index = parentIndex; // 记录父节点下放的位置
            } else { // 子节点不比父节点小，说明父子路径已经按从小到大排好顺序了，不需要调整了
                break;
            }
        }

        // index此时记录是的最后一个被下放的父节点的位置（也可能是自身），所以将最开始的调整的元素值放入index位置即可
        items.set(index, intent);
    }

    /**
     * 向下调整堆
     *
     * @param index 被下移的元素的起始位置
     */
    public void siftDown(int index) {
        T intent = items.get(index);  // 获取开始调整的元素对象
        int leftIndex = 2 * index + 1; // // 获取开始调整的元素对象的左子结点的元素位置

        while (leftIndex < items.size()) { // 如果有左子结点
            T minChild = items.get(leftIndex); // 取左子结点的元素对象，并且假定其为两个子结点中最小的
            int minIndex = leftIndex; // 两个子节点中最小节点元素的位置，假定开始时为左子结点的位置

            int rightIndex = leftIndex + 1;  // 获取右子结点的位置
            if (rightIndex < items.size()) {  // 如果有右子结点
                T rightChild = items.get(rightIndex);  // 获取右子结点的元素对象
                if (rightChild.compareTo(minChild) < 0) {  // 找出两个子节点中的最小子结点
                    minChild = rightChild;
                    minIndex = rightIndex;
                }
            }

            // 如果最小子节点比父节点小，则需要向下调整
            if (minChild.compareTo(intent) < 0) {
                items.set(index, minChild); // 将子节点向上移
                index = minIndex; // 记录上移节点的位置
                leftIndex = index * 2 + 1; // 找到上移节点的左子节点的位置
            } else { // 最小子节点不比父节点小，说明父子路径已经按从小到大排好顺序了，不需要调整了
                break;
            }
        }

        // index此时记录是的最后一个被上移的子节点的位置（也可能是自身），所以将最开始的调整的元素值放入index位置即可
        items.set(index, intent);
    }

    /**
     * 向堆中添加一个元素
     *
     * @param item 等待添加的元素
     */
    public void add(T item) {
        items.add(item); // 将元素添加到最后
        siftUp(items.size() - 1); // 循环上移，以完成重构
    }

    /**
     * 删除堆顶元素
     *
     * @return 堆顶部的元素
     */
    public T deleteTop() {
        if (items.isEmpty()) { // 如果堆已经为空，就报出异常
            throw new RuntimeException("The heap is empty.");
        }

        T maxItem = items.get(0); // 获取堆顶元素
        T lastItem = items.remove(items.size() - 1); // 删除最后一个元素
        if (items.isEmpty()) { // 删除元素后，如果堆为空的情况，说明删除的元素也是堆顶元素
            return lastItem;
        }

        items.set(0, lastItem); // 将删除的元素放入堆顶
        siftDown(0); // 自上向下调整堆
        return maxItem; // 返回堆顶元素
    }

    /**
     * 获取下一个元素
     *
     * @return 下一个元素对象
     */
    public T next() {
        if (cursor >= (items.size() - 1)) {
            throw new RuntimeException("No more element");
        }

        cursor++;
        return items.get(cursor);

    }

    /**
     * 判断堆中是否还有下一个元素
     *
     * @return true堆中还有下一个元素，false堆中无下五元素
     */
    public boolean hasNext() {
        return cursor < items.size() - 1;
    }

    /**
     * 获取堆中的第一个元素
     *
     * @return 堆中的第一个元素
     */
    public T first() {
        if (items.size() == 0) {
            throw new RuntimeException("The heap is empty.");
        }
        cursor = 0;
        return items.get(0);
    }

    /**
     * 判断堆是否为空
     *
     * @return true是，false否
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * 获取堆的大小
     *
     * @return 堆的大小
     */
    public int size() {
        return items.size();
    }

    /**
     * 清空堆
     */
    public void clear() {
        items.clear();
    }
}
