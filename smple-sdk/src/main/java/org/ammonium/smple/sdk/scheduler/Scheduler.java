package org.ammonium.smple.sdk.scheduler;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * Represents a scheduler for running tasks asynchronously or synchronously.
 */
public interface Scheduler {

    Executor async();

    Executor sync();

    ScheduledTask runAsyncTaskLater(Runnable task, long delay, TimeUnit unit);

    ScheduledTask runAsyncTaskRepeating(Runnable task, long interval, TimeUnit unit);

    void shutdown();

}
