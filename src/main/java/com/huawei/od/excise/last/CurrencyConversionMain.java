package com.huawei.od.excise.last;

import java.util.*;

/**
 * 货币转换
 */
public class CurrencyConversionMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 获取输入的货币行数
        int n = Integer.parseInt(in.nextLine());
        // 接下来要定义货币的总和
        double sum = 0;
        // 开始循环获取每一行的货币数据并转成人民币对应的分
        for (int i = 0; i < n; i++) {
            String moneyStr = in.nextLine();
            // 现在使用数字将这个货币数据拆分成对应的多个数据
            String[] units = moneyStr.split("\\d+");
            // 开始循环读取货币单位并计算出来对应的分
            for (String unit : units) {
                if (!unit.isEmpty()) {
                    // 非空的情况下才去处理货币单位
                    String money = moneyStr.substring(0, moneyStr.indexOf(unit));
                    // 获取货币对应的分
                    sum += moneyChange(unit, Double.parseDouble(money));
                    // 然后要处理拆分后的下一个单位，所以要对这一行数据进行替换
                    moneyStr = moneyStr.substring(moneyStr.indexOf(unit) + unit.length());
                }
            }
        }

        System.out.println(Math.round(Math.floor(sum)));
    }

    private static double moneyChange(String unit, double money) {
        switch (unit) {
            case "CNY":
                return money * 100;
            case "fen":
                return money;
            case "JPY":
                return money * 10000 / 1825;
            case "sen":
                return money * 100 / 1825;
            case "HKD":
                return money * 10000 / 123;
            case "cents":
                return money * 100 / 123;
            case "EUR":
                return money * 10000 / 14;
            case "eurocents":
                return money * 100 / 14;
            case "GBP":
                return money * 10000 / 12;
            case "pence":
                return money * 100 / 12;
            default:
                return 0;
        }
    }
}
