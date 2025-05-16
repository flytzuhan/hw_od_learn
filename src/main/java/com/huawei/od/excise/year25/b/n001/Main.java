package com.huawei.od.excise.year25.b.n001;

import java.util.Scanner;

/**
 * 对称美学 -- 逻辑模拟
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        for (int i = 0; i < t; i++) {
            long n = scanner.nextLong();
            double k = scanner.nextDouble();
            String res = find(n, k) == 'R' ? "red" : "blue";
            System.out.println(res);
        }
    }

    private static char find(long n, double k) {
        // 边界条件
        if (n == 1) {
            return 'R';
        }
        if (n == 2) {
            if (k == 0) {
                return 'B';
            } else {
                return 'R';
            }
        }

        // 现在开始计算n超过2的情况 折半查找
        double len = 1L << (n - 2);
        if (k >= len) {
            // 要查找的字符在后半段，根据规则，后半段跟上一个字符串保持一致，因此递归查找上一个字符串
            double pos = k - len;
            return find(n - 1, pos);
        } else {
            // 在前半段，根据规则，前半段跟上一个字符串取反
            return find(n - 1, k) == 'R' ? 'B' : 'R';
        }
    }
}
