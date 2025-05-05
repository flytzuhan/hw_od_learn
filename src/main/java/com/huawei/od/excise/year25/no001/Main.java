package com.huawei.od.excise.year25.no001;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 投篮大赛- 考察字符串处理 - 实际上就是处理四个分支就可以了
 * <p>
 * 你现在是一场采用特殊赛制投篮大赛的记录员，这场比赛由若干个回合组成，过去几个回合的得分，可能会影响后续
 * 几个回合的得分。比赛开始的时候，记录是空白的
 * 你会得到一个记录操作的字符串列表，其中ops[i]是你需要记录的第i项操作，ops遵循以下规则：
 * 整数x - 表示本回合新获得分数x
 * “+” - 表示本回合新获得的分数是前两次得分的总和
 * “D” - 表示本回合新获得的分数是前一次得分的两倍
 * “C” - 表示本回合没有分数，并且前一次得分无效，将其从记录中移除
 * 请你返回记录中所有得分的总和
 * <p>
 * 示例：
 * 输入：5 2 C D +
 * 输出：30
 * 解释：
 * 5 - 记录加5，记录现在是[5]
 * 2 - 记录加2，记录现在是[5, 2]
 * C - 使得前一次的记录无效，记录现在是[5]
 * D - 记录加2*5，记录现在是[5, 10]
 * + - 记录加5+10，记录现在是[5, 10, 15]
 * 总的记录和是5+10+15=30
 */
public class Main {

    public static void main(String[] args) {
        // 处理输入
        Scanner scanner = new Scanner(System.in);
        String[] params = scanner.nextLine().split(" ");

        // 使用列表记录所有的数字
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("+")) {
                // 判断scores的长度是否大于2
                if (scores.size() < 2) {
                    System.out.println(-1);
                    return;
                }
                scores.add(scores.get(scores.size() - 2) + scores.get(scores.size() - 1));
            } else if (params[i].equals("D")) {
                if (scores.isEmpty()) {
                    System.out.println(-1);
                    return;
                }
                scores.add(scores.get(scores.size() - 1) * 2);
            } else if (params[i].equals("C")) {
                if (scores.isEmpty()) {
                    System.out.println(-1);
                    return;
                }
                scores.remove(scores.size()-1);
            } else {
                // 纯数字
                scores.add(Integer.parseInt(params[i]));
            }
        }

        if (scores.isEmpty()) {
            System.out.println(0);
        } else {
            System.out.println(scores.stream().reduce(Integer::sum).get());
        }
    }
}
