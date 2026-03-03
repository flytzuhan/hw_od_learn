package com.huawei.od.excise.last;

import java.util.*;

/**
 * 国际移动用户识别 -- 递归
 */
public class IMSIMain {

    public static void main(String[] args) {
        // 先获取扫描器，用来从命令行中获取数据
        Scanner scanner = new Scanner(System.in);
        // 获取配置项数据并转成数组的格式
        String[] networkConfigs = Arrays.stream(scanner.nextLine().split(",")).toArray(String[]::new);
        // 获取要匹配的目标字符串
        String targetStr = scanner.nextLine();

        // 现在需要定义一个列表用来存储可以匹配到的配置项
        List<String> matchedList = new ArrayList<>();

        // 现在开始循环匹配字符串
        for (String networkConfig : networkConfigs) {
            if (checkMatched(networkConfig, targetStr)) {
                // 这表示匹配到了，添加到匹配列表中
                matchedList.add(networkConfig);
            }
        }

        // 现在需要判断存储匹配配置项的列表是为空
        if (matchedList.isEmpty()) {
            System.out.println("null");
        } else {
            // 表示匹配的配置项列表中存在数据，需要排序，然后使用,将他们拼接起来
            Collections.sort(matchedList);
            System.out.println(String.join(",", matchedList));
        }

        // 关闭扫描器
        scanner.close();
    }

    private static boolean checkMatched(String config, String targetStr) {
        return isMatch(config, 0, targetStr, 0);
    }

    /**
     * 一个字符一个字符的匹配，判断配置项和目标字符串是否匹配
     */
    private static boolean isMatch(String config, int cIndex, String targetStr, int tIndex) {
        // 判断配置项是否已经循环结束
        if (cIndex == config.length()) {
            // 判断目标字符串是否循环完毕
            return tIndex == targetStr.length();
        }

        // 获取当前的字符
        char currentChar = config.charAt(cIndex);

        // 分情况判断当前字符
        if ('*' == currentChar) {
            // 表示匹配到了*，也就是说可以匹配0次、1次或者是多次，先尝试匹配0次看看情况
            if (isMatch(config, cIndex + 1, targetStr, tIndex)) {
                return true;
            }
            // 尝试匹配1次或者是多次
            for (int i = tIndex; i < targetStr.length(); i++) {
                if (isMatch(config, cIndex + 1, targetStr, i + 1)) {
                    return true;
                }
            }

            // 没有匹配到
            return false;
        }

        // 如果是目标字符串已经循环完毕了
        if (tIndex >= targetStr.length()) {
            return false;
        }
        // 现在来判断是?的情况
        if ('?' == currentChar) {
            // 当前这个字符要是奇数才能算是匹配到
            if (tIndex % 2 == 1) {
                // 是奇数，接着继续往后面匹配
                return isMatch(config, cIndex + 1, targetStr, tIndex + 1);
            } else {
                // 表示不是奇数
                return false;
            }
        }

        // 现在来对数字做判断，要完全匹配
        if (targetStr.charAt(tIndex) == currentChar) {
            return isMatch(config, cIndex + 1, targetStr, tIndex + 1);
        }

        return false;
    }
}
