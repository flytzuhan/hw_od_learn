package com.huawei.od.excise.year25.b.n054;

import javafx.scene.transform.Scale;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * claude的api key
 * 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
 */

/**
 * 组装最大可靠性设备 -- 动态规划
 */
public class Main {

    public static long result = -1;

    public static int s = -1;

    public static int n = -1;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        s = in.nextInt();
        n = in.nextInt();
        int total = in.nextInt();
        in.nextLine();
        List<long[]> items = new ArrayList<>();
        for (int i = 0; i < total; i++) {
            String input = in.nextLine();
            String[] split = input.split(" ");
            int[] nums = new int[split.length];
            for (int j = 0; j < nums.length; j++) {
                nums[j] = Integer.parseInt(split[j]);
            }
            items.add(new long[]{nums[0] + 1, nums[1], nums[2]});
        }

        // 使用二分查找来处理
        int left = 0;
        int right = total * 10000;
        while (true) {
            if (left > right) {
                System.out.println(result);
                break;
            } else {
                if (cal(left + (right - left) / 2, items)) {
                    // 移动左边
                    result = left + (right - left) / 2;
                    left += (right - left) / 2 + 1;
                } else {
                    // 移动右边指针
                    right = left + (right - left) / 2 - 1;
                }
            }
        }
    }

    public static boolean cal(long x, List<long[]> items) {
        // 二分查找
        long[] temp = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            temp[i] = Integer.MAX_VALUE;
        }

        int j = 0;
        while (true) {
            if (j > items.size()) {
                long all = 0;
                for (int i = 1; i <= n; i++) {
                    all += temp[i];
                }
                return all <= s;
            } else {
                if (items.get(j)[1] >= x) {
                    temp[(int) items.get(j)[0]] = Math.min(temp[(int) items.get(j)[0]], items.get(j)[2]);
                }
            }
            j++;
        }
    }
}
