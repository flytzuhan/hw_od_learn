package com.huawei.od.excise.last;

import java.util.*;

/**
 * VLAN资源池
 */
public class VLANResourcePoolMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 先来获取第一行的资源数据
        String[] resourceData = in.nextLine().split(",");
        // 获取申请的资源
        int applyResource = in.nextInt();
        // 将资源数据转成资源池结构
        TreeSet<Integer> resourcePool = getResourcePool(resourceData);
        // 移除申请的资源数据
        resourcePool.remove(applyResource);
        // 将资源池还原成资源数据
        System.out.println(formatPoolData(resourcePool));
    }

    private static String formatPoolData(TreeSet<Integer> resourcePool) {
        List<Integer> resourceList = new ArrayList<>(resourcePool);
        List<String> result = new ArrayList<>();
        int start = 1;
        for (int i = 1; i <= resourceList.size(); i++) {
            if (i < resourceList.size() && resourceList.get(i) == resourceList.get(i-1)+1) {
                continue;
            } else {
                // 说明中断了，不连续了
                if (i == start) {
                    // 表示是一个单数
                    result.add(String.valueOf(resourceList.get(i-1)));
                } else {
                    // 表示是一个区间
                    result.add(resourceList.get(start-1) + "-" + resourceList.get(i-1));
                }
                if (i < resourceList.size()) {
                    start = i+1;
                }
            }
        }
        return String.join(",", result);
    }

    private static TreeSet<Integer> getResourcePool(String[] resourceData) {
        TreeSet<Integer> resourcePool = new TreeSet<>();
        // 遍历数据
        for (String resource : resourceData) {
            if (resource.contains("-")) {
                // 表示是一个区间数据
                int[] poolRange = Arrays.stream(resource.split("-")).mapToInt(Integer::parseInt).toArray();
                for (int i = poolRange[0]; i <= poolRange[1]; i++) {
                    resourcePool.add(i);
                }
            } else {
                // 表示是一个单数据
                resourcePool.add(Integer.parseInt(resource));
            }
        }

        return resourcePool;
    }
}
