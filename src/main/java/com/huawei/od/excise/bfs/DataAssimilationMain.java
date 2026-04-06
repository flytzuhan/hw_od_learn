package com.huawei.od.excise.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 数值同化 -- BFS
 */
public class DataAssimilationMain {

    private static int[][] DIRS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 先来获取行和列的数据
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();

        // 现在来开始获取数据
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = in.nextInt();
            }
        }

        // 现在需要将开始设置为1，然后开始计算所有非1元素的个数
        grid[0][0] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            // 获取队列中的元素
            int[] poll = queue.poll();
            int x = poll[0];
            int y = poll[1];
            // 从四个方向来处理
            for (int[] dir : DIRS) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                // 判断是否越界
                if (nx < 0 || nx >= n || ny < 0 || ny >= m || grid[nx][ny] == 2) {
                    continue;
                }
                if (grid[nx][ny] == 0) {
                    grid[nx][ny] = 1;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        // 现在开始计算剩余的数量
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    count++;
                }
            }
        }
        System.out.println(n * m - count);
    }
}
