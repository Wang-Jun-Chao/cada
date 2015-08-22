package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-27
 * Time: 11:01
 * Declaration: All Rights Reserved !!!
 */
public final class BatchJob {

    private static int n;
    private static int[][] M;
    private static int[][] b;
    private static int[][] a;
//    private static int[] bestX;
//    private static int bestC;
    private static boolean[][] y;


    private BatchJob() {
        throw new RuntimeException("The class com.com.king.BatchJob should not be initialized.");
    }

    private static class MinHeapNode implements Comparable<MinHeapNode> {
        private int s;
        private int f1;
        private int f2;
        private int sf2;
        private int bb;
        private int[] x;

        public MinHeapNode(int n) {
            x = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = i;
            }
            s = 0;
            f1 = 0;
            f2 = 0;
            sf2 = 0;
            bb = 0;
        }

        public MinHeapNode(MinHeapNode E, int ef1, int ef2, int ebb, int n) {
            x = new int[n];
            System.arraycopy(E.x, 0, x, 0, n);

            f1 = ef1;
            f2 = ef2;
            sf2 = E.sf2 + f2;
            bb = ebb;
            s = E.s + 1;

        }

        @Override
        public int compareTo(MinHeapNode o) {
            if (o == null) {
                return 1;
            } else {
                return this.bb - o.bb;
            }
        }
    }

    private static void swap(int[][] a, int x1, int y1, int x2, int y2) {
        int tmp = a[x1][y1];
        a[x1][y1] = a[x2][y2];
        a[x2][y2] = tmp;
    }

    private static void swap(int[] a, int x, int y) {
        int tmp = a[x];
        a[x] = a[y];
        a[y] = tmp;

    }

    private static void sort() {
        int[] c = new int[n];
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                b[i][j] = M[i][j];
                c[i] = i;
            }

            for (int i = 0; i < n - 1; i++) {
                for (int k = n - 1; k > i; k--) {
                    if (b[k][j] < b[k - 1][j]) {
                        swap(b, k, j, k - 1, j);
                        swap(c, k, k - 1);
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                a[c[i]][j] = i;
            }
        }
    }

    private static int bound(MinHeapNode E, int[] f, boolean[][] y) {
        for (int k = 0; k < n; k++) {
            for (int j = 0; j < 2; j++) {
                y[k][j] = false;
            }
        }

        for (int k = 0; k <= E.s; k++) {
            for (int j = 0; j < 2; j++) {
                y[a[E.x[k]][j]][j] = true;
            }
        }

        f[0] = E.f1 + M[E.x[E.s]][0];
        f[1] = ((f[0] > E.f2) ? f[0] : E.f2) + M[E.x[E.s]][1];


        int sf2 = E.sf2 + f[1];
        int s1 = 0;
        int s2 = 0;
        int k1 = n - E.s;
        int k2 = n - E.s;
        int f3 = f[1];


        for (int j = 0; j < n; j++) {
            if (!y[j][0]) {
                k1--;
                if (k1 == n - E.s - 1) {
                    f3 = (f[1] > f[0] + b[j][0]) ? f[1] : f[0] + b[j][0];
                }

                s1 += f[0] + k1 * b[j][0];
            }
        }

        for (int j = 0; j < n; j++) {
            if (!y[j][1]) {
                k2--;
                s1 += b[j][1];
                s2 += f3 + k2 * b[j][1];
            }
        }

        return sf2 + ((s1 > s2) ? s1 : s2);
    }

    public static int solve(int[][] M, int n, int[] bestX) {

        BatchJob.M = M;
        BatchJob.n = n;

//        BatchJob.bestX = bestX;
//        BatchJob.bestC = Integer.MAX_VALUE;

        BatchJob.a = new int[M.length][M[0].length];
        BatchJob.b = new int[M.length][M[0].length];
        BatchJob.y = new boolean[M.length][2];

        for (int i = 0; i < bestX.length; i++) {
            bestX[i] = i;
        }

        int bestC = Integer.MAX_VALUE;
        sort();
        MinHeap<MinHeapNode> H = new MinHeap<>(64);
        MinHeapNode E = new MinHeapNode(n);

        while (E.s < n) {
            if (E.s == n) {

                if (E.sf2 < bestC) {
//                    System.out.println("======" + bestC);
                    bestC = E.f2;
                    System.arraycopy(E.x, 0, bestX, 0, n);
                }
            } else {
                for (int i = E.s; i < n; i++) {
                    swap(E.x, E.s, i);
                    int[] f = new int[2];
                    int bb = bound(E, f, y);
                    if (bb < bestC) {
                        MinHeapNode N = new MinHeapNode(E, f[0], f[1], bb, n);
                        H.add(N);
                    }
                    swap(E.x, E.s, i);
                }
            }

            if (H.isEmpty()) {
                break;
            } else {
                E = H.deleteTop();
            }
        }

        return bestC;
    }
}
