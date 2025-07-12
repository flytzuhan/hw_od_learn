package com.huawei.od.excise.sort.y2025.b.n005;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] triangle = in.nextLine().split(" ");
        int m = Integer.parseInt(triangle[0]);
        int n = Integer.parseInt(triangle[1]);
        // 创建一个数组
        int[] params = new int[n];
        for (int i = 0; i < n; i++) {
            params[i] = in.nextInt();
        }

        // 对这个进行排序
        Arrays.sort(params);
        // 创建一个结果数组
        int[] result = new int[m];
        for(int i = 0; i < n; i++) {
            result[i % m] += params[i];
        }

        System.out.println(Arrays.stream(result).max().getAsInt());
    }
}
