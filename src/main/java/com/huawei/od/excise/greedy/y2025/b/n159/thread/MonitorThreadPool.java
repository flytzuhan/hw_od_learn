package com.huawei.od.excise.greedy.y2025.b.n159.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MonitorThreadPool {

    public static ThreadPoolExecutor createMonitorThreadPool(
            String poolName,
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            TimeUnit unit,
            int queueSize) {
        // 创建自定义线程池工厂
        AppThreadPoolFactory threadPoolFactory = new AppThreadPoolFactory(poolName);

        LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(queueSize);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadPoolFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());

        threadPoolExecutor.allowsCoreThreadTimeOut();

        return threadPoolExecutor;
    }
}
