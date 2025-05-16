package com.huawei.od.excise.year25.b.n002;

import java.util.Scanner;

/**
 * 拼接URL
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(",");
        StringBuilder result = new StringBuilder();
        if (input.length == 0) {
            System.out.println("/");
            return;
        }
        if (input[0].charAt(0) != '/') {
            result.append("/");
        }
        if (input[0].charAt(input[0].length() - 1) == '/') {
            result.append(input[0], 0, input[0].length() - 1);
        } else {
            result.append(input[0]);
        }
        result.append("/");
        if (input[1].charAt(0) == '/') {
            result.append(input[1], 1, input[1].length());
        } else {
            result.append(input[1]);
        }
        System.out.println(result);
    }
}
