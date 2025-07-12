package com.huawei.od.excise.str.y2025.b.n017;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 货币单位换算 -- 字符串处理
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double money = 0.0;
        int count = Integer.parseInt(in.nextLine());
        for (int i = 0; i < count; i++) {
            String param = in.nextLine();
            // 使用数字将这个拆分成数组
            String[] tmp = param.split("\\d+");
            for (int j = 0; j < tmp.length; j++) {
                if (!tmp[j].isEmpty()) {
                    String num = param.substring(0, param.indexOf(tmp[j]));
                    money += exchange(tmp[j], Double.parseDouble(num));
                    param = param.substring(param.indexOf(tmp[j]) + tmp[j].length());
                }
            }
        }

        System.out.println(Math.round(Math.floor(money)));
    }

    public static double exchange(String unit, double num) {
        switch (unit) {
            case "CNY":
                return num * 100;
            case "fen":
                return num;
            case "JPY":
                return num / 1825 * 10000;
            case "sen":
                return num / 1825 * 100;
            case "HKD":
                return num / 123 * 10000;
            case "cents":
                return num / 123 * 100;
            case "EUR":
                return num / 14 * 10000;
            case "eurocents":
                return num / 14 * 100;
            case "GBP":
                return num / 12 * 10000;
            case "pence":
                return num / 12 * 100;
        }

        return 0;
    }
}
