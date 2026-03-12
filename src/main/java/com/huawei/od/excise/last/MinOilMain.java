package com.huawei.od.excise.last;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 最少加油量 -- BFS
 */
public class MinOilMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 获取行和列
        int row = in.nextInt();
        int col = in.nextInt();
        in.nextLine();
        // 现在获取矩阵数据
        int[][] grid = new int[row][col];
        for (int i = 0; i < row; i++) {
            grid[i] = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        }

        // 现在要计算从(0,0)行驶到(row-1,col-1)点，起始点所需要的最少加油量，油箱最大容量是100，因此可以使用暴力破解，或者是二分查找
        int left = 1, right = 100;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canReach(grid, row, col, mid)) {
                // 满足条件，则向右缩小范围
                right = mid - 1;
                result = mid;
            } else {
                // 不能达到终点
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    private static boolean canReach(int[][] grid, int row, int col, int initOil) {
        // 处理边界条件
        if (grid[0][0] == 0 || grid[row - 1][col - 1] == 0) {
            return false;
        }
        if (grid[0][0] == -1) {
            initOil = 100;
        }
        // 判断初始油量能否满足条件
        if (initOil < grid[0][0]) {
            return false;
        }
        // 还需要使用一个矩阵来记录剩余的油量
        int[][] remainOil = new int[row][col];
        for (int i = 0; i < row; i++) {
            Arrays.fill(remainOil[i], -1);
        }
        remainOil[0][0] = initOil-grid[0][0];
        // 使BFS来进行处理
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, remainOil[0][0]});
        // 定义四个方向
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            // 获取当前的位置
            int[] poll = queue.poll();
            int curRow = poll[0], curCol = poll[1];
            int curOil = poll[2];
            // 判断是否到达了终点
            if (curRow == row - 1 && curCol == col - 1) {
                return true;
            }
            // 现在开始循环四个方向
            for (int[] direction : directions) {
                int nextRow = curRow + direction[0];
                int nextCol = curCol + direction[1];
                // 处理边界条件
                if (nextRow < 0 || nextRow >= row || nextCol < 0 || nextCol >= col) {
                    continue;
                }
                // 这里是障碍物
                if (grid[nextRow][nextCol] == 0) {
                    continue;
                }
                // 计算剩余油量
                int diffOil;
                // 如果这里是加油站的话
                if (grid[nextRow][nextCol] == -1) {
                    diffOil = 100;
                } else {
                    diffOil = curOil - grid[nextRow][nextCol];
                }
                // 如果剩余油量小于0，则无法行驶
                if (diffOil < 0) {
                    continue;
                }
                if (diffOil > remainOil[nextRow][nextCol]) {
                    // 只有剩余油量多的才能放入到队列中
                    remainOil[nextRow][nextCol] = diffOil;
                    queue.offer(new int[]{nextRow, nextCol, diffOil});
                }
            }
        }
        return false;
    }
}
