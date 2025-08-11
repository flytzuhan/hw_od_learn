package com.huawei.od.excise.year25.c.n085;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 统计文本数量 -- 正则匹配
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine()) {
            sb.append(in.nextLine()).append("\n");
        }

        System.out.println(countStatements(sb.toString()));
    }

    public static int countStatements(String text) {
        int result = 0;
        List<Character> current = new ArrayList<>();
        boolean inString = false;
        char quoteChar = 0;
        boolean escaped = false;

        String[] lines = text.split("\n");
        for (String line : lines) {
            int i = 0;
            while (i < line.length()) {
                char ch = line.charAt(i);
                if (escaped) {
                    current.add(ch);
                    escaped = false;
                    i++;
                    continue;
                }
                // 判断是否是转义字符
                if (ch == '\\') {
                    current.add(ch);
                    escaped = true;
                    i++;
                    continue;
                }
                if (ch == '"' || ch == '\'') {
                    if (!inString) {
                        inString = true;
                        quoteChar = ch;
                    } else if (ch == quoteChar) {
                        inString = false;
                    }
                    current.add(ch);
                    i++;
                    continue;
                }
                if (inString) {
                    current.add(ch);
                    i++;
                    continue;
                }
                // 处理注释
                if (ch == '-' && i+1 < line.length() && line.charAt(i+1) == '-') {
                    break;
                }
                // 处理分号
                if (ch == ';') {
                    if (!current.isEmpty() && !isEmptyStatement(current)) {
                        result++;
                    }
                    current.add(ch);
                    i++;
                    continue;
                }
                current.add(ch);
                i++;
            }
            // 处理换行
            if (!inString) {
                current.add(' ');
            }
        }
        // 处理最后一个
        if (!current.isEmpty() && !isEmptyStatement(current)) {
            result++;
        }

        return result;
    }

    public static boolean isEmptyStatement(List<Character> statement) {
        StringBuilder sb = new StringBuilder();
        for (char ch : statement) {
            sb.append(ch);
        }
        return sb.toString().trim().isEmpty();
    }
}
