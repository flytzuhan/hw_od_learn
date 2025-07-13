package com.huawei.od.excise.str.y2025.b.n043;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 字符串分割转换/密钥格式化
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = Integer.parseInt(in.nextLine());
        String[] params = in.nextLine().split("-");

        // 先找到第一个
        String first = params[0];
        // 先把所有的字符串拼接起来
        String[] strArr = Arrays.copyOfRange(params, 1, params.length);
        String str = String.join("", strArr);
        char[] chars = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(first);
        for (int i = 0; i < chars.length; i++) {
            if (i % k == 0) {
                sb.append("-");
            }
            sb.append(Character.toUpperCase(chars[i]));
        }
        System.out.println(sb.toString());
    }
}
