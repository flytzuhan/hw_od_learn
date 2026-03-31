package com.huawei.od.excise.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 开心消消乐 -- BFS
 */
public class HappyMatchedMain {

    private static final int[][] DIRECTIONS = {{0, 1}, {-1, 1}, {-1, -1}, {0, -1}, {1, 0}, {-1, 0}, {1, -1}, {1, 1}};

    private static int[][] grid;

    private static boolean[][] visited;

    private static int n, m;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        in.nextLine();

        // 声明一个二维数组
        grid = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] = in.nextInt();
            }
        }

        int count = 0;
        // 现在要开始计算
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 计算每一个点，如果点击了之后变成1，最后需要点击多少次才能让数字全部变成1
                if (grid[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void bfs(int sx, int sy) {
        // 使用bfs来处理开始节点，看看能否将所有的节点都变成1
        Queue<int[]> queue = new LinkedList<>();
        visited[sx][sy] = true;
        queue.add(new int[]{sx, sy});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for (int[] direction : DIRECTIONS) {
                int nx = x + direction[0];
                int ny = y + direction[1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }
}
