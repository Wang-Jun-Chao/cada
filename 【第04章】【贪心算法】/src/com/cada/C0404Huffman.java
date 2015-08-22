package com.cada;

import java.util.ArrayList;

/**
 * Author: 王俊超
 * Date: 2014-12-31
 * Time: 09:55
 * Declaration: All Rights Reserved !!!
 */
public class C0404Huffman {

    private final static int N = 6;

    private static class BTNode<T extends Comparable<T>> {
        private T data;
        private BTNode<T> leftChild;
        private BTNode<T> rightChild;

        public BTNode(T data, BTNode<T> leftChild, BTNode<T> rightChild) {
            this.data = data;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public BTNode<T> copyTree() {
            BTNode<T> root;
            BTNode<T> left;
            BTNode<T> right;

            if (data == null) {
                return null;
            }

            left = leftChild.copyTree();
            right = rightChild.copyTree();

            root = new BTNode<T>(data, left, right);
            return root;
        }
    }

    private static class BinaryTree<T extends Comparable<T>> {
        private BTNode<T> root;

        public BinaryTree() {
            root = null;
        }

        public void preOrder(BTNode<T> root) {
            if (root != null) {
                System.out.print(root.data + " ");
                preOrder(root.leftChild);
                preOrder(root.rightChild);
            }
        }

        public void inOrder() {
            if (root != null) {
                preOrder(root.leftChild);
                System.out.print(root.data + " ");
                preOrder(root.rightChild);
            }

        }

        public void postOrder() {
            if (root != null) {
                preOrder(root.leftChild);
                preOrder(root.rightChild);
                System.out.print(root.data + " ");
            }
        }

        public int treeHeight(BTNode<T> root) {
            if (root == null) {
                return 0;
            } else {
                int leftHeight;
                int rightHeight;
                leftHeight = treeHeight(root.leftChild);
                rightHeight = treeHeight(root.rightChild);

                return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
            }
        }

        public int treeNodeCount(BTNode<T> root) {
            if (root == null) {
                return 0;
            } else {
                return 1 + treeNodeCount(root.leftChild) + treeNodeCount(root.rightChild);
            }

        }

        public void destroyTree(BTNode<T> root) {
            if (root != null) {
                destroyTree(root.leftChild);
                destroyTree(root.rightChild);
                root.data = null;
                root = null;
            }
        }

        public void change(BTNode<T> root) {
            BTNode<T> temp;
            if (root != null) {
                temp = root.leftChild;
                root.leftChild = root.rightChild;
                root.rightChild = temp;
                change(root.leftChild);
                change(root.rightChild);
            }
        }

        public void makeTree(T data, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
            root = new BTNode<T>(data, leftTree.root, rightTree.root);
        }

    }

    private static class MinHeap<T extends Comparable<T>> {
        private ArrayList<T> heap;
        private int currentSize;
        private int maxSize;

        public MinHeap(int maxSize) {
            this.maxSize = maxSize;
            this.heap = new ArrayList<T>(maxSize);
            currentSize = 0;
        }

        /**
         * 自上往下调整堆，使关键字小的节点在上
         *
         * @param start 调整的起始位置
         * @param end   调整的结束位置
         */
        public void filterDown(final int start, final int end) {
            int i = start;
            int j = 2 * i + 1;
            T temp = heap.get(i);
            while (j <= end) {

                if (j < end && heap.get(j).compareTo(heap.get(j + 1)) > 0) { // 找两个节点中较小的那个
                    j++;
                }

                // 如果父结点不大于子结点中较小的那个，说明已经按从小到大调整好了
                if (temp.compareTo(heap.get(j)) <= 0) {
                    break;
                } else { // 否则
                    heap.set(i, heap.get(j)); // 将较小的子结点当作父结点
                    i = j; // 记录被交换的结点下标位置，i最后的值就是最后交换的元素的下标位置
                    j = 2 * j + 1; // 被交换的结点的左子结点
                }
            }

            heap.set(i, temp); // 将最开始的节点放入到最后被调整的位置
        }

        /**
         * 自下往上调整椎
         *
         * @param start 调整的起始位置
         */
        public void filterUp(int start) {
            int j = start;
            int i = (j - 1) / 2; // i指向j的双亲节点
            T temp = heap.get(j); // 记录起始调整位置的值
            while (j > 0) {
                // 如果父结点不大于子结点，说明已经按从小到大调整好了
                if (heap.get(i).compareTo(temp) <= 0) {
                    break;
                } else {
                    heap.set(j, heap.get(i)); // 将父结点调整到子结点
                    j = i; // 记录被交换的结点下标位置，j最后的值就是最后交换的元素的下标位置
                    i = (i - 1) / 2; // 找到被交换（下放）结点的父结点
                }
            }

            heap.set(j, temp); // 将最开始的节点放入到最后被调整的位置
        }

        public boolean insert(final T node) {
            if (currentSize == maxSize) {
                return false;
            }
            heap.set(currentSize, node);
            filterUp(currentSize);

            currentSize++;
            return true;
        }

        public T removeMin() {
            T node = heap.get(0);
            heap.set(0, heap.get(currentSize - 1));
            currentSize--;
            filterDown(0, currentSize - 1); // 调整新的根节点
            return node;
        }

        public T GetMin() {
            return heap.get(0);
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public boolean isFull() {
            return currentSize == maxSize;
        }

        public void clear() {
            heap.clear();
            currentSize = 0;
        }
    }


    private static class Huffman<T extends Comparable<T>> implements Comparable<Huffman<T>> {
        private BinaryTree<Integer> tree;
        private T weight;

        @Override
        public int compareTo(Huffman<T> o) {
            return 0;
        }
    }


    public static <T extends Comparable<T>> BinaryTree<Integer> huffmanTree(T f[], int n) {
        Huffman<T>[] w = new Huffman[N + 1];
        BinaryTree<Integer> z = new BinaryTree<Integer>();
        BinaryTree<Integer> zero = new BinaryTree<Integer>();

        for (int i = 1; i <= N; i++) {
            z.makeTree(i, zero, zero);
            w[i].weight = f[i];
            w[i].tree = z;
        }

        return null;
    }

    public static void main(String[] args) {

    }
}
