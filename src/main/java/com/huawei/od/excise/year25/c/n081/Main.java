package com.huawei.od.excise.year25.c.n081;

import java.util.Scanner;

/**
 * 猴子爬山 -- 递归
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int step1 = 1;
        int step2 = 1;
        int step3 = 2;
        int step4 = n == 1 || n== 2 ? 1 : 2;
        for (int i = 4; i <= n; i++) {
            step4 = step3 + step1;
            step1 = step2;
            step2 = step3;
            step3 = step4;
        }

        System.out.println(step4);
    }
}
