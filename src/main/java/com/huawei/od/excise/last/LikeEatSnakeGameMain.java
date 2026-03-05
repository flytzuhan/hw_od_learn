package com.huawei.od.excise.last;

import java.util.*;

/**
 * 贪吃蛇游戏
 */
public class LikeEatSnakeGameMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 获取操作数据的序列
        String[] operations = scanner.nextLine().split(" ");
        // 获取地图的行和列数据
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        scanner.nextLine();
        // 现在开始读取地图上的数据
        char[][] snakeMap = new char[row][col];
        // 记录蛇头位置
        int startRow = 0, startCol = 0;
        for (int i = 0; i < row; i++) {
            String[] line = scanner.nextLine().split(" ");
            for (int j = 0; j < col; j++) {
                snakeMap[i][j] = line[j].charAt(0);
                if (snakeMap[i][j] == 'H') {
                    startRow = i;
                    startCol = j;
                }
            }
        }
        // 要使用双端队列来存储蛇的移动路径，方便后续进行蛇头和蛇尾的处理
        Deque<int[]> snakePath = new LinkedList<>();
        // 还要使用Set来存储蛇的身体部分，方便对蛇头是否碰撞到身体进行处理
        Set<String> snakeBody = new HashSet<>();
        // 找到了蛇头的位置之后，定义默认的蛇头方向
        int[] direction = new int[] {-1, 0};
        // 先记录蛇头的位置
        snakePath.addFirst(new int[]{startRow, startCol});
        snakeBody.add(startRow + "," + startCol);
        boolean gameOver = false;
        // 现在按照操作符来进行处理
        for (String operation : operations) {
            if (gameOver) break;
            switch (operation) {
                case "U":
                    direction = new int[]{-1, 0};
                    break;
                case "D":
                    direction = new int[]{1, 0};
                    break;
                case "L":
                    direction = new int[]{0, -1};
                    break;
                case "R":
                    direction = new int[]{0, 1};
                    break;
                case "G":
                    // 前进一格,先从双端队列中获取到蛇头的坐标
                    int[] headData = snakePath.peekFirst();
                    int newRow = headData[0] + direction[0];
                    int newCol = headData[1] + direction[1];
                    // 判断是否越界
                    if (newRow < 0 || newRow >= row || newCol < 0 || newCol >= col) {
                        // 说明蛇要跑出去了，游戏结束
                        gameOver = true;
                        break;
                    }
                    // 判断当前位置是否是食物
                    boolean isFood = snakeMap[newRow][newCol] == 'F';
                    String newHeadKey = newRow + "," + newCol;
                    if (isFood) {
                        // 表示上面是一个食物，需要吃掉这个食物，蛇头要移动到新位置，蛇尾不用变动
                        snakeMap[newRow][newCol] = 'E';
                        snakePath.addFirst(new int[]{newRow, newCol});
                        // 检查头是否碰撞了
                        if (snakeBody.contains(newHeadKey)) {
                            // 说明碰撞上了
                            gameOver = true;
                            break;
                        }
                        snakeBody.add(newHeadKey);
                    } else {
                        // 表示不是一个食物，蛇头要移动到新位置，蛇尾要移除
                        int[] tailData = snakePath.removeLast();
                        snakeBody.remove(tailData[0] + "," + tailData[1]);
                        // 检查新头是否和剩余部分碰撞
                        if (snakeBody.contains(newHeadKey)) {
                            gameOver = true;
                            break;
                        } else {
                            snakePath.addFirst(new int[]{newRow, newCol});
                            snakeBody.add(newHeadKey);
                        }
                    }
                    break;
            }
        }

        System.out.println(snakePath.size());
    }
}
