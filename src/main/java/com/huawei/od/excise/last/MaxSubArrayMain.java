package com.huawei.od.excise.last;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 最大子数组之和 -- 子数组之和
 */
public class MaxSubArrayMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 获取输入的蛋糕层数
        int cakeNum = Integer.parseInt(in.nextLine());
        // 获取每层蛋糕的甜度数据
        int[] cakeLevels = new int[cakeNum];
        for (int i = 0; i < cakeNum; i++) {
            cakeLevels[i] = in.nextInt();
        }
        // 现在要找这样一个连续的区间，使得这个区间的和最大，因此要使用连续和的算法
        // 如何获取这个区间呢，遍历数组，维护一个当前子数组最大和的值，如果一个新的元素进来之后，这个子数组的最大和就变成了这样的一个情况
        // 要么是presum+curNum,要么是直接以curNum单独作为一个子数组的起点来处理
        int preSum = cakeLevels[0];
        int maxSum = cakeLevels[0];
        // 现在从索引为1的下标开始计算
        for (int i = 1; i < cakeNum; i++) {
            preSum = Math.max(preSum + cakeLevels[i], cakeLevels[i]);
            // 然后还要跟最大的这个值进行比较
            maxSum = Math.max(maxSum, preSum);
        }

        System.out.println(maxSum);
    }
}
