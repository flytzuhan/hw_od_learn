package com.huawei.od.excise.year25.c.n098;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最大花费金额 -- 滑动窗口
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int money = Integer.parseInt(in.nextLine());

        // 先排序
        Arrays.sort(nums);
        int left = 0;
        int sum = 0;
        int result = 0;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum > money) {
                sum -= nums[left++];
            }
            result = Math.max(result, sum);
        }

        System.out.println(result);
    }
}
