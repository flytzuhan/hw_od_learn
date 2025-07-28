package com.huawei.od.excise.tree.y2025.b.n022;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 小明减肥 -- DFS
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] target = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int n = target[0];
        int t = target[1];
        int k = target[2];

        int[] calories = new int[n];
        for (int i = 0; i < n; i++) {
            calories[i] = in.nextInt();
        }
        if (!(n > 0 && n < 10 && t > 0 && 0 < k && k <= n)) {
            System.out.println(0);
            return;
        }
        System.out.println(dfs(0, k, 0, n, t, calories));
    }

    public static int dfs(int start, int remainK, int curSum, int n, int t, int[] grid) {
        if (remainK == 0) {
            return curSum == t ? 1 : 0;
        }
        // 判断剩余的运动不勾选或者超出目标值
        if (start >= n || remainK > n -start || curSum > t) {
            return 0;
        }
        // 不选择当前运动
        int count = dfs(start+1, remainK, curSum, n, t, grid);
        // 选择当前的运动
        count += dfs(start+1, remainK-1, curSum+grid[start], n, t, grid);
        return count;
    }
}
