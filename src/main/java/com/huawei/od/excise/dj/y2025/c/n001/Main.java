package com.huawei.od.excise.dj.y2025.c.n001;

import java.util.*;

/**
 * 电脑感染病毒 -- Dijkstra算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        // 使用List存储邻接关系，表示每个电脑的邻接关系，电脑的编号是从1到n，因此循环的时候，索引从0开始
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        // 读取数据
        for (int i = 0; i < m; i++) {
            int[] relation = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = relation[0];
            int to = relation[1];
            int weight = relation[2];
            graph.get(from).add(new int[]{to, weight});
        }
        int start = in.nextInt();
        // 添加最后的更新值的数组
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        // 声明一个优先级队列，用来存储当前最短距离的节点
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int from = node[0];
            int distance = node[1];
            if (distance > dist[from]) {
                continue;
            }
            for(int[] edge : graph.get(from)) {
                int to = edge[0];
                int weight = edge[1];
                if (distance + weight < dist[to]) {
                    dist[to] = distance + weight;
                    pq.offer(new int[]{to, dist[to]});
                }
            }
        }

        // 计算最大值
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
