package com.huawei.od.excise.tree.y2025.b.n020;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 矩阵中1的个数 -- BFS
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        in.nextLine();
        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = in.nextInt();
            }
        }
        // 先将起始位置的元素值设置为1
        grid[0][0] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        int result = 1;
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int x = poll[0], y = poll[1];
            if (x + 1 < m && grid[x + 1][y] == 0) {
                grid[x + 1][y] = 1;
                result++;
                queue.offer(new int[]{x + 1, y});
            }
            if (x - 1 >= 0 && grid[x - 1][y] == 0) {
                grid[x - 1][y] = 1;
                result++;
                queue.offer(new int[]{x - 1, y});
            }
            if (y + 1 < n && grid[x][y + 1] == 0) {
                grid[x][y + 1] = 1;
                result++;
                queue.offer(new int[]{x, y + 1});
            }
            if (y - 1 >= 0 && grid[x][y - 1] == 0) {
                grid[x][y - 1] = 1;
                result++;
                queue.offer(new int[]{x, y - 1});
            }
        }

        System.out.println(m * n - result);
    }
}
