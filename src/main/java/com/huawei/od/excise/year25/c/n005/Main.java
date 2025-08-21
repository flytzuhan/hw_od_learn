package com.huawei.od.excise.year25.c.n005;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 宽度最小的子矩阵 -- 滑动窗口
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int k = Integer.parseInt(in.nextLine());
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 先统计目标数字出现的次数
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int num : nums) {
            targetMap.put(num, targetMap.getOrDefault(num, 0) + 1);
        }
        int size = targetMap.size();
        // 现在开始使用滑动窗口来寻找最短的宽度
        Map<Integer, Integer> curMap = new HashMap<>();
        int left = 0, result = Integer.MAX_VALUE;
        int count = 0;
        // 开始使用滑动窗口来处理数据
        for (int right = 0; right < m; right++) {
            for (int i = 0; i < n; i++) {
                int num = matrix[i][right];
                curMap.put(num, curMap.getOrDefault(num, 0) + 1);
                if (targetMap.containsKey(num) && curMap.get(num).equals(targetMap.get(num))) {
                    count++;
                }
            }
            // 现在判断是否要缩小窗口
            while (left <= right && count == size) {
                // 先更新最小值
                result = Math.min(result, right - left + 1);
                // 缩小窗口
                for (int i = 0; i < n; i++) {
                    int num = matrix[i][left];
                    curMap.put(num, curMap.get(num) - 1);
                    if (targetMap.containsKey(num) && curMap.get(num) < targetMap.get(num)) {
                        count--;
                    }
                }
                left++;
            }
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}
