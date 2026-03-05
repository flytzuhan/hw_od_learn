package com.huawei.od.excise.last;

import java.util.*;

/**
 * AI面板识别
 */
public class AiDashboardMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 先获取一共有多少个灯
        int lightNumber = Integer.parseInt(in.nextLine());
        // 接着用一个二维数组来保存灯的中心点位置和对应的序号
        int[][] lights = new int[lightNumber][3];
        // 现在开始从每一行中获取灯的数据
        for (int i = 0; i < lightNumber; i++) {
            int[] lightLineArr = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // 记录灯的中心点位置和对应的序号
            int[] tmpLight = new int[3];
            // 每个灯的大小都是一样的，所以每个灯的中心点是可以通过上下左右计算出来的
            tmpLight[0] = lightLineArr[1] + (lightLineArr[3] - lightLineArr[1])/2;
            tmpLight[1] = lightLineArr[2] + (lightLineArr[4] - lightLineArr[2])/2;
            tmpLight[2] = lightLineArr[0];
            lights[i] = tmpLight;
        }

        // 现在要对灯进行排序
        Arrays.sort(lights, (light1, light2) -> {
            // 先获取他们的X坐标和Y坐标
            int x1 = light1[0];
            int x2 = light2[0];
            int y1 = light1[1];
            int y2 = light2[1];
            if (y1 == y2) {
                // 说明他们在同一行上
                return x1 - x2;
            } else {
                // 不在同一行上的话，就按照Y坐标进行排序
                return y1 - y2;
            }
        });

        // 将排好序的路灯转成字符串数组的格式，方便后面打印
        String[] list = Arrays.stream(lights).map(light -> String.valueOf(light[2])).toArray(String[]::new);

        System.out.println(String.join(" ", list));
    }
}