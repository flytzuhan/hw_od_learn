package com.huawei.od.excise.year25.c.n084;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 火星文计算 -- 正则匹配
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String text = in.nextLine();
        Pattern pattern = Pattern.compile("(\\d+)\\$(\\d+)");
        while (true) {
            Matcher matcher = pattern.matcher(text);
            if (matcher.find()) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                text = text.replaceAll(pattern.pattern(), Integer.toString(3 * x + y + 2));
            } else {
                break;
            }
        }
        List<Integer> list = new ArrayList<>();
        String delimiter = "#";
        int pos = 0;
        while ((pos = text.indexOf(delimiter)) != -1) {
            list.add(Integer.parseInt(text.substring(0, pos)));
            text = text.substring(pos + delimiter.length());
        }
        list.add(Integer.parseInt(text));

        // 计算结果集
        int sum = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            sum = 2 * sum + 3 * list.get(i) + 4;
        }
        System.out.println(sum);
    }
}
