package com.huawei.od.excise.year25.c.n122;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 查找充电设备组合 -- 动态规划
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int pMax = Integer.parseInt(in.nextLine());
        // 定义动态规划数组,dp[j]表示前面i个设备的在最大输出功率为pMax下的最大输出功率
        int[] dp = new int[pMax+1];
        for (int i = 0; i < n; i++) {
            for (int j = pMax; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j-nums[i]] + nums[i]);
            }
        }

        System.out.println(dp[pMax]);
    }
}
