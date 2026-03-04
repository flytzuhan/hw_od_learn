package com.huawei.od.excise.last;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 游戏分组 -- 先暴力破解，再来一个递归的方式
 */
public class GameGroupMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 获取每个人的分数
        int[] personScores = Arrays.stream(in.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        // 定义差值变量
        int diffVal = Integer.MAX_VALUE;
        // 方法一使用暴力破解
        // diffVal = getDiffValFromMultiForeach(personScores, diffVal);
        // 使用递归的方式来计算，所有回溯算法都包含递归，但并非所有递归都是回溯算法
        diffVal = recursive(personScores, 0, 0, 0);

        System.out.println(diffVal);
    }

    // 使用暴力循环获取
    private static int getDiffValFromMultiForeach(int[] personScores, int diffVal) {
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < 10; j++) {
                for (int k = j + 1; k < 10; k++) {
                    for (int m = k + 1; m < 10; m++) {
                        for (int n = m + 1; n < 10; n++) {
                            // 先把这5个人搞成一个组
                            int firstSum = personScores[i] + personScores[j] + personScores[k] + personScores[m] + personScores[n];
                            // 计算全部的总和
                            int totalSum = Arrays.stream(personScores).sum();
                            // 计算差值
                            diffVal = Math.min(diffVal, Math.abs(2 * firstSum - totalSum));
                        }
                    }
                }
            }
        }
        return diffVal;
    }

    // 使用递归的方式获取两个分组之间的差值的最小值
    private static int recursive(int[] scores, int curIndex, int curSum, int nextSum) {
        // 判断终止条件
        if (curIndex == scores.length) {
            return Math.abs(nextSum - curSum);
        }

        // 获取当前值
        int curVal = scores[curIndex];
        // 第一组选择这个值
        int diffCur = recursive(scores, curIndex+1, curSum+curVal, nextSum);
        // 第二组选择这个值
        int diffNext = recursive(scores, curIndex+1, curSum, nextSum+curVal);
        return Math.min(diffCur, diffNext);
    }
}
