package com.huawei.od.excise.year25.a.n052;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 补种未成活胡杨 -- 滑动窗口
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int m = Integer.parseInt(in.nextLine());
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(in.nextLine());
        // 先构造一个胡杨的数组
        int[] trees = new int[n + 1];
        Arrays.fill(trees, 1);
        // 先把未成活的胡杨标记出来
        for (int i = 0; i < m; i++) {
            trees[nums[i]] = 0;
        }

        int left = 1;
        int total = 0;
        for (int right = 1; right <= n; right++) {
            if (trees[right] == 0) {
                k--;
            }
            while (k  < 0) {
                // 要开始缩小窗口了
                if (trees[left] == 0) {
                    k++;
                }
                left++;
            }
            total = Math.max(total, right - left + 1);
        }

        System.out.println(total);
    }
}
