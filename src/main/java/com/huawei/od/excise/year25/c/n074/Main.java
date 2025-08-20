package com.huawei.od.excise.year25.c.n074;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 滑动窗口最大值 -- 滑动窗口
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(in.nextLine());
        // 创建一个滑动窗口
        int left = 0;
        int result = 0;
        int sum = 0;
        for(int right = 0; right < n; right++) {
            sum += nums[right];
            while (left <= right && right - left + 1 >= m) {
                result = Math.max(result, sum);
                sum -= nums[left++];
            }
        }
        System.out.println(result);
    }

    public static void solution(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(in.nextLine());
        // 现在使用另外一种滑动窗口的算法来计算这个题目
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += nums[i];
        }
        int result = sum;
        for (int i = m; i < n; i++) {
            sum += nums[i] - nums[i - m];
            result = Math.max(result, sum);
        }

        System.out.println(result);
    }
}
