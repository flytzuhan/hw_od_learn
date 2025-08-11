package com.huawei.od.excise.year25.c.n001;

import java.util.*;

/**
 * 电脑病毒感染 -- Dijsktra算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 电脑熟练
        int n = in.nextInt();
        // 读取数据
        int count = in.nextInt();
        in.nextLine();
        // 使用邻接表来存储数据
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        // 现在开始读取链接数据
        for (int i = 0; i < count; i++) {
            int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = nums[0];
            int to = nums[1];
            int weight = nums[2];
            graph.get(from).add(new int[]{to, weight});
        }

        // 读取电脑的起始节点
        int start = Integer.parseInt(in.nextLine());
        // 设置最后的结果集
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        // 先使用优先级队列来存储数据
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});
        // 循环处理数据
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int cur = node[0];
            int distance = node[1];
            // 判断是否超过最大值
            if (distance > dist[cur]) {
                continue;
            }
            // 遍历当前节点的邻接节点
            for (int[] edge : graph.get(cur)) {
                int next = edge[0];
                int weight = edge[1];
                if (distance + weight < dist[next]) {
                    dist[next] = distance + weight;
                    pq.offer(new int[]{next, dist[next]});
                }
            }
        }

        // 现在要处理结果
        int result = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
            result = Math.max(result, dist[i]);
        }

        System.out.println(result);
    }
}
