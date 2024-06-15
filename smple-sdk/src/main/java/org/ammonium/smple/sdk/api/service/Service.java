package org.ammonium.smple.sdk.api.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

}
