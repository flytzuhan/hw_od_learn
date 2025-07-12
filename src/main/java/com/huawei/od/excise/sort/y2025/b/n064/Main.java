package com.huawei.od.excise.sort.y2025.b.n064;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 字符串重新排序 -- 排序算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        String[] params = in.nextLine().split(" ");

        // 对单词内部进行排序
        List<String> list = new ArrayList<>();
        for(String param : params) {
            char[] chars = param.toCharArray();
            Arrays.sort(chars);
            list.add(String.valueOf(chars));
        }

        // 统计每个单词出现的次数
        Map<String, Integer> map = new HashMap<>();
        for(String param : list) {
            map.put(param, map.getOrDefault(param, 0) + 1);
        }
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        // 现在准备开始排序
        entryList.sort((o1, o2) -> {
            // 如果值不相等，按照单词的值降序排序
            if (o1.getValue() > o2.getValue()) {
                return -1;
            } else if (o1.getValue().equals(o2.getValue())) {
                // 值相等的话，按照单词长度升序
                if (o1.getKey().length() < o2.getKey().length()) {
                    return -1;
                } else if (o1.getKey().length() == o2.getKey().length()) {
                    // 长度相等的话，按照单词的字母升序
                    return o1.getKey().compareTo(o2.getKey());
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        });
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String, Integer> entry : entryList) {
            int count = entry.getValue();
            while (count > 0) {
                result.append(entry.getKey()).append(" ");
                count--;
            }

        }
        System.out.println(result);
    }
}
