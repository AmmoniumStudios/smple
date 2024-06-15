package org.ammonium.smple.sdk.api.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public interface Service<ID, T> {

    ExecutorService EXECUTOR = Executors.newSingleThreadExecutor(
        new ThreadFactoryBuilder()
            .setDaemon(true)
            .setNameFormat("smple-sdk-sw-%d")
            .build()
    );

    default CompletableFuture<Void> create(T entity) {
        return CompletableFuture.completedFuture(null);
    }

    default CompletableFuture<T> get(ID id) {
        return CompletableFuture.completedFuture(null);
    }

    default CompletableFuture<Void> update(T entity) {
        return CompletableFuture.completedFuture(null);
    }

    default CompletableFuture<Void> delete(ID id) {
        return CompletableFuture.completedFuture(null);
    }

    default CompletableFuture<Void> deleteAll() {
        return CompletableFuture.completedFuture(null);
    }

    default <E> CompletableFuture<E> supplyAsync(Supplier<E> supplier) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, EXECUTOR);
    }

    default CompletableFuture<Void> runAsync(Runnable runnable) {
        return CompletableFuture.runAsync(() -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }, EXECUTOR);
    }

}
