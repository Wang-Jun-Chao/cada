package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-01
 * Time: 10:16
 * Declaration: All Rights Reserved !!!
 */
public class Node<K, T extends Number> implements Comparable<Node<K, T>> {
    private K data;
    private T weight;

    private Node<K, T> leftChild;
    private Node<K, T> rightChild;

    /**
     * 构造函数，那一个空的结点
     */
    public Node() {
        this(null, null);
    }

    /**
     * 构造函数，创建一个有元素有权重的节点
     *
     * @param data   元素对象
     * @param weight 元素权重
     */
    public Node(K data, T weight) {
        this(data, weight, null, null);
    }

    /**
     * 构造函数，创建一个有元素有权重有左右子树的的节点
     *
     * @param data       元素对象
     * @param weight     元素权重
     * @param leftChild  左子树的根结点
     * @param rightChild 右子树的根结点
     */
    public Node(K data, T weight, Node<K, T> leftChild, Node<K, T> rightChild) {
        this.data = data;
        this.weight = weight;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public K getData() {
        return data;
    }

    public void setData(K data) {
        this.data = data;
    }

    public T getWeight() {
        return weight;
    }

    public void setWeight(T weight) {
        this.weight = weight;
    }

    public Node<K, T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node<K, T> leftChild) {
        this.leftChild = leftChild;
    }

    public Node<K, T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node<K, T> rightChild) {
        this.rightChild = rightChild;
    }

    /**
     * 比较方法
     *
     * @param other 待比较的对象
     * @return -1：当前对象比other小，0：当前对象与other一样大，1：当前对象比other大
     */
    @Override
    public int compareTo(Node<K, T> other) {
        // 待比较对对象空，则待比较对象大
        if (other == null) {
            return 1;
        }

        // 当前对象的权重为null
        if (weight == null) {
            if (other.weight == null) { // 待比较对象的权重为null，则两者相等
                return 0;
            } else { // 否则当前对象小
                return -1;
            }
        }
        // 权重对象不为空的情况下，对权重进行比较


        double result = weight.doubleValue() - other.weight.doubleValue();

        if (result > 0.00) {
            return 1;
        } else if (result < 0.00) {
            return -1;
        } else {
            return 0;
        }
    }

    /**
     * 前序遍历
     *
     * @param handler 遍历时使用的处理器对象
     * @param <M>     参数化类型，表示处理后返回的对象
     * @param <N>     参数化类型，表示处理时的参数对象
     */
    public <M, N> void preOrder(Handler<M, N> handler) {

        handler.hand((N) this);

        if (leftChild != null) {
            leftChild.preOrder(handler);
        }

        if (rightChild != null) {
            rightChild.preOrder(handler);
        }
    }

    /**
     * 中序遍历
     *
     * @param handler 遍历时使用的处理器对象
     * @param <M>     参数化类型，表示处理后返回的对象
     * @param <N>     参数化类型，表示处理时的参数对象
     */
    public <M, N> void inOrder(Handler<M, N> handler) {


        if (leftChild != null) {
            leftChild.inOrder(handler);
        }
        handler.hand((N) this);

        if (rightChild != null) {
            rightChild.inOrder(handler);
        }
    }

    /**
     * 后序遍历
     *
     * @param handler 遍历时使用的处理器对象
     * @param <M>     参数化类型，表示处理后返回的对象
     * @param <N>     参数化类型，表示处理时的参数对象
     */
    public <M, N> void postOrder(Handler<M, N> handler) {

        if (leftChild != null) {
            leftChild.postOrder(handler);
        }

        if (rightChild != null) {
            rightChild.postOrder(handler);
        }

        handler.hand((N) this);
    }

    @Override
    public String toString() {
        return "(" + data + ", " + weight + ")";
    }
}
