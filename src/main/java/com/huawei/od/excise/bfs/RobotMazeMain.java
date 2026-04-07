package com.huawei.od.excise.bfs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 机器人走迷宫 -- BFS
 */
public class RobotMazeMain {

    private static int[][] DIRS = new int[][]{{0, 1}, {1, 0}};

    private static int[][] UN_DIRS = new int[][]{{0, -1}, {-1, 0}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        in.nextLine();

        // 现在来初始化数据并设置障碍物
        int[][] grid = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(grid[i], 1);
        }

        for (int i = 0; i < k; i++) {
            int[] line = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            grid[line[0]][line[1]] = 0;
        }

        // 现在开始从起点到终点统计不可达方格
        boolean[][] unReachable = new boolean[n][m];
        bfs(grid, 0, 0, unReachable, true);
        boolean[][] visited = new boolean[n][m];
        bfs(grid, n - 1, m - 1, visited, false);

        int unReachableCount = 0;
        int trapCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !unReachable[i][j]) {
                    unReachableCount++;
                }
                if (grid[i][j] == 1 && !visited[i][j]) {
                    trapCount++;
                }
            }
        }

        System.out.println(trapCount + " " + unReachableCount);
    }

    private static void bfs(int[][] grid, int sx, int sy, boolean[][] unReachable, boolean flag) {
        Queue<int[]> queue = new LinkedList<>();
        unReachable[sx][sy] = true;
        queue.add(new int[]{sx, sy});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for (int[] dir : flag ? DIRS : UN_DIRS) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                if (nx < 0 || nx >= grid.length || ny < 0 || ny >= grid[0].length || grid[nx][ny] == 0 || unReachable[nx][ny]) {
                    continue;
                }
                unReachable[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
    }
}
