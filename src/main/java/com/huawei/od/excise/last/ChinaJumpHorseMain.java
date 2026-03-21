package com.huawei.od.excise.last;

import java.util.*;

/**
 * 跳马问题
 */
public class ChinaJumpHorseMain {

    private static int[][] directions = new int[][]{{2, 1}, {2, -1}, {1, 2}, {1, -2}, {-2, 1}, {-2, -1}, {-1, 2}, {-1, -2}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 获取行和列数据
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        scanner.nextLine();
        // 现在要获取棋盘数据
        String[][] grid = new String[row][col];
        // 存储马的位置
        List<Horse> horses = new ArrayList<>();
        // 处理数据
        for (int i = 0; i < row; i++) {
            String[] lineData = scanner.nextLine().split(" ");
            grid[i] = lineData;
            // 现在要判断哪些位置是马
            for (int j = 0; j < col; j++) {
                if (!lineData[j].equals(".")) {
                    horses.add(new Horse(i, j, Integer.parseInt(lineData[j])));
                }
            }
        }
        if (horses.isEmpty() || horses.size() == 1) {
            // 没有马
            System.out.println(0);
            return;
        }
        // 记录要到达这个位置的最少距离
        int minSteps = Integer.MAX_VALUE;

        // 找到了多个数据源，那就是多个源头到同一个地方，那就枚举所有可能的目标位置，然后判断马是否能到达这个位置
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 移动了多少步
                int totalStep = 0;
                boolean canReach = true;
                for (Horse horse : horses) {
                    int step = bfs(horse.row, horse.col, horse.maxStep, i, j, row, col);
                    if (step == -1) {
                        canReach = false;
                        break;
                    }
                    totalStep += step;
                }

                // 表示所有的马都能到达这里
                if (canReach) {
                    minSteps = Math.min(minSteps, totalStep);
                }
            }
        }

        System.out.println(minSteps == Integer.MAX_VALUE ? 0 : minSteps);
    }

    private static int bfs(int startRow, int startCol, int maxStep, int targetRow, int targetCol, int row, int col) {
        // 用来计算从当前位置走到目标位置在多少步之内可以完成
        // 终止条件
        if (startRow == targetRow && startCol == targetCol) {
            // 说明到达了终点了
            return 0;
        }
        boolean[][] visited = new boolean[row][col];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol, 0});
        visited[startRow][startCol] = true;
        // 循环开始处理
        while (!queue.isEmpty()) {
            // 获取当前这个位置
            int[] curNode = queue.poll();
            int curRow = curNode[0], curCol = curNode[1];
            int curStep = curNode[2];
            if (curStep >= maxStep) {
                continue;
            }
            for (int[] direction : directions) {
                int newRow = curRow + direction[0];
                int newCol = curCol + direction[1];
                int newStep = curStep + 1;
                // 处理边界条件
                if (newRow < 0 || newRow >= row || newCol < 0 || newCol >= col) {
                    continue;
                }
                // 判断是否到达了目标地址
                if (newRow == targetRow && newCol == targetCol) {
                    return newStep;
                }
                // 判断如果是地点没有被访问过，且不超过最大步数的限制
                if (!visited[newRow][newCol] && newStep < maxStep) {
                    queue.offer(new int[]{newRow, newCol, newStep});
                    visited[newRow][newCol] = true;
                }
            }
        }
        return -1;
    }

    static class Horse {
        int col;
        int row;
        int maxStep;
        public Horse(int row, int col, int maxStep) {
            this.col = col;
            this.row = row;
            this.maxStep = maxStep;
        }
    }
}
