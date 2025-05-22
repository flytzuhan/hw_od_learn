package com.huawei.od.excise.year25.b.n005;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * 流水线 -- 排序算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Integer[] triple = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        int m = triple[0];
        int n = triple[1];
        // 现在获取第二行数据
        Integer[] tasks = Arrays.stream(in.nextLine().split(" ")).map(Integer::parseInt).toArray(Integer[]::new);
        // 对这个进行排序
        Arrays.sort(tasks, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        // 现在开始对这个进行分组
        int[] temp = new int[m];
        for (int i = 0; i < n; i++) {
            temp[i % m] += tasks[i];
        }

        System.out.println(Arrays.stream(temp).max().getAsInt());
    }
}
