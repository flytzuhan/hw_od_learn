package com.huawei.od.excise.bi.y2025.b.n033;

import java.util.Scanner;
import java.util.*;

/**
 * 机器人搬砖 -- 二分查找法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 分为三种情况，
        // 1. 大于8的，肯定干不完活
        // 2. 等于8的情况下，那就要找出每个仓库里面的最大值，然后取最大值
        // 3. 小于8的情况下，一个仓库干多个小时，所以要找出一个中间值，然后能正好干完8个小时，而且全部干完
        if (nums.length > 8) {
            System.out.println(-1);
        } else if (nums.length == 8) {
            System.out.println(Arrays.stream(nums).max().getAsInt());
        } else {
            System.out.println(binarySearch(nums));
        }
    }

    public static int binarySearch(int[] nums) {
        // 先对数组进行排序
        Arrays.sort(nums);
        int left = 0;
        int right = nums[nums.length - 1];
        while (left <= right) {
            int mid = left + (right - left) / 2;
            // 计算以当前速度完成所有的仓库所需要的时间
            int total = 0;
            for (int num : nums) {
                // 向上取整
                total += (num + mid - 1) / mid;
            }
            if (total <= 8) {
                // 说明速度太快了，应该慢一些
                right = mid - 1;
            } else {
                // 速度太慢了，应该快一些
                left = mid + 1;
            }
        }

        return left;
    }
}
