package com.huawei.od.excise.year25.b.n074;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 最小宽度的子矩阵 -- 滑动窗口
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        // 矩阵数据
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            matrix[i] = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        int k = Integer.parseInt(in.nextLine());
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 统计目标数字出现的次数
        Map<Integer, Integer> targetMap = new HashMap<>();
        for (int num : nums) {
            targetMap.put(num, targetMap.getOrDefault(num, 0) + 1);
        }
        int size = targetMap.size();
        // 现在开始使用滑动窗口来寻找最小的宽度
        int left = 0, result = Integer.MAX_VALUE;
        int count = 0;
        Map<Integer, Integer> curMap = new HashMap<>();
        for (int right = 0; right < m; right++) {
            for (int i = 0; i < n; i++) {
                // 获取每一行的数据
                int num = matrix[i][right];
                curMap.put(num, curMap.getOrDefault(num, 0) + 1);
                if (targetMap.containsKey(num) && curMap.get(num).equals(targetMap.get(num))) {
                    count++;
                }
            }
            // 现在判断是否要缩小窗口
            while (left <= right && count == size) {
                // 更新最小值
                result = Math.min(result, right - left + 1);
                for (int i = 0; i < n; i++) {
                    int curNum = matrix[i][left];
                    curMap.put(curNum, curMap.get(curNum) - 1);
                    if (targetMap.containsKey(curNum) && curMap.get(curNum) < targetMap.get(curNum)) {
                        count--;
                    }
                }
                left++;
            }
        }

        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }
}
