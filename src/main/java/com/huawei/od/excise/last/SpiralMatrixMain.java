package com.huawei.od.excise.last;

import java.util.*;

/**
 * 螺旋矩阵
 */
public class SpiralMatrixMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 获取输入的总数和行数
        int totalNum = in.nextInt();
        int row = in.nextInt();
        // 现在要计算最少的列是多少
        int col = (totalNum + row - 1) / row; // 向上取整
        // 现在创建这样一个二维数组
        String[][] matrix = new String[row][col];
        // 现在先填充默认值
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = "*";
            }
        }

        // 现在要开始循环的填充真实的数据了
        int top = 0, bottom = row - 1;
        int left = 0, right = col - 1;
        int index = 1;
        while (top <= bottom && left <= right && index <= totalNum) {
            // 先从左往右填充行
            for (int i = left; i <= right && index <= totalNum; i++) {
                matrix[top][i] = String.valueOf(index++);
            }
            top++;
            // 然后从上往下填充列
            for (int i = top; i <= bottom && index <= totalNum; i++) {
                matrix[i][right] = String.valueOf(index++);
            }
            right--;
            // 判断如果top<bottom的话，循环填充最下面的一行
            if (top <= bottom) {
                for (int i = right; i >= left && index <= totalNum; i--) {
                    matrix[bottom][i] = String.valueOf(index++);
                }
            }
            bottom--;
            // 最后填充最左边的数据
            if (left <= right) {
                for (int i = bottom; i >= top && index <= totalNum; i--) {
                    matrix[i][left] = String.valueOf(index++);
                }
            }
            left++;
        }

        // 现在打印数据
        for (int i = 0; i < row; i++) {
            System.out.println(String.join(" ", matrix[i]));
        }
    }
}
