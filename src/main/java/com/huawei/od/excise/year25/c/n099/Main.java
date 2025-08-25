package com.huawei.od.excise.year25.c.n099;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 数组连续和 -- 双指针、滑动窗口
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int target = in.nextInt();
        in.nextLine();
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 现在要使用滑动窗口来计算
        int left = 0, sum = 0, count = 0;
        for (int right = 0; right < n; right++) {
            sum += nums[right];
            while (sum >= target && left <= right) {
                count += n - right;
                sum -= nums[left++];
            }
        }

        System.out.println(count);
    }
}
