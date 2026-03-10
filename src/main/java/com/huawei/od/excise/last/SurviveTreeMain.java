package com.huawei.od.excise.last;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 补种胡杨-- 滑动窗口
 */
public class SurviveTreeMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 先获取总的胡杨树木
        int totalTreeNumber = Integer.parseInt(in.nextLine());
        // 获取未成活的胡杨数量
        int unSurvivorTreeNum = Integer.parseInt(in.nextLine());
        // 获取未成活的胡杨的树木编号
        int[] unSurvivorTreePos = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 先定义胡杨的总数据，并将未成活的位置设置为0
        int[] treeList = new int[totalTreeNumber+1];
        for (int i = 1; i <= totalTreeNumber; i++) {
            treeList[i] = 1;
        }
        // 将未成活的位置设置为0
        for (int unSurvivorTreePo : unSurvivorTreePos) {
            treeList[unSurvivorTreePo] = 0;
        }

        // 获取补种的胡杨数量
        int addTreeNumber = Integer.parseInt(in.nextLine());

        // 现在要使用滑动窗口来计算
        int left = 1;
        int count = 0;
        for (int right = 1; right <= totalTreeNumber; right++) {
            // 从左往右开始滑动，并计算每个区间的胡杨存活数量
            int currentTree = treeList[right];
            if (currentTree == 0) {
                // 说明这里需要补种
                addTreeNumber--;
                while (addTreeNumber < 0) {
                    // 需要移动左边的指针
                    if (treeList[left] == 0) {
                        addTreeNumber++;
                    }
                    left++;
                }
            }
            count = Math.max(count, right - left + 1);
        }

        System.out.println(count);
    }
}
