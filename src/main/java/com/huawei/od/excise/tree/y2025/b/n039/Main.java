package com.huawei.od.excise.tree.y2025.b.n039;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 寻找最大价值的矿堆 -- BFS
 */
public class Main {

    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid = new int[300][300];
        int index = 0;
        while (in.hasNextLine()) {
            grid[index++] = Arrays.stream(in.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int max = 0;
        for (int i = 0; i < 300; i++) {
            for (int j = 0; j < 300; j++) {
                max = Math.max(max, bfs(i, j, grid));
            }
        }

        System.out.println(max);
    }

    private static int bfs(int x, int y, int[][] grid) {
        // 判断如果起始位置是0的话，返回0
        if (grid[x][y] == 0) {
            return 0;
        }
        int sum = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int i = poll[0];
            int j = poll[1];
            // 如果是已经访问过的，就跳过
            if (grid[i][j] == 0) {
                continue;
            }
            sum += grid[i][j];
            grid[i][j] = 0;
            for (int[] direction : DIRECTIONS) {
                int nx = i + direction[0];
                int ny = i + direction[1];
                if (nx >= 0 && nx < 300 && ny >= 0 && ny < 300 && grid[nx][ny] > 0) {
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        return sum;
    }
}
