package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-19
 * Time: 10:28
 * Declaration: All Rights Reserved !!!
 */
public final class MaxLoading {
    private static class BBNode {
        private BBNode parent;
        private boolean lChild;

        public BBNode() {

        }

        public BBNode(BBNode parent, boolean lChild) {
            this.parent = parent;
            this.lChild = lChild;
        }
    }

    private static class HeapNode implements Comparable<HeapNode> {
        private BBNode ptr;
        private int uWeight;
        private int level;

        public HeapNode() {

        }

        public HeapNode(BBNode ptr, int uWeight, int level) {
            this.ptr = ptr;
            this.uWeight = uWeight;
            this.level = level;
        }

        @Override
        public int compareTo(HeapNode o) {
            if (o == null) {
                return 1;
            } else {
                return this.uWeight - o.uWeight;
            }
        }
    }


    private MaxLoading() {
        throw new RuntimeException("The class com.com.king.MaxLoading should not be initialized.");
    }

    private static void addLiveNode(MaxHeap<HeapNode> heap, BBNode node, int wt, boolean ch, int lev) {
        BBNode b = new BBNode(node, ch);
        HeapNode N = new HeapNode(b, wt, lev);
        heap.add(N);
    }

    public static int solve(int[] w, int c, int n, int bestX[]) {
        MaxHeap<HeapNode> H = new MaxHeap<>();

        int[] r = new int[n + 1]; // 剩余可用的重量数组
        r[0] = 0;
        for (int j = n - 1; j > 0; j--) {
            r[j] = r[j + 1] + w[j];
        }

        int i = 1;
        BBNode E = null;
        int Ew = 0;

        while (i != n+1) {
            if (Ew + w[i] <= c) {
                addLiveNode(H, E, Ew + r[i], true, i+1);
            }

            addLiveNode(H, E, Ew + r[i], false, i + 1);

            HeapNode N = H.deleteTop();
            i = N.level;
            E = N.ptr;
            Ew = N.uWeight - r[i - 1];
        }

        for (int j = n; j > 0; j--) {
            bestX[j] = E.lChild ? 1: 0;
            E = E.parent;
        }



        return Ew;
    }

}
