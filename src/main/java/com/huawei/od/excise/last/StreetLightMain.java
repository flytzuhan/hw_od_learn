package com.huawei.od.excise.last;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 路灯照明问题 -- 逻辑
 */
public class StreetLightMain {

    public static void main(String[] args) {
        // 获取扫描器，用来从命令行中获取数据
        Scanner scanner = new Scanner(System.in);
        // 获取路灯的数量
        int streetLightNum = Integer.parseInt(scanner.nextLine());
        // 获取每个路灯照亮的半径
        int[] streetLightRadius = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        // 现在计算未照亮的路径的长度
        System.out.println(calcUnLightDistance(streetLightNum, streetLightRadius));
    }

    private static int calcUnLightDistance(int lightNum, int[] streetLightRadius) {
        // 先对边界条件进行处理
        if (lightNum <= 0 || streetLightRadius == null || lightNum != streetLightRadius.length) {
            throw new RuntimeException("数量不匹配");
        }
        // 表示只有一个路灯的情况
        if (lightNum == 1) {
            return 0;
        }
        // 每个路灯之间间隔100米
        int baseGap = 100;
        int unLightDistance = 0;
        // 先计算每个路灯的照亮区间
        List<int[]> lightRange = new ArrayList<>();
        for (int i = 0; i < lightNum; i++) {
            int start = Math.max(0, i * baseGap - streetLightRadius[i]);
            int end = i * baseGap + streetLightRadius[i];
            lightRange.add(new int[]{start, end});
        }

        // 现在对这个照亮区间按照起始位置进行升序排序
        lightRange.sort((light1, light2) -> light1[0] - light2[0]);
        // 先获取第一个路灯作为起始路灯
        int[] currentLight = lightRange.get(0);
        // 排序好了之后就要去合并重叠区间
        List<int[]> mergedLightRange = new ArrayList<>();
        for (int i = 1; i < lightRange.size(); i++) {
            // 获取下一个路灯
            int[] nextLight = lightRange.get(i);
            if (nextLight[0] <= currentLight[1]) {
                // 说明存在重叠
                currentLight[1] = Math.max(currentLight[1], nextLight[1]);
            } else {
                // 不存在的话，就把这个加入到区间里面，然后设置下一个路灯作为开始比较的路灯
                mergedLightRange.add(currentLight);
                currentLight = nextLight;
            }
        }
        // 添加上最后一个路灯的区间
        mergedLightRange.add(currentLight);

        // 现在开始计算未照亮的距离
        for (int i = 1; i < mergedLightRange.size(); i++) {
            unLightDistance += Math.max(0, mergedLightRange.get(i)[0]-mergedLightRange.get(i-1)[1]);
        }

        // 加上从起点到第一个照明范围开始之前的黑暗长度
        unLightDistance += Math.max(0, mergedLightRange.get(0)[0]);
        return unLightDistance;
    }
}
