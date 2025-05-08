package com.huawei.od.excise.year25.no003;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        String[] lines = line.split(",");
        int[] nums = new int[lines.length];
        for (int i = 0; i < lines.length; i++) {
            nums[i] = Integer.parseInt(lines[i]);
        }

        String nextLine = in.nextLine();
        String[] nextLines = nextLine.split(",");
        int[] nums2 = new int[nextLines.length];
        for (int i = 0; i < nextLines.length; i++) {
            nums2[i] = Integer.parseInt(nextLines[i]);
        }

        Map<Integer, Integer> map1 = new HashMap<>();
        int i = 0;
        while (true) {
            if (i >= nums.length) {
                break;
            } else {
                if (map1.containsKey(nums[i])) {
                    map1.put(nums[i], map1.get(nums[i]) + 1);
                } else {
                    map1.put(nums[i], 1);
                }
            }
            i += 1;
        }

        Map<Integer, Integer> map2 = new HashMap<>();
        int j = 0;
        while (true) {
            if (j >= nums2.length) {
                break;
            } else {
                if (map2.containsKey(nums2[j])) {
                    map2.put(nums2[j], map2.get(nums2[j]) + 1);
                } else {
                    map2.put(nums2[j], 1);
                }
            }
            j += 1;
        }

        // 开始处理数据
        boolean flag = true;
        String output = "NULL";
        Map<Integer, List<Integer>> result = new HashMap<>();
        for (Integer num : map1.keySet()) {
            if (map2.containsKey(num)) {
                flag = false;
                int count = Math.min(map1.get(num), map2.get(num));
                if (result.containsKey(count)) {
                    result.get(count).add(num);
                } else {
                    result.put(count, new ArrayList<>());
                    result.get(count).add(num);
                }
            }
        }

        if (!flag) {
            List<Integer> tmp = new ArrayList<>();
            for (Integer num : result.keySet()) {
                tmp.add(num);
            }
            Collections.sort(tmp, new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return o1-o2;
                }
            });
            output = "";
            for (int m = 0; m < tmp.size(); m++) {
                output += tmp.get(m) + ":";
                for (int n = 0; n < result.get(tmp.get(m)).size(); n++) {
                    output += result.get(tmp.get(m)).get(n);
                    if (n != result.get(tmp.get(m)).size() - 1) {
                        output += ",";
                    }
                }
                output += "\n";
            }
            System.out.println(output);
        } else {
            System.out.println(output);
        }

        return;
    }
}
