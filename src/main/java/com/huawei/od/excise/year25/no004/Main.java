package com.huawei.od.excise.year25.no004;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 天然蓄水库 - 双指针，参考这篇博文
 * https://blog.csdn.net/qq2279523723/article/details/129027270?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7ECtr-3-129027270-blog-128441437.235%5Ev43%5Epc_blog_bottom_relevance_base5&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EYuanLiJiHua%7ECtr-3-129027270-blog-128441437.235%5Ev43%5Epc_blog_bottom_relevance_base5&utm_relevant_index=5
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 转成数组
        Integer[] height = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        int n = height.length;

        int[] left = new int[n];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], height[i - 1]);
        }

        int[] right = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i + 1]);
        }

        // 第i位置能蓄水的高度
        int[] waterHeight = new int[n];
        Set<Integer> waterHeights = new HashSet<>();
        for (int i = 1; i < n - 1; i++) {
            int water = Math.max(0, Math.min(left[i], right[i]) - height[i]);
            if (water != 0) {
                waterHeight[i] = water + height[i];
                waterHeights.add(waterHeight[i]);
            }
        }

        int[] result = new int[3];
        int capacity = 0;
        for (Integer singleHeight : waterHeights) {
            int l = 0;
            while (waterHeight[l] < singleHeight || height[l] >= singleHeight) {
                l++;
            }
            int r = n - 1;
            while (waterHeight[r] < singleHeight || height[r] >= singleHeight) {
                r--;
            }

            int totalWater = 0;
            for (int i = l; i <= r; i++) {
                totalWater += Math.max(0, singleHeight - height[i]);
            }

            if (totalWater > result[2]) {
                result[0] = l - 1;
                result[1] = r + 1;
                result[2] = totalWater;
            } else if (totalWater == result[2]) {
                int dis1 = r - l + 1;
                int dis2 = result[1] - result[0] - 1;
                if (dis1 < dis2) {
                    result[0] = l - 1;
                    result[1] = r + 1;
                }
            }
        }

        if (result[2] == 0) {
            System.out.println(0);
        } else {
            System.out.print(result[0]);
            System.out.print(" ");
            System.out.print(result[1]);
            System.out.print(":");
            System.out.print(result[2]);
        }
    }
}
