package com.huawei.od.excise.tree.y2025.c.n021;

import java.util.Scanner;

/**
 * 小华地图寻宝 -- BFS
 */
public class Main {

    private static int count = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] params = in.nextLine().split(" ");
        int m = Integer.parseInt(params[0]);
        int n = Integer.parseInt(params[1]);
        int k = Integer.parseInt(params[2]);
        int[][] visited = new int[m][n];
        bfs(visited, m, n, k, 0, 0);
        System.out.println(count);
    }

    public static void bfs(int[][] visited, int m, int n, int k, int x, int y) {
        // 处理边界值
        if (x < 0 || x>= m || y < 0 || y >= n || visited[x][y] == 1) {
            return;
        }
        // 判断纵横坐标的值
        if (check(x) + check(y) > k) {
            return;
        }
        visited[x][y] = 1;
        count++;
        // 四个方向全部要处理
        bfs(visited, m, n, k, x + 1, y);
        bfs(visited, m, n, k, x - 1, y);
        bfs(visited, m, n, k, x, y + 1);
        bfs(visited, m, n, k, x, y - 1);
    }

    public static int check(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
