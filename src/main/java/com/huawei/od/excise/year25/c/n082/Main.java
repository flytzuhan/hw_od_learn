package com.huawei.od.excise.year25.c.n082;

import java.util.*;

/**
 * 约瑟夫环问题 -- 约瑟夫环，跟跳7是一种类型的问题
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] arr = in.nextLine().split(",");
        int[] nums = Arrays.stream(arr).mapToInt(Integer::parseInt).toArray();
        int size = in.nextInt(); // 取多少个
        int m = in.nextInt();    // 初始步长

        LinkedList<Integer> queue = new LinkedList<>();
        for (int num : nums) queue.add(num);

        List<Integer> result = new ArrayList<>();
        int count = 1;

        while (size > 0 && !queue.isEmpty()) {
            int num = queue.removeFirst();
            if (count == m) {
                result.add(num);
                m = num; // 动态更新步长
                count = 1;
                size--;
            } else {
                queue.addLast(num);
                count++;
            }
        }

        System.out.println(result);
    }
}
