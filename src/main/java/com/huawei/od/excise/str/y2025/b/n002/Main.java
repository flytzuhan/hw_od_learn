package com.huawei.od.excise.str.y2025.b.n002;

import java.util.Scanner;

/**
 * 拼接URL
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String result = input;
        if (input.contains(",")) {
            String rep = "/";
            result = input.replace(",", rep).replaceAll("/+", rep);
        }
        System.out.println(result);
    }
}
