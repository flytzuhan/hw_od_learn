package com.huawei.od.excise.last;

import java.util.*;

/**
 * 水池蓄水 -- 双指针
 */
public class WaterReservoirMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 获取山脉的数据
        int[] mountains = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        // 现在要获取可以存水的最大蓄水量
        int left = 0, right = mountains.length -1;
        int bestLeft = -1,  bestRight = -1;
        int maxWater = 0;
        while (left < right) {
            // 先根据当前这两个位置来获取存储的水量，如果当前存储的水量大于等于之前的最大值，则更新最大值和位置
            int water = getWater(mountains, left, right);
            if (water >= maxWater) {
                // 更新当前位置和最大蓄水量
                maxWater = water;
                bestLeft = left;
                bestRight = right;
            }
            // 现在要判断移动哪个的位置呢
            if (mountains[left] < mountains[right]) {
                // 说明左指针比较小，所以要移动左指针，让蓄水量更大
                left++;
            } else {
                // 说明右指针比较小，所以要移动右指针，让蓄水量更大
                right--;
            }
        }
        if (maxWater == 0) {
            System.out.println("0");
        } else {
            System.out.println(bestLeft + " " + bestRight + ":" + maxWater);
        }
    }

    private static int getWater(int[] mountains, int left, int right) {
        // 如果获取当前位置的蓄水量，那就先计算出来没有山脉的情况下的蓄水量，然后再减去包含山脉的部分
        int height = Math.min(mountains[left], mountains[right]);
        int totalWater = height * (right - left + 1);
        // 现在要计算包含山脉部分的空间
        int mountainPart = 0;
        for (int i = left; i <= right; i++) {
            mountainPart += Math.min(mountains[i], height);
        }

        return totalWater - mountainPart;
    }
}
