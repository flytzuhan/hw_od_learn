package com.huawei.od.excise.bfs;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 流浪地球 -- BFS
 */
public class EarthMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // 先获取发动机总数和手动启动的总数
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
        // 声明一个数组用来保存发动机启动的位置
        int[] engines = new int[n];
        Arrays.fill(engines, -1);

        // 接下来用map来存储每个时刻启动的发动机位置编号
        int minTime = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            int[] startPos = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            minTime = Math.min(minTime, startPos[0]);
            engines[startPos[1]] = startPos[0];
        }

        // 使用BFS来启动,先找出来最小的时刻，然后把这个时刻的发动机全部启动
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        getStartEngines(engines, minTime, queue, list);
        while (!queue.isEmpty()) {
            // 找到当前时刻要启动的发动机
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int post = queue.poll();
                int left = post - 1;
                int right = post + 1;
                if (left < 0) {
                    left = n -1;
                }
                if (right >= n) {
                    right = 0;
                }
                // 现在判断当前位置是否为-1，如果是-1的话，就代表这个位置要启动
                if (engines[left] == -1) {
                    engines[left] = minTime+1;
                    queue.add(left);
                }
                if (engines[right] == -1) {
                    engines[right] = minTime + 1;
                    queue.add(right);
                }
            }
            minTime++;
            getStartEngines(engines, minTime, queue, list);
        }

        // 现在处理完了所有的，判断结果
        System.out.println(list.size());
        System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void getStartEngines(int[] engines, int startTime, Queue<Integer> queue, List<Integer> list) {
        // 先获取队列中所有的发动机
        Set<Integer> set = new HashSet<>(queue);
        for (int i = 0; i < engines.length; i++) {
            if (engines[i] == startTime && !set.contains(i)) {
                queue.add(i);
            }
        }

        // 判断list数据
        if (!queue.isEmpty()) {
            list.clear();
            list.addAll(queue);
        }
    }
}
