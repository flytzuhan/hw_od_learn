package com.huawei.od.excise.year25.c.n035;

import java.util.Scanner;

/**
 * 购买数量最多的宝石 -- 滑动窗口
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        int[] nums = new int[count];
        for (int i = 0; i < count; ++i) {
            nums[i] = in.nextInt();
        }
        int money = in.nextInt();

        int left = 0, total = 0, sum = 0;
        for (int right = 0; right < count; right++) {
            sum += nums[right];
            // 买了当前这个位置的宝石，就超过了最大的钱数，因此要缩小窗口
            while (money < sum) {
                sum -= nums[left++];
            }
            total = Math.max(total, right - left + 1);
        }
        System.out.println(total);
    }
}
