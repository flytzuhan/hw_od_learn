package com.huawei.od.excise.year25.b.n006;

import java.util.Scanner;

/**
 * 字符串加密 -- 动态规划
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int max = 4;
        for (int i = 0; i < n; i++) {
            String inputStr = in.nextLine();
            int len = inputStr.length();
            max = Math.max(max, len);
            // 设置偏移数组
            int[] offset = new int[max];
            offset[0] = 1;
            offset[1] = 2;
            offset[2] = 4;
            for (int j = 3; j < max; j++) {
                offset[j] = offset[j-1] + offset[j-2] + offset[j-3];
            }
            StringBuilder result = new StringBuilder();
            // 现在开始循环字符串的长度，开始处理字符串
            for (int j = 0; j < len; j++) {
                int res = (inputStr.charAt(j) - 'a' + offset[j]) % 26 + 'a';
                result.append((char) res);
            }
            System.out.println(result.toString());
        }
    }
}
