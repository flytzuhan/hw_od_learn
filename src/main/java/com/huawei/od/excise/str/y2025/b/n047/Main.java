package com.huawei.od.excise.str.y2025.b.n047;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 人民币转化
 */
public class Main {

    private static final String[] CN_NUM = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    private static final String[] CN_UNIT = {"仟", "佰", "拾", ""};

    private static final String[] CN_BIG_UNIT = {"", "万", "亿", "万亿"};

    public static String exchange(double num) {
        DecimalFormat df = new DecimalFormat("0.00");
        String numStr = df.format(num);
        String[] numArr = numStr.split("\\.");
        String intStr = numArr[0];
        String decimalStr = numArr[1];

        // 先转换整数部分
        String intChinese = convertToIntPart(intStr);

        StringBuilder result = new StringBuilder();
        if (intChinese.equals("零")) {
            result.append("零元");
        } else {
            result.append(intChinese).append("元");
        }

        // 开始处理小数部分
        int first = Integer.parseInt(decimalStr.substring(0, 1));
        int second = Integer.parseInt(decimalStr.substring(1, 2));
        if (first == 0 && second == 0) {
            result.append("整");
        } else {
            if (first != 0) {
                result.append(CN_NUM[first]).append("角");
            }
            if (second != 0) {
                result.append(CN_NUM[second]).append("分");
            }
        }

        return result.toString();
    }

    private static String convertToIntPart(String numStr) {
        if (numStr == null || numStr.isEmpty() || "0".equals(numStr)) {
            return "零";
        }
        // 将数字拆分成4个一组
        List<String> group = new ArrayList<>();
        while (!numStr.isEmpty()) {
            if (numStr.length() > 4) {
                group.add(0, numStr.substring(numStr.length() - 4));
                numStr = numStr.substring(0, numStr.length() - 4);
            } else {
                group.add(0, numStr);
                break;
            }
        }
        List<String> result = new ArrayList<>();
        return "";
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            double num = in.nextDouble();
            System.out.println(exchange(num));
        } catch (Exception e) {
            System.out.println("请输入有效的金融数字");
        }
    }
}
