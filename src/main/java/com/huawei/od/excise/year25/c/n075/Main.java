package com.huawei.od.excise.year25.c.n075;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 全排列 -- 回溯、DFS回溯
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        char[] chars = in.nextLine().toCharArray();
        Arrays.sort(chars);
        List<Character> list = new ArrayList<>();
        List<List<Character>> result = new ArrayList<>();
        boolean[] used = new boolean[chars.length];
        dfs(chars, 0, used, list, result);
        System.out.println(result.size());
    }

    public static void dfs(char[] chars, int index, boolean[] used, List<Character> list, List<List<Character>> result) {
        // 递归结束条件
        if (index == chars.length) {
            result.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (used[i] || (i > 0 && chars[i] == chars[i - 1] && !used[i - 1])) {
                continue;
            }
            used[i] = true;
            list.add(chars[i]);
            dfs(chars, index + 1, used, list, result);
            // 回溯
            list.remove(list.size() - 1);
            used[i] = false;
        }
    }
}
