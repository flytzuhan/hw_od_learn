package com.huawei.od.excise.year25.b.n171;

import java.util.*;

/**
 * 最小传输时延I -- Dijkstra算法
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        // 使用邻接表来存储数据
        List<List<int[]>> list = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        // 现在读取数据
        for (int i = 0; i < m; i++) {
            int[] nums = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int from = nums[0];
            int to = nums[1];
            int weight = nums[2];
            list.get(from).add(new int[]{to, weight});
        }

        // 现在开始读取开始节点和结束节点
        int start = in.nextInt();
        int end = in.nextInt();
        // 声明存储结果集的数组
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        boolean[] visited = new boolean[n+1];
        // 使用优先级队列来计算最小值
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});
        while (!pq.isEmpty()) {
            int [] num = pq.poll();
            int from = num[0];
            int weight = num[1];
            if (visited[from]) {
                continue;
            }
            visited[from] = true;
            // 获取邻接的节点
            for (int[] node : list.get(from)) {
                int to = node[0];
                int distance = node[1];
                if (!visited[to] && weight + distance < dist[to]) {
                    dist[to] = weight + distance;
                    pq.offer(new int[]{to, dist[to]});
                }
            }
        }

        System.out.println(dist[end] == Integer.MAX_VALUE ? -1 : dist[end]);
    }
}
