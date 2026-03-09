package com.huawei.od.excise.last;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网格红绿灯最短路径 - Dijkstra算法
 */
public class GridRedGreenMinPathMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 先来获取第一行数据实际上是地图数据
        int[][] gridMap = parseGridMap(in.nextLine());

        // 获取红绿灯数据
        int[][] redGreenLight = parseGridMap(in.nextLine());

        System.out.println(getMoveDistance(gridMap, redGreenLight));
    }

    private static int[][] parseGridMap(String param) {
        // 用来存放解析字符串之后的数据
        List<int[]> list = new ArrayList<>();
        // 怎么获取这个数据呢，使用正则表达式来获取
        Pattern pattern = Pattern.compile("\\[([\\d,]+)\\]");
        Matcher matcher = pattern.matcher(param);
        while (matcher.find()) {
            int[] lineData = Arrays.stream(matcher.group(1).split(",")).mapToInt(Integer::parseInt).toArray();
            list.add(lineData);
        }

        return list.toArray(new int[0][]);
    }

    private static int getMoveDistance(int[][] gridMap, int[][] redLight) {
        // 先获取行和列的长度
        int m = gridMap.length, n = gridMap[0].length;
        // 然后要记录一下每个位置需要等待的时间，方便直接使用
        Map<String, Integer> costMap = new HashMap<>();
        for (int[] light : redLight) {
            costMap.put(light[0] + "," + light[1], light[2]);
        }
        // 先是要使用优先级队列来完成这个任务
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // 要获取最短的路径长度，那需要将到达每个节点的最短路径先初始化出来
        int[][] dist = new int[m][n];
        // 先填充默认数据
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }
        // 这里要定义方向
        int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        // 默认从左上角开始
        dist[0][0] = 0;
        // 想将这个点添加到优先级队列中
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            // 先拿出来这个点，然后
            int[] current = pq.poll();
            int x = current[1], y = current[2];
            int time = current[0];
            // 如果这个地方就是已经要达到的终点了
            if (x == m - 1 && y == n -1) {
                return time;
            }
            // 永远无法到达这个地方
            if (time > dist[x][y]) {
                continue;
            }
            // 现在开始在四个方向上进行循环处理
            for (int[] direction : directions) {
                int newX = x + direction[0], newY = y + direction[1];
                // 判断是否达到了边界
                if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                    continue;
                }
                // 如果是障碍物的话，就不能走下去了
                if (gridMap[newX][newY] == 1) {
                    continue;
                }
                // 走到下一个格子需要的时间
                int newTime = time + 1;
                // 判断如果下一个格子是红绿灯的话，还要加上红绿灯的等待时间
                String newKey = newX + "," + newY;
                if (costMap.containsKey(newKey)) {
                    newTime += costMap.get(newKey);
                }
                // 判断是否要把这个节点加入进去
                if (newTime < dist[newX][newY]) {
                    // 更新时间
                    dist[newX][newY] = newTime;
                    pq.offer(new int[]{newTime, newX, newY});
                }
            }
        }

        return -1;
    }
}
