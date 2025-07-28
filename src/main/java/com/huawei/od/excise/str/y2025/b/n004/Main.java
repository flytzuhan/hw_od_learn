package com.huawei.od.excise.str.y2025.b.n004;

import java.util.Scanner;

/**
 * 字符串子序列II -- 字符串处理
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String target = in.nextLine();
        String source = in.nextLine();
        char targetChat = target.charAt(target.length() - 1);
        int i = source.length() - 1;
        int count = 1;
        while (true) {
            if (i < 0) {
                break;
            } else {
                char current = source.charAt(i);
                if (current == targetChat) {
                    count++;
                    // 现在要判断是否还有剩余的字符串
                    if (target.length() - count >= 0) {
                        // 移动到下一个元素的位置上
                        targetChat = target.charAt(target.length() - count);
                    } else {
                        System.out.println(i);
                        return;
                    }
                }
                i--;
            }
        }
        System.out.println(-1);
    }
}
