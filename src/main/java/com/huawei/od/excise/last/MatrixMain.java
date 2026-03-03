package com.huawei.od.excise.last;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 矩阵匹配 -- 二分查找+匈牙利算法
 */
public class MatrixMain {

    // 声明行和列，以及最k大的变量
    private static int INPUT_ROW, INPUT_COL, INPUT_KTH;
    // 声明一个二维数组来存储N*M的矩阵数据
    private static int[][] INPUT_MATRIX = new int[INPUT_ROW][INPUT_COL];

    //  匹配到的列数组
    private static int[] matched = new int[INPUT_COL];

    // 声明一个数组表示是否被访问过
    private static boolean[] visited;

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        // 先从命令行中获取到行列以及K大的值
        INPUT_ROW = inputScanner.nextInt();
        INPUT_COL = inputScanner.nextInt();
        INPUT_KTH = inputScanner.nextInt();
        // 这一行数据获取完毕了之后，要跳转到下一行
        inputScanner.nextLine();

        // 根据题目的意思，要先获取上下边界，
        int rowMinVal = 1, rowMaxVal = Integer.MIN_VALUE;
        for (int i = 0; i < INPUT_ROW; i++) {
            for (int j = 0; j < INPUT_COL; j++) {
                INPUT_MATRIX[i][j] = inputScanner.nextInt();
                // 找到这一行的最大值，也就是上边界
                rowMaxVal = Math.max(rowMaxVal, INPUT_MATRIX[i][j]);
            }
        }

        // 现在开始使用二分查找来获取
        while (rowMinVal <= rowMaxVal) {
            // 先得到二分查找的中间值
            int rowMidVal = rowMinVal + (rowMaxVal - rowMinVal) / 2;
            if (checkMidValIsStatisfyCurrentCondition(rowMidVal)) {
                // 表示当前这个中间值满足条件，可以继续往下找
                rowMaxVal = rowMidVal - 1;
            } else {
                // 表示当前这个中间值不满足条件，要往上边界找
                rowMinVal = rowMidVal + 1;
            }
        }

        // 打印出来最终要找的值
        System.out.println(rowMinVal);
    }

    private static boolean checkMidValIsStatisfyCurrentCondition(int midVal) {
        // 每次二分查询的循环时，都是重置这个匹配的列数组
        Arrays.fill(matched, -1);
        // 声明一个数组用来代表是否被访问过
        visited = new boolean[INPUT_COL];
        // 统计满足条件的数量
        int smallerCount = 0;
        for (int i = 0; i < INPUT_ROW; i++) {
            // 循环每一行的数据，先初始化这个访问数组为false，代表没有访问过
            Arrays.fill(visited, false);
            if (dfs(i, midVal)) {
                // 表示满足条件
                smallerCount++;
            }
        }

        return smallerCount >= INPUT_ROW - INPUT_KTH + 1;
    }

    // 现在使用深度优先算法来进行匹配
    private static boolean dfs(int row, int midVal) {
        // 循环遍历每一列，判断是否满足条件
        for (int col = 0; col < INPUT_COL; col++) {
            if (!visited[col] && INPUT_MATRIX[row][col] <= midVal) {
                // 表示这个行的这个数字既没有访问过，而且矩阵中对应的数字也小于中间值
                visited[col] = true;
                if (matched[col] == -1 || dfs(matched[col], midVal)) {
                    matched[col] = row;
                    return true;
                }
            }
        }

        return false;
    }
}
