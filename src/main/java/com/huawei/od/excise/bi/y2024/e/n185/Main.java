package com.huawei.od.excise.bi.y2024.e.n185;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 部门人力分配 -- 二分法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = Integer.parseInt(in.nextLine());
        int[] tasks = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 先对数组进行排序
        Arrays.sort(tasks);
        // 找到临界值
        int left = tasks[tasks.length - 1];
        int right = tasks[tasks.length - 1] + tasks[tasks.length - 2];
        // 二分查找
        int result = -1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (check(mid, tasks) > m) {
                // 说明了花费的时间比较多，因此这个要left要增加
                left = mid + 1;
                result = mid + 1;
            } else {
                right = mid;
            }
        }

        System.out.println(result);
    }

    public static int check(int mid, int[] tasks) {
        int total = 0;
        int low = 0;
        int high = tasks.length - 1;
        while (low <= high) {
            if (tasks[low] + tasks[high] > mid) {
                high--;
            } else {
                low++;
                high--;
            }
            total++;
        }
        return total;
    }
}
