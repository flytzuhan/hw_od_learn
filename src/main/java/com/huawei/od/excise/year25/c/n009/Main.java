package com.huawei.od.excise.year25.c.n009;

import java.util.Scanner;

/**
 * 小明的幸运数 -- 逻辑模拟
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        // 当前位置和最大值
        int last = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            if (num == k) {
                if (num >= 0) {
                    last += num + 1;
                } else {
                    last += num - 1;
                }
            } else {
                last += num;
            }
            max = Math.max(max, last);
        }

        System.out.println(max);
    }
}
