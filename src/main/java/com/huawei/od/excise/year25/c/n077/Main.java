package com.huawei.od.excise.year25.c.n077;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 水果摊小买卖 -- 贪心算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] cost = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int[] prices = Arrays.stream(in.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int k = Integer.parseInt(in.nextLine());
        // 现在要对这个进行排序
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < cost.length; i++) {
            list.add(new int[]{cost[i], prices[i], i});
        }
        list.stream()
                .filter(val -> val[0] <= k)
                .filter(val -> val[1]-val[0] > 0)
                .collect(Collectors.toList())
                .sort(Comparator.comparingInt(a -> a[0]));
        int result = k;
        for (int[] val : list) {
            result += val[1] - val[0];
        }

        System.out.println(result);
    }
}
