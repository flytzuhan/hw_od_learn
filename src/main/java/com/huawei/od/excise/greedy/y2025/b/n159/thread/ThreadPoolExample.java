package com.huawei.od.excise.greedy.y2025.b.n159.thread;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExample {

    public static void main(String[] args) throws Exception {
        // 创建带监控的线程池
        ThreadPoolExecutor orderThreadPool = MonitorThreadPool.createMonitorThreadPool(
                "order-processor",  // 线程池名称
                5,                  // 核心线程数
                10,                 // 最大线程数
                60L,                // 空闲线程存活时间
                TimeUnit.SECONDS,   // 时间单位
                100                 // 队列容量
        );

        ThreadPoolMonitor monitor = new ThreadPoolMonitor(orderThreadPool, "order-processor");

        monitor.startMonitoring(2, TimeUnit.SECONDS);

        for (int i = 1; i <= 20; i++) {
            final int orderId = i;
            orderThreadPool.submit(() -> {
                System.out.println("执行任务: " + orderId + ", 线程: " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 手动获取监控数据
        Thread.sleep(1000);
        monitor.printStats();

        // 等待任务完成
        Thread.sleep(10000);

        // 关闭线程池
        orderThreadPool.shutdown();
        orderThreadPool.awaitTermination(30, TimeUnit.SECONDS);
    }
}
