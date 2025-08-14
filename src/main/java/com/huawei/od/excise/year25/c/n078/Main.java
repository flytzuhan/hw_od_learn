package com.huawei.od.excise.year25.c.n078;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 排队游戏 -- 二分法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = nums[0];
        int n = nums[1];
        int k = nums[2];
        boolean[] status = new boolean[m];
        int[] indexes = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int index : indexes) {
            status[index] = true;
        }
        int[] data = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int result = 0;
        // 现在开始处理数据，使用暴力破解法处理
        for (int i = 0; i < m; i++) {
            if (!status[i]) {
                // 这里表示当前位置的学生不是刺头
                for (int j = 0; j < i; j++) {
                    if (data[j] > data[i]) {
                        result++;
                    }
                }
            }
        }

        System.out.println(result > k ? 1 : 0);
    }
}
