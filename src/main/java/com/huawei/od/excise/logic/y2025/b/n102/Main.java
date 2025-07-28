package com.huawei.od.excise.logic.y2025.b.n102;

import java.util.Scanner;

/**
 * 102. 绘图机器，计算面积 -- 逻辑模拟
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int E = in.nextInt();
        in.nextLine();
        int area = 0;
        int x = 0, y = 0;
        for (int i = 0; i < n; i++) {
            String[] points = in.nextLine().split(" ");
            int curX = Integer.parseInt(points[0]);
            int curY = Integer.parseInt(points[1]);

            area += (curX - x) * Math.abs(y);
            x = curX;
            y += curY;
        }

        if (x < E) {
            area += (E - x) * Math.abs(y);
        }
        System.out.println(area);
    }
}
