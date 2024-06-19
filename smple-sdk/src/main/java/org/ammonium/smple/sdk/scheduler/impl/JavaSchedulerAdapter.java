package org.ammonium.smple.sdk.scheduler.impl;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.ammonium.smple.sdk.scheduler.ScheduledTask;
import org.ammonium.smple.sdk.scheduler.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JavaSchedulerAdapter implements Scheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger("smple/JavaSchedulerAdapter");

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
        final ScheduledFuture<?> future = this.scheduler.schedule(task, delay, unit);
        return () -> future.cancel(false);
    }

    @Override
    public ScheduledTask runAsyncTaskRepeating(Runnable task, long interval, TimeUnit unit) {
        final ScheduledFuture<?> future = this.scheduler.scheduleAtFixedRate(task, 0, interval, unit);
        return () -> future.cancel(false);
    }

    @Override
    public void shutdown() {
        try {
            this.scheduler.shutdown();
            this.async.shutdown();

            if (this.scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                this.scheduler.shutdownNow();
            }

            if (this.async.awaitTermination(5, TimeUnit.SECONDS)) {
                this.async.shutdownNow();
            }
        } catch (InterruptedException e) {
            LOGGER.error("Failed to shutdown scheduler", e);
        }
    }
}
