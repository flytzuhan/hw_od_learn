package com.huawei.od.excise.str.y2025.b.n030;

import java.util.*;

/**
 * 英文输入法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String param = in.nextLine();
        String prefix = in.nextLine();

        Set<String> set = new TreeSet<>();

        StringBuilder sb = new StringBuilder();
        for (char ch : param.toCharArray()) {
            // 判断是否是字母
            if (Character.isLetter(ch)) {
                sb.append(ch);
            } else {
                // 判断单词的长度是否大于0，如果大于0，则添加单词
                if (sb.length() > 0) {
                    set.add(sb.toString());
                }
                // 重置
                sb = new StringBuilder();
            }
        }

        // 添加最后一个单词
        if (sb.length() > 0) {
            set.add(sb.toString());
        }

        // 存储匹配的单词列表
        List<String> list = new ArrayList<>();
        for (String word : set) {
            if (word.startsWith(prefix)) {
                list.add(word);
            }
        }

        if (list.isEmpty()) {
            System.out.println(prefix);
        } else {
            System.out.println(String.join(" ", list));
        }
    }
}
