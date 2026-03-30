package com.shadow.bfs;

/**
 * 流浪地球 -- BFS
 */
public class WanderingEarthMain {
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        in.nextLine();
    
        // 先记录每个位置的初始值
        int[] grid = new int[n];
        Arrays.fill(grid, -1);

        // 现在开始获取命令行中输入的数据
        int minTime = Integer.MAX_VALUE;
        for(int i = 0; i < m; i++) {
            int[] postData = Arrays.stream(in.nextLint().split(" ")).mapToInt(Integer::parseInt).toArray();
            minTime = Math.min(minTime, postData[0]);
            grid[postData[1]] = postData[0]; 
        }
        // 现在使用队列来处理数据
        Queue<Integer> queue = new LinkedList<>();
        // 记录最后存储的位置列表
        List<Integer> list = new ArrayList<>();
        // 现在先获取队列中的数据并初始化list
        getPosition(queue, minTime, grid, list);
        // 判断队列是否为空
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int val = queue.poll();
                int newLeft = val - 1;
                int newRight = val + 1;
                if(newLeft < 0) {
                    newLeft = n - 1;
                }
                if(newRight >= n) {
                    newRight = 0;
                }
                if(grid[newLeft] == -1) {
                    grid[newLeft] = minTime+1;
                    queue.add(newLeft);
                }
                if(grid[newRight] == -1) {
                    grid[newRight] = minTime + 1;
                    queue.add(newRight);
                }
            }
            minTime++;
            // 找最新的位置
            getPosition(queue, minTime, grid, list);
        }

        // 打印结果数据
        System.out.println(list.size());
        System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    // 使用方法来获取位置数据
    public static void getPosition(Queue<Integer> queue, int time, int[] grid, List<Integer> list) {
        Set<Integer> set = new HashSet<>(queue);
        for(int i = 0; i < grid.length; i++) {
            if(grid[i] == time && !set.contains(i)) {
                queue.add(i);
            }        
        }

        // 判断列表是否为空
        if(!queue.isEmpty()) {
            list.clear();
            list.addAll(queue);
        }
    }
}