package com.huawei.od.excise.greedy.y2025.c.n004;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 小朋友来自多少小区 -- 贪心算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] zones = new int[1000];
        for (int num : nums) {
            zones[num]++;
        }
        // 统计来自多少个小区的话
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            if (zones[i] != 0) {
                set.add(i);
            }
        }
        System.out.println(set.size());
        // 计算有多少个人的话
        System.out.println(set.stream().map(t -> t + 1).reduce(Integer::sum).orElse(0));
    }
}
