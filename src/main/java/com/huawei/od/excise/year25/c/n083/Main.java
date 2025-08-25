package com.huawei.od.excise.year25.c.n083;

import sun.nio.cs.ext.MacHebrew;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最大矩阵和 -- 动态规划
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        // 创建二维数组，记录以当前元素为右下角的最大矩阵和
        int maxSum = Integer.MIN_VALUE;
        for (int top = 0; top < n; top++) {
            int[] tmp = new int[m];
            for (int bottom = top; bottom < n; bottom++) {
                // 计算每一列的和
                for (int j = 0; j < m; j++) {
                    tmp[j] += matrix[bottom][j];
                }
                // 对这个tmp数组求最大值
                int currentMax = maxSum(tmp);
                maxSum = Math.max(maxSum, currentMax);
            }
        }

        System.out.println(maxSum);
    }

    public static int maxSum(int[] nums) {
        int dp = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp = Math.max(nums[i], dp + nums[i]);
            sum = Math.max(sum, dp);
        }
        return sum;
    }
}
