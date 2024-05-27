package org.ammonium.smple.sdk.scheduler;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public final class Schedulers {

    private static final ScheduledExecutorService ASYNC_SCHEDULER = Executors.newScheduledThreadPool(4,
        new ThreadFactoryBuilder()
            .setDaemon(true)
            .setNameFormat("smple-sdk-async-scheduler-%d")
            .build()
    );

    private static final BukkitScheduler SYNC_SCHEDULER = Bukkit.getScheduler();

    public static void runTaskTimerAsync(Runnable task, long period, TimeUnit unit) {
        ASYNC_SCHEDULER.schedule(task, period, unit);
    }

    public static ExecutorService async() {
        return ASYNC_SCHEDULER;
    }

    public static BukkitScheduler sync() {
        return SYNC_SCHEDULER;
    }

}
