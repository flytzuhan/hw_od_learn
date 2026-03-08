package com.huawei.od.excise.last;

import java.util.*;

/**
 * 分苹果 - 二进制
 */
public class DivideAppleMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 先来获取苹果的数量
        int n = scanner.nextInt();
        scanner.nextLine();

        // 获取每个苹果的重量
        int[] appleWeights = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 用两种方式来计算苹果的总重量
        int binaryTotalWeight = 0;
        int planBTotalWeight = Arrays.stream(appleWeights).sum();
        for (int i = 0; i < n; i++) {
            binaryTotalWeight ^= appleWeights[i];
        }

        if (binaryTotalWeight != 0) {
            // 不等于0，说明没有找到一种情况，使得他们两个的和相等
            System.out.println("-1");
        } else {
            // 既然现在使用A的方案，即二进制的方式来分苹果，可以让两个人平分，那么就需要在十进制的基础上，让B同学尽可能的多分，因此要使用
            // 排序的方式，先将苹果升序排序之后，尝试让A同学只分到最小的一个，剩余的全部分给B
            Arrays.sort(appleWeights);
            System.out.println(planBTotalWeight - appleWeights[0]);
        }
    }
}
