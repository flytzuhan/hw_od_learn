package com.huawei.od.excise.year25.c.n109;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 翻牌求最大分  -- 动态规划
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(getMaxScore(nums));
    }

    public static long getMaxScore(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        long[] dp = new long[n];
        dp[0] = Math.max(nums[0], 0);
        if (n == 1) {
            return dp[0];
        }
        dp[1] = Math.max(nums[1] + dp[0], 0);
        if(n == 2) {
            return dp[1];
        }
        dp[2] = Math.max(nums[2] + dp[1], 0);
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(nums[i] + dp[i-1], dp[i-3]);
        }

        return dp[n-1];
    }
}
