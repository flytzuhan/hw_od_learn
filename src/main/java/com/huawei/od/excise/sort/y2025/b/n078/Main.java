package com.huawei.od.excise.sort.y2025.b.n078;

import java.util.*;

/**
 * 78. 磁盘容量 -- 排序算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = Integer.parseInt(in.nextLine());
        // 开始存储数据
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < count; i++) {
            String tmp = in.nextLine();
            String key = tmp;
            int total = 0;
            String[] diskArr = tmp.split("\\d+");
            for (String s : diskArr) {
                if (!s.isEmpty()) {
                    switch (s) {
                        case "M":
                            total += Integer.parseInt(tmp.substring(0, tmp.indexOf(s, 0)));
                            tmp = tmp.substring(tmp.indexOf(s, 0) + 1);
                            break;
                        case "G":
                            total += Integer.parseInt(tmp.substring(0, tmp.indexOf(s, 0))) * 1024;
                            tmp = tmp.substring(tmp.indexOf(s, 0) + 1);
                            break;
                        case "T":
                            total += Integer.parseInt(tmp.substring(0, tmp.indexOf(s, 0))) * 1024 * 1024;
                            tmp = tmp.substring(tmp.indexOf(s, 0) + 1);
                            break;
                    }
                }
            }
            map.put(key, total);
            indexMap.put(key, i);
        }
        // 现在开始对数据进行排序
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        entryList.sort((a, b) -> {
            if (a.getValue() > b.getValue()) {
                return 1;
            } else if (a.getValue().equals(b.getValue())) {
                // 值相等的时候，判断位置
                return indexMap.get(a.getKey()) - indexMap.get(b.getKey());
            } else {
                return -1;
            }
        });

        // 现在知道了排序的规则之后，打印数据
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey());
        }
    }
}
