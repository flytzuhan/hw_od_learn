package com.huawei.od.excise.greedy.y2025.b.n159;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 最佳植树距离 -- 贪心算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] trees = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(in.nextLine());
        // 使用快排算法
        Arrays.sort(trees);
        System.out.println(findMaxDistance(trees, n, k));
    }

    public static int findMaxDistance(int[] trees, int n, int k) {
        // 最小间距，一个紧挨着一个，所以最小间距为1
        int left = 1;
        // 最大间距，那就是最后一个树和第一个树之间的距离
        int right = trees[n - 1] - trees[0];
        int result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (check(trees, n, k, mid)) {
                result = mid;
                left = mid + 1;
            } else {
                // 说明间隙太大了，导致了树没法种植完
                right = mid - 1;
            }
        }

        return result;
    }

    public static boolean check(int[] trees, int n, int k, int mid) {
        // 这里表示最左侧的那一颗先种下去，因此count=1，树已经种了1颗
        int count = 1;
        // 树已经种了1颗，位置就是数组中的最左侧的位置
        int lastPosition = trees[0];
        for(int i = 1; i < n; i++) {
            if (trees[i] - lastPosition >= mid) {
                count++;
                lastPosition = trees[i];
                if (count >= k) {
                    return true;
                }
            }
        }

        return false;
    }
}
