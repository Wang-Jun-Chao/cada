package com.king;

/**
 * Author: 王俊超
 * Date: 2015-01-05
 * Time: 09:12
 * Declaration: All Rights Reserved !!!
 */
public final class MaxLoading {
    private MaxLoading() {
        throw new RuntimeException("The class com.com.king.MaxLoading should not be initialized.");
    }


    /**
     * 使用迭代回溯法求解装载问题，调用递归方法backtrack(0)实现回溯搜索
     *
     * @param weight     重量数组
     * @param capacity   轮船的载重量
     * @param bestResult 最优解
     * @return 不超过c1Weight的最大子集和
     */
    public static int maxLoading(int[] weight, int capacity, int[] bestResult) {

        int layout = 0;                             // 当前层，从第0层开始
        int[] curResult = new int[weight.length];   // 最优解
        int bestWeight = 0;                         // 当前最优载重量
        int curWeight = 0;                          // 当前载重量
        int remainder = 0;                          // 剩余可用的集装箱重量

        for (int e : weight) { // 计算集装箱总的重量
            remainder += e;
        }

        while (true) { // 搜索子树
            // 进入左子树，一直对左子树进行操作，即添加集装箱到船上
            while (layout < weight.length && curWeight + weight[layout] <= capacity) {
                remainder -= weight[layout];
                curWeight += weight[layout];
                curResult[layout] = 1;
                layout++;
            }


            if (layout >= weight.length) { // 到达叶结点
                System.arraycopy(curResult, 0, bestResult, 0, curResult.length); // 保存最优结果
                bestWeight = curWeight; // 记录最大载重量
            } else { // 进入右子树，说明第layout层（也就是第layout个集装箱）不能添加到船上
                // 不论第layout个集装箱是否安装到船上，在以后的过程中是不能使用第layout个集装箱的重量
                remainder -= weight[layout];
                curResult[layout] = 0; // 第layout个集装箱不能装上船
                layout++; // 标记处理下一层
            }

            while (curWeight + remainder <= bestWeight) { // 剪枝回溯

                layout--; // 退回到最后一个已经处理的层（节点）

                while (layout >= 0 && curResult[layout] == 0) {
                    // 从右子树返回，天下一直到最后一个装上船的箱子为止，需要将可用的剩余装载量还原
                    remainder += weight[layout];
                    layout--; // 再退回到上一个层（节点）
                }

                if (layout == -1) { // 如果从右树已经回溯完了，就完成了所有的操作，返回结果
                    return bestWeight;
                }

                // 再次进入右子树
                curResult[layout] = 0;
                curWeight -= weight[layout];
                layout++;
            }
        }
    }
}
