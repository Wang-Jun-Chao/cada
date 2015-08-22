package com.king;

/**
 * 最大团问题
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-26
 * Time: 15:06
 * Declaration: All Rights Reserved !!!
 */
public final class MaxClique {

    /**
     * 相当于一个链表，用于记录当前结点是否被选中
     */
    private static class BBNode {
        private BBNode parent;  // 指向父节点的指针
        boolean lChild;         // 左儿子节点标识，即当前结点是否被选中

        public BBNode() {
        }

        public BBNode(BBNode parent, boolean lChild) {
            this.parent = parent;
            this.lChild = lChild;
        }
    }

    /**
     * 最大团结点，用于记录当前处理的中间结果
     */
    private static class CliqueNode implements Comparable<CliqueNode> {
        private int cn;     // 当前团的顶点数
        private int un;     // 当前团最大顶点数的上界
        private int level;  // 节点在子集空间树中所处的层次
        private BBNode ptr; // 指向活节点在子集树中相应节点的指针

        public CliqueNode() {
        }

        public CliqueNode(int cn, int un, int level, BBNode ptr) {
            this.cn = cn;
            this.un = un;
            this.level = level;
            this.ptr = ptr;
        }

        /**
         * 比较函数
         *
         * @param o 另一个比较对象
         * @return 比较结果，如果o为null返回1，否则返回this.un-o.un的结果
         */
        @Override
        public int compareTo(CliqueNode o) {
            if (o == null) {
                return 1;
            } else {
                return this.un - o.un;
            }
        }
    }

    private MaxClique() {
        throw new RuntimeException("The class com.com.king.MaxClique should not be initialized.");
    }

    /**
     * 向堆中添加一个节点
     *
     * @param heap  堆对象
     * @param cn    当前团的顶点数
     * @param un    当前团最大顶点数的上界
     * @param level 节点在子集空间树中所处的层次
     * @param e     上一个元素的标记
     * @param ch    当前节点是否被选中
     */
    private static void addLiveNode(MaxHeap<CliqueNode> heap, int cn, int un, int level, BBNode e, boolean ch) {
        BBNode b = new BBNode(e, ch); // 创建当前节点是否被选中的标记对象
        CliqueNode n = new CliqueNode(cn, un, level, b); // 创建当前处理对象是中间结果
        heap.add(n); // 添加到大顶堆中
    }

    /**
     * 最大团问题的入口
     *
     * @param a     图的邻接矩阵，注意：其中下标为0的行和列不使用，使用0作为无边标记
     * @param n     图的顶点数
     * @param bestX 用于保存最优值的数组
     * @return 最优解
     */
    public static int solve(int[][] a, int n, int bestX[]) {
        // 初始化操作
        MaxHeap<CliqueNode> H = new MaxHeap<>(64); // 初始化大顶堆
        BBNode E = new BBNode(); // 一个节点标记
        int i = 1; // 当前处理节点的编号
        int cn = 0; // 当前团中的顶点数
        int bestN = 0; // 最优解

        // 搜集子集空间树
        while (i != n + 1) { // 非叶节点
            // 检查顶点i与当前团中其他顶点之间是否有边相连
            boolean OK = true; // 默认情况认为相连
            BBNode B = E;
            for (int j = i - 1; j > 0; B = B.parent, j--) {
                // B.lChild：为true表当前节点在团中
                // a[i][j] == 0：顶点i和顶点j之间没有边
                // 当两件都满足时说明加入顶点j使顶点i和顶点j之间没有边，不能成团
                // 标记OK为false，退出循环
                if (B.lChild && a[i][j] == 0) {
                    OK = false;
                    break;
                }
            }

            if (OK) { // 如果加入j顶点依然可以成团
                if (cn + 1 > bestN) {// 如果新的团比记录的最大团的顶点数多，就重新记录最大团的数目
                    bestN = cn + 1;
                }

                addLiveNode(H, cn + 1, cn + n - i + 1, i + 1, E, true); // 向大顶堆中添加节点
            }

            // cn：当前团中的顶点数
            // n-1：未处理的顶点数
            // 如果，当前团中的顶点数+未处理的顶点数>=当前记录的团中最多顶点数，说明有可能有最优解
            if (cn + n - i >= bestN) {
                addLiveNode(H, cn, cn + n - i + i, i + 1, E, false);
            }

            CliqueNode N = H.deleteTop(); // 删除堆顶元素
            E = N.ptr; // 记录堆顶元素的处理状态，选中还是不选中
            cn = N.cn; // 记录当前团中的顶点数
            i = N.level; // 当前处理的层数
        }

        // E最后记录的是最后一个顶点的处理结果，通过E.ptr可以找到他的上一个顶点的处理结果，
        // 节点是否选中可能通过E.lChild来判断，因为处理是按顺序进行的，最后的E代表n顶点，E.ptr代表n-1顶点
        // 依此类推
        for (int j = n; j > 0; j--) {
            bestX[j] = E.lChild ? 1 : 0;
            E = E.parent;
        }
        return bestN;
    }
}
