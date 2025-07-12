package com.huawei.od.excise.year25.a.n012;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 斗地主之顺子 -- 字符串处理
 */
public class Main {

    private static Integer toInt(String type) {
        switch (type) {
            case "J":
                return 11;
            case "Q":
                return 12;
            case "K":
                return 13;
            case "A":
                return 14;
            default:
                return Integer.parseInt(type);
        }
    }

    private static String toStr(Integer num) {
        switch (num) {
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "A";
            default:
                return String.valueOf(num);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 先获取这个字符串
        String[] params = in.nextLine().split(" ");
        List<Integer> nums = new ArrayList<>();
        for (String param : params) {
            Integer val = toInt(param);
            if (val != 2) {
                nums.add(val);
            }
        }
        // 对整个列表进行排序
        Collections.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        while (nums.size() >= 5) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(nums.get(0));
            nums.remove(0);
            // 现在开始循环处理
            for (int i = 0; i < nums.size(); i++) {
                // 判断这个值跟tmp中的元素是否连续
                if (nums.get(i).equals(tmp.get(tmp.size()-1))) {
                    continue;
                } else if (nums.get(i).equals(tmp.get(tmp.size()-1)+1)) {
                    tmp.add(nums.get(i));
                    nums.remove(i);
                    i--;
                } else {
                    // 不连续了
                    if (tmp.size() >= 5) {
                        result.add(new ArrayList<>(tmp));
                    }
                    tmp.clear();
                }
            }
            if(tmp.size() >= 5) {
                result.add(new ArrayList<>(tmp));
            }
            tmp.clear();
        }

        // 现在要对结果集合进行排序
        result.sort((a, b) -> {
            for (int i = 0; i < Math.min(a.size(), b.size()); i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i).compareTo(b.get(i));
                }
            }
            return a.size() - b.size();
        });

        // 对结果集合进行输出
        if (result.isEmpty()) {
            System.out.println("No");
        } else {
            for (List<Integer> integers : result) {
                System.out.println(integers.stream().map(Main::toStr).collect(Collectors.joining(" ")));
            }
        }
    }
}
