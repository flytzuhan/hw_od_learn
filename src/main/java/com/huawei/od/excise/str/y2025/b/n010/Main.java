package com.huawei.od.excise.str.y2025.b.n010;

import java.util.Scanner;

/**
 * 字符串子序列III -- 字符串处理
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        String str2 = in.nextLine();
        char target = str1.charAt(str1.length()-1);
        int i = str2.length()-1;
        int count = 1;
        while (true) {
            if (i < 0) {
                break;
            } else {
                // 获取字符，跟目标字符进行对比
                char current = str2.charAt(i);
                if (current == target) {
                    // 说明这字符相等了
                    count++;
                    if (str1.length()-count >= 0) {
                        target = str1.charAt(str1.length()-count);
                    } else {
                        System.out.println(i);
                        return;
                    }
                }
            }
            i--;
        }
        System.out.println(-1);
    }
}
