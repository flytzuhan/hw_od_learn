package com.huawei.od.excise.year25.b.n007;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] target = in.nextLine().split(" ");
        int m = Integer.parseInt(target[0]);
        int n = Integer.parseInt(target[1]);

        // 构造输入的数据结构
        ArrayList<ArrayList<Integer>> edgeList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] edgeArr = in.nextLine().split(" ");
            ArrayList<Integer> edge = new ArrayList<>();
            edge.add(Integer.parseInt(edgeArr[0]));
            edge.add(Integer.parseInt(edgeArr[1]));
            edgeList.add(edge);
        }

        // 遍历所有可能得经历
        int count = 0;
        for (int i = 0; i < (1 << m); i++) {
            boolean flag = true;
            for (int j = 0; j < n; j++) {
                // 检测相同的边是否同为红颜色
                if (((i >> (m-edgeList.get(j).get(0))) & 1) == 1 && ((i >> (m-edgeList.get(j).get(1))) & 1) == 1) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                count++;
            }
        }
        System.out.println(count);
    }
}
