package com.huawei.od.excise.year25.c.n141;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * 污染水域 -- BFS
 */
public class Main {

    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] nums = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        if (nums.length == 0) {
            System.out.println(-1);
            return;
        }
        int n = (int) Math.sqrt(nums.length);
        // 声明一个n*n的数组
        int[][] grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            grid[i] = Arrays.copyOfRange(nums, i * n, (i + 1) * n);
        }
        // 现在开始计算
        System.out.println(bfs(grid));
    }

    public static int bfs(int[][] grid) {
        // 先获取所有的污染源
        Queue<int[]> queue = new LinkedList<>();
        int n = grid.length;
        int clean = 0;
        int day = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                } else {
                    clean++;
                }
            }
        }
        if (clean == 0) {
            return 0;
        }
        while (!queue.isEmpty() && clean > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] node = queue.poll();
                int x = node[0];
                int y = node[1];
                for (int[] direction : DIRECTIONS) {
                    int newX = x + direction[0];
                    int newY = y + direction[1];
                    if (newX >= 0 && newX < n && newY >= 0 && newY < n && grid[newX][newY] == 0) {
                        grid[newX][newY] = 1;
                        queue.offer(new int[]{newX, newY});
                        clean--;
                    }
                }
            }
            day++;
        }

        return day;
    }
}
