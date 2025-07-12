package com.huawei.od.excise.year25.b.n070;

import java.util.Scanner;

/**
 * 中文分词模拟器 -- 动态规划
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] target_words = in.nextLine().split(",");
        int[] target_len = new int[target_words.length];
        for (int i = 0; i < target_words.length; i++) {
            target_len[i] = target_words[i].length();
        }

        String[] words = in.nextLine().split(",");
        int[] words_len = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            words_len[i] = words[i].length();
        }

        String result = "";
        int k = 0;
        while (true) {
            if (k >= target_words.length) {
                break;
            } else {
                int[] dp = new int[target_len[k] + 1];
                for (int i = 0; i <= target_len[k]; i++) {
                    dp[i] = 0;
                }
                dp[target_len[k]] = 1;
                for (int i = target_len[k]; i >= 0; i--) {
                    for (int j = 0; j < words.length; j++) {
                        if (i + words[j].length() <= target_len[k]) {
                            String split_str = target_words[k].substring(i, i + words[j].length());

                            if (dp[i + words[j].length()] == 1 && split_str.equals(words[j])) {
                                dp[i] = 1;
                                break;
                            }
                        }

                    }
                }

                if (dp[0] != 0) {
                    String temp_res = "";
                    int i = 0;
                    while (true) {
                        if (i >= target_len[k]) {
                            break;
                        } else {
                            int pos = -1;
                            for (int j = 0; j < words.length; j++) {
                                if (i + words_len[j] <= target_len[k]) {
                                    String split_str = target_words[k].substring(i, i + words_len[j]);
                                    if (split_str.equals(words[j])
                                            && dp[i + words_len[j]] == 1 && words_len[j] > pos) {
                                        pos = words_len[j];
                                    }
                                }
                            }
                            temp_res += target_words[k].substring(i, i + pos) + ",";
                            i += pos;
                        }

                    }
                    result += temp_res;
                } else {
                    char[] char_arr = target_words[k].toCharArray();
                    for (int i = 0; i < char_arr.length; i++) {
                        System.out.print(char_arr[i]);
                        if (i != char_arr.length - 1) {
                            System.out.print(",");
                        }
                    }
                    return;
                }
            }
            k += 1;
        }

        System.out.println(result.substring(0, result.length() - 1));

    }
}
