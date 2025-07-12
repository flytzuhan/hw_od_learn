package com.huawei.od.excise.str.y2025.b.n003;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = Integer.parseInt(in.nextLine());
        String str = in.nextLine().replace(" ", "");
        for (int i = 1; i <= count; i++) {
            if (count % i == 0) {
                // 表示循环当前这个下标，可以达到重复的次数
                String tmp = str;
                tmp = tmp.replace(str.substring(0, i), "");
                if (tmp.isEmpty()) {
                    // 表示找到了这个子数组
                    for (int j = 0; j < i; j++) {
                        System.out.print(str.charAt(j));
                        if (j != i - 1) {
                            System.out.print(" ");
                        }
                    }
                    break;
                }
            }
        }
    }
}
