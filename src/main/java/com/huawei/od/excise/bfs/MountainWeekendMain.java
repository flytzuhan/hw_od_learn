package com.shadow.bfs;

import java.util.*;

/**
 * 周末爬山 -- BFS
 */
public class MountainWeekendMain {
    private static int[][] DIRS = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        int k = in.nextInt();
        in.nextLine();

        // 现在先记录存储数据并初始化
        int[][] grid = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                grid[i][j] = in.nextInt();
            }
        }

        // 现在开始使用BFS来处理这个问题
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        visited[0][0] = true;
        queue.add(new int[]{0, 0, 0});
        int maxH = 0;
        int minStep = 0;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            int step = cur[2];
            for(int[] dir : DIRS) {
                int nx = dir[0] + x;
                int ny = dir[1] + y;
                int nStep = step + 1;
                // 边界值跳过
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                // 已经访问过的值跳过
                if(visited[nx][ny]) {
                    continue;
                }
                // 对于超过k值高度的，也要跳过
                if(Math.abs(grid[nx][ny]-grid[x][y]) > k) {
                    continue;
                }
                // 现在是不超过k的数据
                if(grid[nx][ny] > max) {
                    // 更新最短距离和最大高度
                    max = grid[nx][ny];
                    minStep = Math.max(minStep, nStep);
                }        
                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny, nStep});    
            }
        }
        System.out.println(max + " " + minStep);
    }
}