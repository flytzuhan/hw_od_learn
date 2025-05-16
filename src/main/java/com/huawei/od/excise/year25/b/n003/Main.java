package com.huawei.od.excise.year25.b.n003;

import java.util.Scanner;

/**
 * 最小循环子数组 - KMP算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String str = scanner.nextLine().replace(" ", "");
        // 现在开始循环处理
        for (int i = 1; i < n; i++) {
            // 求约数，就是可以被整除的数
            if (str.length() % i == 0) {
                // 能被整除
                String tmp = str;
                tmp = tmp.replace(str.substring(0, i), "");
                if (tmp.equals("")) {
                    // 表示找到了这个子数组了
                    for (int j = 0; j < i; j++) {
                        System.out.print(str.charAt(j));
                        if (j != i - 1) {
                            // 判断是不是快到末尾了，如果没有达到末尾的话，就给添加上空格
                            System.out.print(" ");
                        }
                    }
                }
            }
        }
    }
}
