package com.king;

/**
 * 将正整数n表示成一系列正整数之和，n=n1+n2+n3+......nk(其中，n1>=n2>=......nk>=1,k>=1),
 * 正整数n的这种表示称为正整数n的划分。正整数n的不同划分个数称为正整数n的划分数,记作p(n)。
 * <p/>
 * Author: 王俊超
 * Date: 2015-01-01
 * Time: 19:48
 * Declaration: All Rights Reserved !!!
 */
public class Division {
    private Division() {
    }

    /**
     * 将整数n划分成不大于m的正整数
     *
     * @param n 待划分的数
     * @param m 每个划分出来的数大小不大于m
     * @return 总计有多少种划分方法
     */
    public static int division(int n, int m) {
        if (n < 1 || m < 1) { // 不能再划分了
            return 0;
        }

        if (n == 1 || m == 1) { // 只有一种划分，就是划分成1
            return 1;
        }

        if (n < m) { // 等价转换，划分出来的数不可能比n大
            return division(n, n);
        }

        if (n == m) {
        // 有两种情况，
        // 1、从n中划分出m，则只有一种方法，
        // 2、从n不划分出小于m的数，其有division(n, m - 1)种
            return division(n, m - 1) + 1;
        }

        // 有两种情况，
        // 1、从n中划分出m，则只有division(n - m, m)种，
        // 2、从n不划分出小于m的数，其有division(n, m - 1)种
        return division(n - m, m) + division(n, m - 1);
    }
}
