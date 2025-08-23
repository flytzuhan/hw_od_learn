package com.huawei.od.excise.year25.c.n107;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 高效的任务规划 -- 动态规划
 */
public class Main {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = Integer.parseInt(in.nextLine());
        for (int i = 0; i < m; i++) {
            int n = Integer.parseInt(in.nextLine());
            List<int[]> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                list.add(nums);
            }
            list.sort((o1, o2) -> o2[1] - o1[1]);
            int setupTime = 0;
            int maxTime = 0;
            for (int[] nums : list) {
                setupTime += nums[0];
                maxTime = Math.max(maxTime, setupTime + nums[1]);
            }
            System.out.println(maxTime);
        }
    }
}
