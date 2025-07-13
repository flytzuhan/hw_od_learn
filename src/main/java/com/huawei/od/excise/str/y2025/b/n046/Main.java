package com.huawei.od.excise.str.y2025.b.n046;

import java.util.Scanner;

/**
 * 计算某个字符出现的次数
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char target = in.nextLine().charAt(0);
        int length = str.length();
        int replacedLen = str.toLowerCase().replaceAll(String.valueOf(target).toLowerCase(), "").length();
        System.out.println(length - replacedLen);
    }
}
