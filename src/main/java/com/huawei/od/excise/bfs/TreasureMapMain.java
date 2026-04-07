package com.huawei.od.excise.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 小华地图寻宝 -- BFS
 */
public class TreasureMapMain {

    private static int[][] DIRS = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        // 现在先初始化地图数据
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 计算哪些地方有黄金
                int x = i;
                int sum = 0;
                while (x > 0) {
                    sum += x % 10;
                    x /= 10;
                }
                int y = j;
                while (y > 0) {
                    sum += y % 10;
                    y /= 10;
                }
                if (sum <= k) {
                    grid[i][j] = 1;
                }
            }
        }

        // 现在使用BFS算法来计算可以获取的黄金数量
        int count = 0;
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for (int[] dir : DIRS) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && grid[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
