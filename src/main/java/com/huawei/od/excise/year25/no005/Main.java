package com.huawei.od.excise.year25.no005;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static int count = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] inputArr = scanner.nextLine().split(" ");
        int m = Integer.parseInt(inputArr[0]);
        int n = Integer.parseInt(inputArr[1]);

        // 存储目录和子目录的关系
        Map<Integer, List<Integer>> relations = new HashMap<>();
        // 存储目录和对应的文件大小
        Map<Integer, Integer> relationKeys = new HashMap<>();

        for (int i = 0; i < m; i++) {
            String[] parts = scanner.nextLine().split(" ");
            int dirId = Integer.parseInt(parts[0]);
            int fileSize = Integer.parseInt(parts[1]);

            relations.put(dirId, new ArrayList<>());
            relationKeys.put(dirId, fileSize);

            // 解析目录和子目录的关系
            if (parts[2].length() > 2) {
                String[] subDirs = parts[2].substring(1, parts[2].length() - 1).split(",");
                List<Integer> subDirIds = Arrays.stream(subDirs).map(Integer::parseInt).collect(Collectors.toList());
                relations.get(dirId).addAll(subDirIds);
            }
        }

        // 关系建立之后需要使用BFS遍历，从查询目录的ID 开始
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(n);
        while (!queue.isEmpty()) {
            int currentDir = queue.pop();
            // 如果当前目录存在于relation中，累加大小
            if (relationKeys.containsKey(currentDir)) {
                count += relationKeys.get(currentDir);
                queue.addAll(relations.get(currentDir)); // 将对应的子目录加入到队列中
            }
        }

        System.out.println(count);
    }
}
