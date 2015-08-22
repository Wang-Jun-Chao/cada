package com.king;

import java.util.Map;

/**
 * 哈夫曼树编码
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-01
 * Time: 10:33
 * Declaration: All Rights Reserved !!!
 */
public class HuffmanTree<K, V extends Number> {

    private Node<K, V> root; // 树的根结点
    private MinHeap<Node<K, V>> minHeap; // 最小堆

    /**
     * 构造函数，根据Map中的对象来创建一个哈夫曼树
     *
     * @param data map对象
     */
    public HuffmanTree(Map<K, V> data) {

        // 构建最小堆
        minHeap = new MinHeap<>(data.size());
        for (Map.Entry<K, V> entry : data.entrySet()) {
            V v = data.remove(entry.getKey());
            minHeap.add(new Node<>(entry.getKey(), v));
        }

        createHuffmanTree();

    }

    /**
     * 构造函数，根据键值对来创建一个哈夫曼树
     *
     * @param keys   键的集合
     * @param values 值的集合
     */
    public HuffmanTree(K[] keys, V[] values) {
        // 构建最小堆
        minHeap = new MinHeap<>(keys.length);
        for (int i = 0; i < keys.length; i++) {
            minHeap.add(new Node<>(keys[i], values[i]));
        }

        createHuffmanTree();
    }

    /**
     * 创建一个哈夫曼树
     */
    private void createHuffmanTree() {

        while (minHeap.size() > 1) { // 堆中元素不大于1时才进行
            Node left = minHeap.deleteTop();
            Node right = minHeap.deleteTop();

            double result = left.getWeight().doubleValue() + right.getWeight().doubleValue();

            root = new Node(null, result, left, right);

            root.setLeftChild(left);
            root.setRightChild(right);
            minHeap.add(root);
        }

        root = minHeap.deleteTop();
        minHeap = null;
    }

    public Node<K, V> getRoot() {
        return root;
    }
}
