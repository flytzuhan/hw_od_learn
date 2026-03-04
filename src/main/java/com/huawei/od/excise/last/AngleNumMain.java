package com.huawei.od.excise.last;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 构建的正方形数量
 */
public class AngleNumMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 获取顶点个数
        int n = Integer.parseInt(in.nextLine());
        int[][] points = new int[n][2];
        if (n <= 3) {
            // 无法构成正方形
            System.out.println(0);
            return;
        }

        // 存储所有的顶点方便查找
        Set<String> pointSet = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int[] point = Arrays.stream(in.nextLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            points[i] = point;
            pointSet.add(point[0] + "," + point[1]);
        }

        // 定义构成的正方形的数量
        int count = 0;
        // 枚举所有的两个点的组合，作为正方形的一条边AB
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int x1 = points[i][0];
                int y1 = points[i][1];
                int x2 = points[j][0];
                int y2 = points[j][1];
                // 计算向量，向量表示x从x1到x2，y从y1到y2
                int dx = x2 - x1, dy = y2 - y1;
                // 方案一，AB为边，顺时针找C，D
                // C 就是B顺时针旋转90° = B的位置 + (dy, -dx)，D就是A顺时针旋转90° = A的位置 + (dy, -dx)
                int cx1 = x2 + dy, cy1 = y2 - dx;
                int dx1 = x1 + dy, dy1 = y1 - dx;

                // 方案二：AB为边，逆时针找CD
                // C 就是B逆时针旋转90° = B的位置 + (-dy, dx)，D就是A逆时针旋转90° = A的位置 + (-dy, dx)
                int cx2 = x2 - dy, cy2 = y2 + dx;
                int dx2 = x1 - dy, dy2 = y1 + dx;

                if (pointSet.contains(cx1 + "," + cy1) && pointSet.contains(dx1 + "," + dy1)) {
                    count++;
                }
                if (pointSet.contains(cx2 + "," + cy2) && pointSet.contains(dx2 + "," + dy2)) {
                    count++;
                }
            }
        }

        // 每个正方形被4条边各枚举一次 * 2个方向 = 计数了4次，实际上要 ➗4
        System.out.println(count / 4);
    }
}
