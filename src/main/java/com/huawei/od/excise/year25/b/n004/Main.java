package com.huawei.od.excise.year25.b.n004;

import java.util.Scanner;

/**
 * 报文响应时间 -- 数据结构
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int c = in.nextInt();
        int[] t = new int[c];
        int[] m = new int[c];
        // 循环获取数据
        for (int i = 0; i < c; i++) {
            t[i] = in.nextInt();
            m[i] = in.nextInt();
        }
        // 设置响应时间
        int responseTime = 0;
        for (int i = 0; i < c; i++) {
            int maxResponseTime = 0;
            if (m[i] < 128) {
                maxResponseTime = m[i];
            } else {
                int exp = (m[i] & 0x70) >> 4;
                int mat = (m[i] & 0x0F);
                maxResponseTime = (mat | 0x10) << (exp + 3);
            }
            int newResponseTime = t[i] + maxResponseTime;
            // 计算最大响应时间
            if (i == 0 || newResponseTime < responseTime) {
                responseTime = newResponseTime;
            }
        }

        System.out.println(responseTime);
    }
}
