package com.huawei.od.excise.last;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 运维日志排序 -- 自定义排序
 */
public class LogTimeMain {

    public static void main(String[] args) {
        // 获取扫描器，从命令行中获取数据
        Scanner scanner = new Scanner(System.in);
        int inputNum = scanner.nextInt();
        // 跳转到下一行
        scanner.nextLine();
        // 获取输入的日志数据
        // 使用一个列表来存储输入的日志数据
        List<String> logList = new ArrayList<>();
        for (int i = 0; i < inputNum; i++) {
            logList.add(scanner.nextLine());
        }

        // 现在对这个列表进行排序
        logList.sort((time1, time2) -> {
            int logTime1 = convertStrLogToInteger(time1);
            int logTime2 = convertStrLogToInteger(time2);
            return logTime1 - logTime2;
        });

        logList.forEach(System.out::println);
    }

    private static int convertStrLogToInteger(String time) {
        // 使用正则表达式来进行匹配
        String regex = "(\\d+):(\\d+):(\\d+)\\.(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(time);
        if (matcher.matches()) {
            // 获取对应的时分秒数据
            int hour = Integer.parseInt(matcher.group(1)) * 3600 * 1000;
            // 获取分数对应的毫秒数
            int minute = Integer.parseInt(matcher.group(2)) * 60 * 1000;
            // 获取秒数对应的毫秒数
            int second = Integer.parseInt(matcher.group(3)) * 1000;
            // 获取毫秒数
            int millSecond = Integer.parseInt(matcher.group(4));
            return hour + minute + second + millSecond;
        } else {
            return 0;
        }
    }
}
