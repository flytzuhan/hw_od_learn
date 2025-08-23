package com.huawei.od.excise.year25.b.n115;

import java.util.Scanner;

/**
 * DNA序列 -- 滑动窗口
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char[] chars = str.toCharArray();
        int k = in.nextInt();
        // 使用滑动窗口
        int left = 0;
        int n = chars.length;
        int last = 0;
        String result = "";
        int count = 0;
        // 先获取第一个窗口
        for (int i = 0; i < k; i++) {
            if (chars[i] == 'G' || chars[i] == 'C') {
                count++;
            }
        }
        result = count > 0 ? str.substring(left, k) : "";
        last = count;
        for (int right = k; right < n; right++) {
            if (chars[right] == 'G' || chars[right] == 'C') {
                count++;
            }
            if (chars[left] == 'G' || chars[left] == 'C') {
                count--;
            }
            left++;
            result = count > last ? str.substring(left, right+1) : result;
            last = Math.max(count, last);
        }

        System.out.println(result);
    }
}
