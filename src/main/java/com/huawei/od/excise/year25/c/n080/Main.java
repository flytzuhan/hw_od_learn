package com.huawei.od.excise.year25.c.n080;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 查找众数和中位数 -- 逻辑模拟
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 使用map来存储数据
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // 众数
        int max = map.values().stream().mapToInt(Integer::intValue).max().getAsInt();
        List<Integer> list = map.entrySet().stream().filter(obj -> obj.getValue().equals(max)).map(Map.Entry::getKey).collect(Collectors.toList());
        list.sort(Integer::compareTo);
        if (list.size() % 2 == 1) {
            System.out.println(list.get(list.size() / 2));
        } else {
            int a = list.get(list.size() / 2 - 1);
            int b = list.get(list.size() / 2);
            System.out.println((a + b) / 2);
        }
    }
}
