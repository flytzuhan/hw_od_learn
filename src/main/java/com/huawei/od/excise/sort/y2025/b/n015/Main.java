package com.huawei.od.excise.sort.y2025.b.n015;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] params = in.nextLine().split(",");
        if (params.length == 1) {
            System.out.println(params[0]);
        } else if (params.length == 2) {
            // 先拼接两个数字，再比较
            int a = Integer.parseInt(params[0] + params[1]);
            int b = Integer.parseInt(params[1] + params[0]);
            System.out.println(Math.min(a, b));
        } else {
            // 数组长度超过3了，先按照升序的方式进行排序
            Arrays.sort(params, Comparator.comparingInt(Integer::parseInt));
            String[] minArr = Arrays.copyOf(params, 3);
            // 现在使用拷贝后的数组先进行升序排序
            Arrays.sort(minArr, (a, b) -> (a + b).compareTo(b + a));
            System.out.println(String.join("", Arrays.asList(minArr)));
        }
    }
}
