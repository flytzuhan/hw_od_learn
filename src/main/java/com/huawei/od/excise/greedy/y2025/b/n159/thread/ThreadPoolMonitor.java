package com.huawei.od.excise.greedy.y2025.b.n159.thread;

import java.util.concurrent.*;

public class ThreadPoolMonitor {

    private final ThreadPoolExecutor executor;

    private final String poolName;

    public ThreadPoolMonitor(ThreadPoolExecutor executor, String poolName) {
        this.executor = executor;
        this.poolName = poolName;
    }

    /**
     * 获取线程池数据
     */
    public void printStats() {
        System.out.println("============ 线程池监控数据 ===============");
        System.out.println("线程池名称: " + this.getPoolName());
        System.out.println("核心线程数: " + getExecutor().getCorePoolSize());
        System.out.println("最大线程数: " + getExecutor().getMaximumPoolSize());
        System.out.println("当前线程数: " + getExecutor().getPoolSize());
        System.out.println("活跃线程数: " + getExecutor().getActiveCount());
        System.out.println("队列任务数: " + getExecutor().getQueue().size());
        System.out.println("队列剩余容量: " + getExecutor().getQueue().remainingCapacity());
        System.out.println("已完成任务数: " + getExecutor().getCompletedTaskCount());
        System.out.println("总任务数: " + getExecutor().getTaskCount());
        System.out.println("历史最大线程数: " + getExecutor().getLargestPoolSize());
        System.out.println("线程池使用率: " + String.format("%.2f%%", getPoolUsageRate()));
        System.out.println("队列使用率: " + String.format("%.2f%%", getQueueUsageRate()));
        System.out.println("===================================");
    }

    /**
     * 获取线程池使用率
     */
    public double getPoolUsageRate() {
        int maxmimumPoolSize = getExecutor().getMaximumPoolSize();
        if (maxmimumPoolSize == 0) {
            return 0.0;
        }
        return (double) getExecutor().getActiveCount() / (double) maxmimumPoolSize * 100;
    }

    /**
     * 获取队列使用率
     */
    public double getQueueUsageRate() {
        BlockingQueue<Runnable> queue = getExecutor().getQueue();
        int capacity = queue.size() + queue.remainingCapacity();
        if (capacity == 0) {
            return 0.0;
        }
        return (double) queue.size() / capacity * 100;
    }

    /**
     * 启动定时任务监控
     */
    public void startMonitoring(long period, TimeUnit unit) {
        ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor(
                new AppThreadPoolFactory("monitor-" + poolName, true)
        );
        schedule.scheduleAtFixedRate(this::printStats, 0, period, unit);
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public String getPoolName() {
        return poolName;
    }
}
