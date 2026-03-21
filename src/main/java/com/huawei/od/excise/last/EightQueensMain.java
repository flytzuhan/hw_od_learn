package com.huawei.od.excise.last;

import java.util.ArrayList;
import java.util.List;

/**
 * 八皇后问题
 */
public class EightQueensMain {

    static List<int[]> paths = new ArrayList<>();

    public static void main(String[] args) {
        int[] queen = new int[8]; // 用来存储i行皇后所在的j列位置

        // 从0行这个位置开始列举他们所在的行的位置
        dfs(queen, 0);

        printBoard(queen);
    }

    private static void dfs(int[] queen, int row) {
        // 判断终止条件
        if (row == 8) {
            paths.add(queen.clone());
            return;
        }
        // 现在要在row行中选择第j列来放这个皇后
        for (int col = 0; col < 8; col++) {
            // 判断能否在当前这个位置放
            if (isSafe(queen, row, col)) {
                // 那就开始处理下一行
                queen[row] = col;
                dfs(queen, row + 1);
            }
        }
    }

    private static boolean isSafe(int[] queen, int row, int col) {
        // 如何检查当前这个位置是否是安全的，没一行，每一列，每一个对角线都不能存放
        for (int i = 0; i < row; i++) {
            int prevCol = queen[i];

            if (prevCol == col) return false;

            // 对角线
            if (Math.abs(i - row) == Math.abs(prevCol - col)) return false;
        }

        return true;
    }

    static void printBoard(int[] queens) {
        for (int r = 0; r < 8; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < 8; c++) {
                sb.append(queens[r] == c ? " Q " : " . ");
            }
            System.out.println(sb);
        }
        System.out.println();
    }
}
