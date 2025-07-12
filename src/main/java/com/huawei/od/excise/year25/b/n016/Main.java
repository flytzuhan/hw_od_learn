package com.huawei.od.excise.year25.b.n016;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 士兵过河 -- 动态规划
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int t = in.nextInt();
        in.nextLine();

        Integer[] arr = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        Arrays.sort(arr);
        // 声明一个dp数组，用来表示前面i+1个士兵过河所需要的最短时间
        int[] dp = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i] = arr[0];
                if (dp[i] > t) {
                    System.out.println("0 0");
                    return;
                }
            } else if (i == 1) {
                // 前面两个士兵过河
                dp[i] = arr[1];
            } else {
                // 前面i+1个士兵过河
                dp[i] = Math.min(dp[i - 1] + arr[0] + arr[i], dp[i - 2] + arr[0] + arr[i] + arr[1] + arr[1]);
            }
            if (dp[i] > t) {
                System.out.println(i + " " + dp[i - 1]);
                return;
            }
        }

        System.out.println(n + " " + dp[n - 1]);
    }
}
