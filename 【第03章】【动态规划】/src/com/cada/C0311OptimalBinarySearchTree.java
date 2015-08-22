package com.cada;

/**
 * 最优二叉搜索树 动态规划
 * 4、求解过程：
 * 1)没有内部节点时，构造T[1][0],T[2][1],T[3][2]……，T[n+1][n]
 * 2)构造只有1个内部结点的最优二叉搜索树T[1][1],T[2][2]…, T[n][n]，可以求得m[i][i]
 * 同时可以用一个数组存做根结点元素为：s[1][1]=1, s[2][2]=2…s[n][n]=n
 * 3)构造具有2个、3个、……、n个内部结点的最优二叉搜索树。
 * ……
 * r （ 起止下标的差）
 * 0   T[1][1], T[2][2]       , …，     T[n][n]，
 * 1   T[1][2], T[2][3], …，T[n-1][n]，
 * 2   T[1][3], T[2][4], …，T[n-2][n]，
 * ……
 * r   T[1][r+1], T[2][r+2], …，T[i][i+r]，…，T[n-r][n]
 * ……
 * n-1   T[1][n]
 * <p/>
 * Author: 王俊超
 * Date: 2014-12-30
 * Time: 09:04
 * All Rights reserved !!!
 */
public class C0311OptimalBinarySearchTree {

    public static final int N = 3;

    /**
     * 最优二叉搜索树算法
     *
     * @param a 条件概率数组，表示在叶结点的条件概率
     * @param b 条件概率数组，表示在内结点的条件概率
     * @param n 结点点的数目
     * @param m TODO 还不知道其作用
     * @param s 记录根结点，i，j子树的根结点是s[i][j]
     * @param w 在子树的顶点处被搜索到的概率
     */
    public static void optimalBinarySearchTree(double[] a, double[] b, int n, double[][] m, int[][] s, double[][] w) {
        // 初始化构造无内部节点的情况
        for (int i = 0; i <= n; i++) {
            w[i + 1][i] = a[i]; // a[i]代表外部结点的被搜索到的概率
            m[i + 1][i] = 0;
        }

        // r代表起止下标的差
        for (int r = 0; r < n; r++) {
            // i为起始元素下标
            for (int i = 1; i <= n - r; i++) {
                int j = i + r; // j为终止元素下标

                // 构造T[i][j]，填写w[i][j]，m[i][j]，s[i][j]
                // 首选i作为根，其左子树为空，右子树为节点
                w[i][j] = w[i][j - 1] + a[j] + b[j];
                m[i][j] = m[i + 1][j];
                s[i][j] = i;

                // 不选i作为根，设k为其根，则k=i+1，……，j
                // 左子树为节点：i,i+1……k-1,右子树为节点：k+1，k+2，……，j
                for (int k = i + 1; k <= j; k++) {
                    double t = m[i][k - 1] + m[k + 1][j];
                    if (t < m[i][j]) {
                        m[i][j] = t;
                        s[i][j] = k; // 记录根节点元素
                    }
                }

                m[i][j] += w[i][j];
            }
        }
    }

    /**
     * 回溯法
     *
     * @param n  结点点的数目
     * @param i  子树的最左叶结点
     * @param j  子树的最右叶结点
     * @param s  记录根结点，i，j子树的根结点是s[i][j]
     * @param f  根结点标记，如果当前f大于0，说明其为根结点
     * @param ch 子树结点标记，左子树为L，右子树为R
     */
    public static void traceBack(int n, int i, int j, int[][] s, int f, char ch) {
        int k = s[i][j]; // 记录i，j之间的根结点
        if (k > 0) {

            if (f == 0) { // 大于0说明其为根
                System.out.println("Root: " + k + " (i:j):(" + i + "," + j + ")");
            } else { // 说明其为子树
                System.out.println(ch + " of " + k + ":" + k + " (i:j):(" + i + "," + j + ")");
            }

            int t = k - 1;
            if (t >= i && t <= n) { // 回溯左子树
                traceBack(n, i, t, s, k, 'L');
            }

            t = k + 1;
            if (t <= j) { // 回溯右子树
                traceBack(n, t, j, s, k, 'R');
            }
        }
    }

    public static void main(String[] args) {
        double a[] = {0.15, 0.1, 0.05, 0.05};
        double b[] = {0.00, 0.5, 0.1, 0.05};

        System.out.println("有序集的概率分布为: ");
        for (int i = 0; i < N + 1; i++) {
            System.out.println("a[" + i + "]=" + a[i] + ", b[" + i + "]=" + b[i]);
        }

        double[][] m = new double[N + 2][N + 2];
        int[][] s = new int[N + 2][N + 2];
        double[][] w = new double[N + 2][N + 2];

        optimalBinarySearchTree(a, b, N, m, s, w);
        System.out.println("二叉搜索树最小平均路长为: " + m[1][N]);
        System.out.println("构造的最优二叉树为: ");
        traceBack(N, 1, N, s, 0, '0');
    }
}
