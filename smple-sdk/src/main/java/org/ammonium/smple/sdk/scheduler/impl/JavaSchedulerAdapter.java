package org.ammonium.smple.sdk.scheduler.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.ammonium.smple.sdk.scheduler.ScheduledTask;
import org.ammonium.smple.sdk.scheduler.Scheduler;

public final class JavaSchedulerAdapter implements Scheduler {

    private final ScheduledExecutorService scheduler;
    private final ForkJoinPool async;

    public JavaSchedulerAdapter() {
        this.scheduler = new ScheduledThreadPoolExecutor(2,
            new ThreadFactoryBuilder()
                .setDaemon(true)
                .setNameFormat("smple-async-scheduler-%d")
                .build()
        );

        this.async = new ForkJoinPool(
            4,
            ForkJoinPool.defaultForkJoinWorkerThreadFactory,
            null,
            true
        );
    }

    @Override
    public Executor async() {
        return this.async;
    }

    @Override
    public Executor sync() {
        return null;
    }

    @Override
    public ScheduledTask runAsyncTaskLater(Runnable task, long delay, TimeUnit unit) {
        return null;
    }

    @Override
    public ScheduledTask runAsyncTaskRepeating(Runnable task, long interval, TimeUnit unit) {
        return null;
    }

    @Override
    public void shutdown() {

    }
}
