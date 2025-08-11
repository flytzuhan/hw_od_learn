package com.huawei.od.excise.year25.c.n086;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 爱吃蟠桃的孙悟空 -- 二分法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int target = Integer.parseInt(in.nextLine());
        // 现在要使用二分法来查找数据
        int left = 1;
        int right = Arrays.stream(nums).max().getAsInt();
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 判断这个中间值是否能满足需求
            long hours = getHours(nums, mid);
            if (hours <= target) {
                // 花费的时间太少了，说明吃的太快了
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    public static long getHours(int[] nums, int k) {
        long total = 0;
        for (int num : nums) {
            // 向上取整
            total += (num + k - 1) / k;
        }
        return total;
    }
}
