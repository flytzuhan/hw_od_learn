package com.huawei.od.excise.data.y2025.c.n002;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 最富裕的小家庭 -- 数据结构
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = in.nextInt();
        }
        in.nextLine();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int[] parts = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            map.put(parts[1], map.getOrDefault(parts[1], 0) + nums[parts[1]]);
            map.put(parts[0], map.getOrDefault(parts[0], nums[parts[0]]) + map.get(parts[1]));
        }

        // 现在计算所有当中的最大值
        System.out.println(map.values().stream().max(Integer::compareTo).orElse(0));
    }
}
