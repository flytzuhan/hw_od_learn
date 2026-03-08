package com.huawei.od.excise.last;

import java.util.*;

/**
 * 字符串解压缩
 */
public class StringDecompressionMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 先来获取压缩后的字符串
        String compressedString = in.nextLine();
        // 使用数字将这个字符串拆分成几个部分
        String[] params = compressedString.split("\\d+");
        // 定义一个map用来保存他们的关联关系,TreeMap会自动排序
        Map<String, Integer> map = new HashMap<>();
        // 开始循环获取数字
        for (int i = 1; i <= params.length; i++) {
            // 先获取这个字符串
            int lastIndex = compressedString.indexOf(params[i-1]) + params[i-1].length();
            if (i < params.length) {
                // 数字部分就是当前的字符串长度-下一个字符串的开头这部分内容
                int currentIndex = compressedString.indexOf(params[i]);
                map.put(params[i-1], Integer.parseInt(compressedString.substring(lastIndex, currentIndex)));
            } else {
                // 直接取结尾的部分即可
                map.put(params[i-1], Integer.parseInt(compressedString.substring(lastIndex)));
            }
        }

        // 现在对这个数据进行排序
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, (map1, map2) -> {
            // 先按照value值进行排序
            int val1 = map1.getValue();
            int val2 = map2.getValue();
            String key1 = map1.getKey();
            String key2 = map2.getKey();
            if (val1 == val2) {
                return key1.compareTo(key2);
            } else {
                return val1 - val2;
            }
        });

        // 现在开始打印结果
        StringBuilder result = new StringBuilder();
        list.forEach(entry -> {
            for (int i = 0; i < entry.getValue(); i++) {
                result.append(entry.getKey());
            }
        });

        System.out.println(result);
    }
}
