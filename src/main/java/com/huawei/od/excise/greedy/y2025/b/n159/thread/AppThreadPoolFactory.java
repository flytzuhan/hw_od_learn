package com.huawei.od.excise.greedy.y2025.b.n159.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class AppThreadPoolFactory implements ThreadFactory {

    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePrefix;

    private final boolean daemon;

    public AppThreadPoolFactory(String poolName) {
        this(poolName, false);
    }

    public AppThreadPoolFactory(String namePrefix, boolean daemon) {
        this.namePrefix = namePrefix + "-thread-";
        this.daemon = daemon;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, namePrefix + threadNumber.getAndIncrement());
        thread.setDaemon(daemon);
        thread.setPriority(Thread.NORM_PRIORITY);
        return thread;
    }
}
