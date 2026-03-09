package com.huawei.od.excise.last;

import java.util.Scanner;

/**
 * 构造数列 -- 动态规划
 * 动态规划适合将大问题拆解成小问题，重点是要能找出递推公式
 */
public class GeneratorNumberSequenceMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 先获取输入的数字是多少
        int n = scanner.nextInt();
        // 现在要根据这个n来构造一个数列，这个数列的第一个是这个n，第二个可以是小于等于n的一半，然后继续除以2，直到1，这样就可以使用递推公式了
        int[] dp = new int[n + 1];
        // 表示数列n的值是i的时候，可以构造的数列长度是dp[i]
        // 那么dp[7] = 6,是怎么得来的呢，7和7后面是1，7后面是2，以及7后面是3，所构造的数列之和，7后面不能是4，因为7/4超过了一半，所以7后面只能是1，2，3
        // 那如果使用前缀和的方式来加速计算的话，要将前面的和给缓存起来
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = 1 + sum[i / 2];
            sum[i] = dp[i] + sum[i - 1];
        }

        System.out.println(dp[n]);
    }
}
