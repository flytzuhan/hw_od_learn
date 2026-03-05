package com.huawei.od.excise.last;

import java.util.*;

/**
 * Excel单元格数值计算
 */
public class ExcelSumValueMain {

    // 记录原始输入的数据
    static String[][] originData;
    // 经过计算后的单元格数据
    static Integer[][] computedData;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 获取行数和列数
        int inputRow = in.nextInt();
        int inputCol = in.nextInt();
        originData = new String[inputRow][inputCol];
        computedData = new Integer[inputRow][inputCol];
        in.nextLine();
        // 接下来就是获取对应的这个区域的数据
        for (int i = 0; i < inputRow; i++) {
            for (int j = 0; j < inputCol; j++) {
                originData[i][j] = in.next();
            }
        }
        in.nextLine();
        // 获取操作区域的数据
        String[] operationRegion = in.nextLine().split(":");
        int[] leftTop = getCell(operationRegion[0]);
        int[] rightBottom = getCell(operationRegion[1]);

        // 定义求和的默认值为0
        int regionSum = 0;

        // 现在要在选定的这个区域里面去计算总和
        for (int row = leftTop[0]; row <= rightBottom[0]; row++) {
            for (int col = leftTop[1]; col <= rightBottom[1]; col++) {
                regionSum += getCellValue(row, col);
            }
        }

        System.out.println(regionSum);
    }

    private static int[] getCell(String cell) {
        // 现在要获取这个单元格的坐标位置
        int col = cell.charAt(0) - 'A';
        // A1，这里的行数是1，但是对应到存储数据中的行应该是0
        int row = Integer.parseInt(cell.substring(1)) - 1;
        return new int[]{row, col};
    }

    private static int getCellValue(int row, int col) {
        // 要获取这个单元格的值，就要先判断这个单元格的数据是否已经计算过了，计算过了的话，就直接返回
        if (computedData[row][col] != null) {
            return computedData[row][col];
        }
        // 现在先获取这个单元格的原始数据
        String cellData = originData[row][col];
        if (cellData.startsWith("=")) {
            // 表示以=开头，需要进行计算
            return computeCellValue(cellData.substring(1));
        } else {
            // 纯数字，直接返回数据
            return Integer.parseInt(cellData);
        }
    }

    private static int computeCellValue(String cellData) {
        // 现在要在这个字符串中查找操作符
        int optIndex = -1; // 表示没有找到对应的操作符
        char optType = '+';
        for (char c : cellData.toCharArray()) {
            if (c == '+' || c == '-') {
                // 表示找到了操作符
                optIndex = cellData.indexOf(c);
                optType = c;
                break;
            }
        }

        // 如果没有找到操作符
        if (optIndex == -1) {
            // 表示这个只是一个单元格的数据，先找到对应的这个单元格的行和列坐标
            int[] cellDataArr = getCell(cellData);
            // 根据这个行和列的坐标去获取对应的值，比如A1，那么就是获取行0列0位置的值
            return getCellValue(cellDataArr[0], cellDataArr[1]);
        }

        // 下面是找到了操作符的话，用操作符把这两个拆分开来
        String leftCell = cellData.substring(0, optIndex);
        String rightCell = cellData.substring(optIndex + 1);
        // 现在分别获取左右两边的值
        int leftValue;
        int rightValue;
        if (isNumber(leftCell)) {
            // 表示左边的这是一个数字
            leftValue = Integer.parseInt(leftCell);
        } else {
            // 获取左侧的这个字符串的坐标
            int[] leftCellArr = getCell(leftCell);
            leftValue = getCellValue(leftCellArr[0], leftCellArr[1]);
        }
        if (isNumber(rightCell)) {
            // 获取右侧的这个数字
            rightValue = Integer.parseInt(rightCell);
        } else {
            // 获取右侧的这个字符串的坐标
            int[] rightCellArr = getCell(rightCell);
            rightValue = getCellValue(rightCellArr[0], rightCellArr[1]);
        }
        return optType == '+' ? leftValue + rightValue : leftValue - rightValue;
    }

    private static boolean isNumber(String cellValue) {
        for (char c : cellValue.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }
}
