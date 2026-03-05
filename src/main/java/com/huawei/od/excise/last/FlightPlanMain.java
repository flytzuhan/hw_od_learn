package com.huawei.od.excise.last;

import java.util.*;
/**
 * 航班行程规划 -- 自定义排序
 */
public class FlightPlanMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 获取航班数据并使用空格拆分成数组
        String[] flightData = in.nextLine().split(",");
        // 现在开始对航班数据进行升序排序，需要使用自定义的排序规则
        Arrays.sort(flightData, (flight1, flight2) -> {
            // 先获取航班的前面两个字母
            String companyPrefix1 = flight1.substring(0, 2);
            String companyPrefix2 = flight2.substring(0, 2);
            // 再获取航班对应的编号
            int flightNum1 = Integer.parseInt(flight1.substring(2));
            int flightNum2 = Integer.parseInt(flight2.substring(2));
            if (companyPrefix1.equals(companyPrefix2)) {
                // 说明两个航班是同一家公司的，比较航班编号
                return flightNum1 - flightNum2;
            } else {
                return companyPrefix1.compareTo(companyPrefix2);
            }
        });

        // 现在要开始打印这些排好序的航班信息了
        System.out.println(String.join(",", flightData));
    }
}
