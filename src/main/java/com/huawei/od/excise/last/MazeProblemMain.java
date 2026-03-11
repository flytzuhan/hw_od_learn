package com.huawei.od.excise.last;

import java.util.*;

/**
 * 迷宫问题 - BFS
 */
public class MazeProblemMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        // 先获取迷宫的行和列
        int row = in.nextInt();
        int col = in.nextInt();
        in.nextLine();
        // 现在是要将迷宫数据记录下来
        int[][] grid = new int[row][col];
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                // 记录这个数据
                grid[i][j] = in.nextInt();
            }
        }

        // 记录循环扩散的路径信息
        List<int[]> pathData = bfs(grid, row, col);
    }

    private static List<int[]> bfs(int[][] grid, int r, int c) {
        // 记录循环扩散的路径信息
        Queue<List<int[]>> paths = new LinkedList<>();
        List<int[]> path = new ArrayList<>();
        path.add(new int[]{0, 0});
        paths.add(path);
        // 定义一个数组，用来表示这个位置是否访问过
        boolean[][] visited = new boolean[r][c];
        visited[0][0] = true;
        // 定义四个方向用来处理下一个节点
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!path.isEmpty()) {
            // 先要获取最开始的这个路径
            List<int[]> currentPath = paths.poll();
            // 获取开始的节点
            int[] currentNode = currentPath.get(currentPath.size()-1);
            int row = currentNode[0];
            int col = currentNode[1];
            if (row == r-1 && col == c-1) {
                // 表示达到了终点了
                return currentPath;
            }
            // 按照四个方向来判断要如何走下一个节点
            for (int[] direction : directions) {
                int newRow = direction[0] + row;
                int newCol = direction[1] + col;

                // 只有没有被访问过，且不是墙，也没有超出边界范围的，才能正常添加进去
                if (newRow >= 0 && newRow < r && newCol >= 0 && newCol < c && grid[newRow][newCol] == 0 && !visited[newRow][newCol]) {
                    // 这样的节点才能加入进来，
                    visited[newRow][newCol] = true;
                    List<int[]> newPath = new ArrayList<>(currentPath);
                    newPath.add(new int[]{newRow, newCol});
                    paths.offer(newPath);
                }
            }
        }
        return path;
    }
}
